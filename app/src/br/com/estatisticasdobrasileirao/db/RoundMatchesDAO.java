package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.externalDB.ExternalDbOpenHelper;

public class RoundMatchesDAO {
	
	private SQLiteDatabase database;

	public List<Game> getRoundGamesStatis(Context context, String homeTeam, String awayTeam) {

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context);
		database = dbOpenHelper.getReadableDatabase();

		String MY_QUERY = "select  h.*, T1.teamName as homeTeam, T2.teamName as awayTeam " +
				"from historico h " +
				"INNER JOIN times AS T1 ON h.homeTeam_id = T1._id  " +
				"INNER JOIN times AS T2 ON h.awayTeam_id = T2._id " +
				" where  T1.teamName = ? and T2.teamName = ? order by h._id";
		
		Cursor cursor = database.rawQuery(MY_QUERY, new String[]{homeTeam.toUpperCase(), awayTeam.toUpperCase()});
		
		List<Game> gameList = buildGameList(cursor);
		cursor.close();
		dbOpenHelper.close();
		return gameList;

	}

	private List<Game> buildGameList(Cursor cursor) {
		List<Game>  games = new ArrayList<Game>();
		
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast()) {
			do {
				Game game = new Game();
				game.setHomeResult(cursor.getString(2));
				game.setAwayResult(cursor.getString(3));
				game.setGameYear(cursor.getString(5));
				game.setGameDate(cursor.getString(6));
				game.setGameDay(cursor.getString(7));
				game.setHomeTeam(cursor.getString(8));
				game.setAwayTeam(cursor.getString(9));
				games.add(game);
				
			} while (cursor.moveToNext());
		}
		
		return games;
	}

}
