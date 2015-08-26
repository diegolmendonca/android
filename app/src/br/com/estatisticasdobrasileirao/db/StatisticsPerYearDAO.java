package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;

import org.apache.commons.collections4.ListUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.entity.Statistics;
import br.com.estatisticasdobrasileirao.externalDB.ExternalDbOpenHelper;

public class StatisticsPerYearDAO {
	
	private SQLiteDatabase database;
	
	public Statistics getAwayMatches(Context context, String favoriteTeamName, String year) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "select  h.*, T1.teamName as homeTeam, T2.teamName as awayTeam from historico h " +
				"INNER JOIN times AS T1 ON h.homeTeam_id = T1._id  " +
				"INNER JOIN times AS T2 ON h.awayTeam_id = T2._id  " +
				"where  T2.teamName = ?  and h.gameYear between ? and ? order by h._id";
		
		Cursor cursor = database.rawQuery(MY_QUERY, new String[]{favoriteTeamName.toUpperCase(), year,year});
		
		Statistics statistics = buildStatistics(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		
		// Precisa inverter o resultado do time da casa!
		return buildAwayStatistics(statistics);

	}

	
	public Statistics getHomeMatches(Context context, String favoriteTeamName, String year) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "select  h.*, T1.teamName as homeTeam, T2.teamName as awayTeam from historico h " +
				"INNER JOIN times AS T1 ON h.homeTeam_id = T1._id  " +
				"INNER JOIN times AS T2 ON h.awayTeam_id = T2._id  " +
				"where  T1.teamName = ?  and h.gameYear between ? and ? order by h._id";
		
		Cursor cursor = database.rawQuery(MY_QUERY, new String[]{favoriteTeamName.toUpperCase(), year,year});
		
		Statistics statistics = buildStatistics(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		return statistics;

	}
	
	public Statistics getBothMatches(Context context, String favoriteTeamName, String year) {
		
		Statistics homeStatistics = getHomeMatches(context, favoriteTeamName, year);
		Statistics awayStatistics = getAwayMatches(context, favoriteTeamName, year);
		
		Statistics bothStatistics = new Statistics();
		bothStatistics.setDrawGames((ArrayList<Game>) ListUtils.union(homeStatistics.getDrawGames(), awayStatistics.getDrawGames()));
		bothStatistics.setLossGames((ArrayList<Game>) ListUtils.union(homeStatistics.getLossGames(), awayStatistics.getLossGames()));
		bothStatistics.setWinGames((ArrayList<Game>) ListUtils.union(homeStatistics.getWinGames(), awayStatistics.getWinGames()));
		bothStatistics.setDraws(Integer.toString(Integer.parseInt(homeStatistics.getDraws()) + Integer.parseInt(awayStatistics.getDraws())));
		bothStatistics.setLosses(Integer.toString(Integer.parseInt(homeStatistics.getLosses()) + Integer.parseInt(awayStatistics.getLosses())));
		bothStatistics.setWins(Integer.toString(Integer.parseInt(homeStatistics.getWins()) + Integer.parseInt(awayStatistics.getWins())));
		bothStatistics.setGoalsMade(Integer.toString(Integer.parseInt(homeStatistics.getGoalsMade()) + Integer.parseInt(awayStatistics.getGoalsMade())));
		bothStatistics.setGoalsTaken(Integer.toString(Integer.parseInt(homeStatistics.getGoalsTaken()) + Integer.parseInt(awayStatistics.getGoalsTaken())));

		return bothStatistics;

	}

	private Statistics buildStatistics(String favoriteTeamName, Cursor cursor) {
		Statistics statistics = new Statistics();
		
		cursor.moveToFirst();
		
		int wins = 0;
		int draws= 0;
		int losses = 0;
		int goalsMade = 0 ;
		int goalsTaken = 0;
		ArrayList<Game> winGames = new ArrayList<Game>();
		ArrayList<Game> drawGames = new ArrayList<Game>();
		ArrayList<Game> lossGames = new ArrayList<Game>();
		
		if (!cursor.isAfterLast()) {
			do {
				int resultHome = cursor.getInt(2);
				int resultAway = cursor.getInt(3);
				goalsMade +=  resultHome;
				goalsTaken += resultAway;
				
				if (resultHome > resultAway){
					winGames.add(buildGame(cursor));
					wins ++;
				}
				else if (resultHome < resultAway){
					lossGames.add(buildGame(cursor));
					losses++;
				}
				else {
					drawGames.add(buildGame(cursor));
					draws++;
				}
			} while (cursor.moveToNext());
		}
		
		cursor.close();
		
		statistics.setDrawGames(drawGames);
		statistics.setWinGames(winGames);
		statistics.setLossGames(lossGames);
		statistics.setLosses(Integer.toString(losses));
		statistics.setWins(Integer.toString(wins));
		statistics.setDraws(Integer.toString(draws));
		statistics.setGoalsMade(Integer.toString(goalsMade));
		statistics.setGoalsTaken(Integer.toString(goalsTaken));
		
		return statistics;
	}

	private Game buildGame(Cursor cursor) {
		Game game = new Game();
		game.setAwayResult(cursor.getString(3));
		game.setHomeResult(cursor.getString(2));
		game.setHomeTeam(cursor.getString(8));
		game.setAwayTeam(cursor.getString(9));
		game.setGameDate(cursor.getString(6));
		return game;
	}
	
	private Statistics buildAwayStatistics(Statistics statistics) {
		ArrayList<Game> lossGames = statistics.getLossGames();
		String goalsMade = statistics.getGoalsMade();
		String losses = statistics.getLosses();
		statistics.setLossGames(statistics.getWinGames());
		statistics.setWinGames(lossGames);
		statistics.setGoalsMade(statistics.getGoalsTaken());
		statistics.setGoalsTaken(goalsMade);
		statistics.setLosses(statistics.getWins());
		statistics.setWins(losses);
		return statistics;
	}

}
