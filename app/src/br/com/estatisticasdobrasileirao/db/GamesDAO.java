package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.estatisticasdobrasileirao.entity.Game;

public class GamesDAO {
	
	public void insertGame(Context context, Game game){
		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase database = dbUtil.getReadableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put("YEAR", game.getGameYear());
		cv.put("HOME_TEAM", game.getHomeTeam());
		cv.put("HOME_GOALS", game.getHomeResult());
		cv.put("AWAY_TEAM", game.getAwayTeam());
		cv.put("AWAY_GOALS",game.getAwayResult());
		cv.put("GAME_DATE",game.getGameDate());
		database.insert("games", null, cv);
	}
	
	
	public void deleteAll(Context context){
		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase database = dbUtil.getReadableDatabase();

		database.delete("games", null, null)   ;
		
		database.close();
	}
	
	// Getting All Games
    public List<Game> getAllGames(Context context) {
        List<Game> gameList = new ArrayList<Game>();
        // Select All Query
        String selectQuery = "SELECT  * FROM GAMES";

        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Game game = new Game();
            	game.setYear(Integer.parseInt(cursor.getString(0)));
            	game.setHomeTeam(cursor.getString(1));
            	game.setHomeResult(cursor.getString(2));
            	game.setAwayTeam(cursor.getString(3));
            	game.setAwayResult(cursor.getString(4));
            	game.setGameDate(cursor.getString(5));
            
                // Adding game to list
                gameList.add(game);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        // return contact list
        return gameList;
    }
    
    
 // Getting All Games By Year
    public List<Game> getAllGamesByYear(Context context, String startYear, String endYear) {
        List<Game> gameList = new ArrayList<Game>();
        
        String[] whereArgs = new String[] { startYear, endYear};
        

        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase database = dbUtil.getReadableDatabase();
        
        
        Cursor cursor = database.query("GAMES", new String[] { "HOME_GOALS", "AWAY_GOALS", "year" }, "year between ? and ?", whereArgs, null, null, null);
      	cursor.moveToFirst();
      	
      	while (!cursor.isAfterLast()) {
      		gameList.add(new Game(cursor.getString(0), cursor.getString(1), cursor.getInt(2)));
			cursor.moveToNext();
		}
      	
      	cursor.close();
      	database.close();
      	
        return gameList;
    }

}
