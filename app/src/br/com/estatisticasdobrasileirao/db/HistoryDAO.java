package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.estatisticasdobrasileirao.entity.ChampionshipStatis;
import br.com.estatisticasdobrasileirao.externalDB.ExternalDbOpenHelper;

public class HistoryDAO {

	private SQLiteDatabase database;

	public List<ChampionshipStatis> getAwayMatches(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "select  count(*) as totalGame, " + "T1.teamName as awayTeam " + "from historico h "
				+ "INNER JOIN TIMES AS T1 ON h.awayTeam_id = T1._id  "
				+ "group by T1.teamName" + " order by totalGame desc";
		
		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGames(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		return championshipStatisList;

	}
	
	public List<ChampionshipStatis> getHomeMatches(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "select  count(*) as totalGame, " + "T1.teamName as homeTeam " + "from historico h "
				+ "INNER JOIN TIMES AS T1 ON h.homeTeam_id = T1._id  "
				+ "group by T1.teamName" + " order by totalGame desc";

		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGames(favoriteTeamName, cursor);
		cursor.close();
		
		dbOpenHelper.close();
		return championshipStatisList;

	}
	
	public List<ChampionshipStatis> getHomeProGoals(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "	select  sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult, " +
				"T1.teamName as homeTeam from historico h INNER JOIN times " +
				"AS T1 ON h.homeTeam_id = T1._id group by T1.teamName  order by homeResult desc";

		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGoals(favoriteTeamName, cursor);
		cursor.close();
		
		dbOpenHelper.close();
		
		return championshipStatisList;

	}
	
	

	public List<ChampionshipStatis> getAwayProGoals(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "	select sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult," +
				"T1.teamName as awayTeam from historico h INNER JOIN " +
				"times AS T1 ON h.awayTeam_id = T1._id group by T1.teamName order by awayResult desc";

		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGoals(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		return championshipStatisList;

	}
	
	public List<ChampionshipStatis> getHomeCounterGoals(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "	select  sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult, " +
				"T1.teamName as homeTeam from historico h INNER JOIN times " +
				"AS T1 ON h.homeTeam_id = T1._id group by T1.teamName  order by awayResult desc";

		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGoals(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		return championshipStatisList;

	}
	
	
	public List<ChampionshipStatis> getAwayCounterGoals(Context context, String favoriteTeamName) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "	select sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult," +
				"T1.teamName as awayTeam from historico h INNER JOIN " +
				"times AS T1 ON h.awayTeam_id = T1._id group by T1.teamName order by homeResult desc";

		Cursor cursor = database.rawQuery(MY_QUERY, null);
		
		List<ChampionshipStatis> championshipStatisList = buildChampionshipListGoals(favoriteTeamName, cursor);
		cursor.close();
		dbOpenHelper.close();
		return championshipStatisList;

	}
	

	


	private List<ChampionshipStatis> buildChampionshipListGames(String favoriteTeamName, Cursor cursor) {
		List<ChampionshipStatis> championshipStatisList = new ArrayList<ChampionshipStatis>();

		cursor.moveToFirst();
		
		ChampionshipStatis championshipStatisFirst = new ChampionshipStatis();
		championshipStatisFirst.setTotalGame(cursor.getLong(0));
		championshipStatisFirst.setTeam(cursor.getString(1));
		championshipStatisFirst.setId(1l);
		championshipStatisList.add(championshipStatisFirst);
		
		long position =0;
		if (!cursor.isAfterLast()) {
			do {
				position++;
				if (StringUtils.stripAccents(cursor.getString(1).toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", "").equalsIgnoreCase(favoriteTeamName.replaceAll(" ", ""))){
					ChampionshipStatis championshipStatis = new ChampionshipStatis();
					championshipStatis.setTotalGame(cursor.getLong(0));
					championshipStatis.setTeam(cursor.getString(1));
					championshipStatis.setId(position);
					championshipStatisList.add(championshipStatis);
					break;
				}
			} while (cursor.moveToNext());
		}
		
		
		// o time do cara está em primeiro no ranking.. estou duplicando por enqto
		if (championshipStatisList.size() == 1)
			championshipStatisList.add(championshipStatisList.get(0));
		
		return championshipStatisList;
	}
	
	
	
	private List<ChampionshipStatis> buildChampionshipListGoals(String favoriteTeamName, Cursor cursor) {
		List<ChampionshipStatis> championshipStatisList = new ArrayList<ChampionshipStatis>();

		cursor.moveToFirst();
		
		ChampionshipStatis championshipStatisFirst = new ChampionshipStatis();
		championshipStatisFirst.setHomeResult(cursor.getInt(0));
		championshipStatisFirst.setAwayResult(cursor.getInt(1));
		championshipStatisFirst.setTeam(cursor.getString(2));
		championshipStatisFirst.setId(1l);
		championshipStatisList.add(championshipStatisFirst);
		
		long position =0;
		if (!cursor.isAfterLast()) {
			do {
				position++;
				if (StringUtils.stripAccents(cursor.getString(2).toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", "").equalsIgnoreCase(favoriteTeamName.replaceAll(" ", ""))){
					ChampionshipStatis championshipStatis = new ChampionshipStatis();
					championshipStatis.setHomeResult(cursor.getInt(0));
					championshipStatis.setAwayResult(cursor.getInt(1));
					championshipStatis.setTeam(cursor.getString(2));
					championshipStatis.setId(position);
					championshipStatisList.add(championshipStatis);
					break;
				}
			} while (cursor.moveToNext());
		}
		
		
		
		return championshipStatisList;
	}
	
	
}

