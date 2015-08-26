package br.com.estatisticasdobrasileirao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "brasileirao.db";
	private static final int DATABASE_VERSION = 18;
    private static final String SQL_GAMES_TABLE ="CREATE TABLE IF NOT EXISTS GAMES (YEAR INTEGER, HOME_TEAM VARCHAR, HOME_GOALS VARCHAR, AWAY_TEAM VARCHAR, AWAY_GOALS VARCHAR , GAME_DATE VARCHAR )";
    private static final String SQL_TEAMS_TABLE ="CREATE TABLE IF NOT EXISTS TEAMS (NAME VARCHAR, SYMBOL_POSITION VARCHAR , FAVORITE INTEGER)";
    private static final String SQL_ROUND_GAMES_TABLE ="CREATE TABLE IF NOT EXISTS ROUND_GAMES (DATE VARCHAR, HOME_TEAM VARCHAR, HOME_SYMBOL_POSITION VARCHAR, AWAY_TEAM VARCHAR, AWAY_SYMBOL_POSITION VARCHAR )";
    		
    public DBUtil (Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    
    @Override
    public void onCreate(SQLiteDatabase db){
    	db.execSQL(SQL_GAMES_TABLE);
    	db.execSQL(SQL_TEAMS_TABLE);
    	db.execSQL(SQL_ROUND_GAMES_TABLE);
//    	GamesDAO gDao = new GamesDAO();
//    	gDao.initAllGames(db);
    	TeamsDAO tDao = new TeamsDAO();
    	tDao.initAllTeams(db);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    	db.execSQL("DROP TABLE IF EXISTS GAMES" );
    	db.execSQL("DROP TABLE IF EXISTS TEAMS" );
    	db.execSQL("DROP TABLE IF EXISTS ROUND_GAMES" );
    	db.execSQL("DROP TABLE IF EXISTS HISTORICO" );
    	onCreate(db);
    }
}
    