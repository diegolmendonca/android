package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;
import java.util.List;

import br.com.estatisticasdobrasileirao.entity.Game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RoundGamesDAO {

	public void insertGame(Context context, Game game){
		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase database = dbUtil.getReadableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("DATE", game.getGameDate());
		cv.put("HOME_TEAM", game.getHomeTeam());
		cv.put("HOME_SYMBOL_POSITION", game.getHomeSymbolId());
		cv.put("AWAY_TEAM", game.getAwayTeam());
		cv.put("AWAY_SYMBOL_POSITION", game.getAwaySymbolId());
		database.insert("round_games", null, cv);
		
		database.close();


	}


	public void deleteAll(Context context){
		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase database = dbUtil.getReadableDatabase();

		database.delete("round_games", null, null)   ;
		database.delete("games", null, null)   ;
		database.close();
	}
	

	// Getting All Games
	public List<Game> getAllGames(Context context) {
		List<Game> gameList = new ArrayList<Game>();
		// Select All Query
		String selectQuery = "SELECT  * FROM ROUND_GAMES";

		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase db = dbUtil.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Game game = new Game();
				game.setGameDate(cursor.getString(0));
				game.setHomeTeam(cursor.getString(1));
				game.setHomeSymbolId(Integer.parseInt(cursor.getString(2)));
				game.setAwayTeam(cursor.getString(3));
				game.setAwaySymbolId(Integer.parseInt(cursor.getString(4)));


				// Adding game to list
				gameList.add(game);
			} while (cursor.moveToNext());
		}
		
		cursor.close();
		db.close();

		// return contact list
		return gameList;
	}

}
