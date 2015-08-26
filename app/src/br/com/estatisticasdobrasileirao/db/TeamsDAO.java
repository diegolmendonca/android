package br.com.estatisticasdobrasileirao.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.estatisticasdobrasileirao.entity.Team;

public class TeamsDAO {
	
	

	
	
	public void initAllTeams(SQLiteDatabase database){
		
		for(String team : teamList){
			ContentValues cv = new ContentValues();
			cv.put("NAME", WordUtils.capitalizeFully(team.toLowerCase().trim()));
			cv.put("SYMBOL_POSITION", StringUtils.stripAccents(team.toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""));
			cv.put("FAVORITE", "0");
			database.insert("teams", null, cv);
			
			
		}
		
	}
	
	
	public void updateFavorite(Context context, String teamName){
		DBUtil dbUtil = new DBUtil(context);
		SQLiteDatabase db = dbUtil.getReadableDatabase();
		
		ContentValues args = new ContentValues();
		args.put("FAVORITE", "0");
		db.update("teams", args, null, null);
		
		args = new ContentValues();
		args.put("FAVORITE", "1");
		String strFilter = "NAME = ?" ;
		db.update("teams", args, strFilter, new String[]{teamName});
		db.close();
	
	}

	
	// Getting All Games
    public Map<String, String> getAllTeams(Context context) {
        Map<String, String> result = new HashMap<String,String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM TEAMS";

        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	result.put(cursor.getString(0), cursor.getString(1));

            } while (cursor.moveToNext());
        }
        
        
        cursor.close();
        db.close();

        // return contact list
        return result;
    }
    
    
    // Getting All Games
    public List<Team> getAllTeamsList(Context context) {
    	List<Team> result = new ArrayList<Team>();
        // Select All Query
        String selectQuery = "SELECT  * FROM TEAMS";

        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Team team = new Team();
            	team.setName(cursor.getString(0));
            	team.setSymbolResource(cursor.getString(1));
            	team.setFavorite(cursor.getInt(2));
            	result.add(team);

            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();

        // return contact list
        return result;
    }



	// Getting Team
    public Team getTeamByName(Context context, String name) {
    	Team result = null;

        String[] tableColumns = new String[] {"*"};
        String whereClause = "NAME = ? ";
        String[] whereArgs = new String[]{ name };
        		
        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.query("teams", tableColumns, whereClause, whereArgs,  null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	result = new Team();
            	result.setName(cursor.getString(0));
            	result.setSymbolResource(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        // return contact list
        return result;
    }
    
    
 // Getting Team
    public Team getFavorite(Context context) {
    	Team result = null;

        String[] tableColumns = new String[] {"*"};
        String whereClause = "FAVORITE = 1 ";
        String[] whereArgs = new String[]{ };
        		
        DBUtil dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.query("teams", tableColumns, whereClause, whereArgs,  null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	result = new Team();
            	result.setName(cursor.getString(0));
            	result.setSymbolResource(cursor.getString(1));
            	result.setSymbolId(cursor.getInt(0));
            	result.setFavorite(cursor.getInt(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
    
    
    private static final List<String> teamList = Collections.unmodifiableList(
    	    new ArrayList<String>() {{
    	    	add("ALECRIM");
    	    	add("AM�RICA-MG");
    	    	add("AMERICANO");
    	    	add("AM�RICA-RJ");
    	    	add("AM�RICA-RN");
    	    	add("AM�RICA-SP");
    	    	add("ANAPOLINA");
    	    	add("ASA");
    	    	add("ATL�TICO-GO");
    	    	add("ATL�TICO-MG");
    	    	add("ATL�TICO-PR");
    	    	add("AUTO ESPORTE");
    	    	add("AVA�");
    	    	add("BAHIA");
    	    	add("BANGU");
    	    	add("BARUERI");
    	    	add("BOTAFOGO");
    	    	add("BOTAFOGO-PB");
    	    	add("BOTAFOGO-SP");
    	    	add("BRAGANTINO");
    	    	add("BRASIL");
    	    	add("BRAS�LIA");
    	    	add("BRASILIENSE");
    	    	add("CALDENSE");
    	    	add("CAMPINENSE");
    	    	add("CAMPO GRANDE");
    	    	add("CATUENSE");
    	    	add("CAXIAS");
    	    	add("CEAR�");
    	    	add("CENTRAL");
    	    	add("CEUB");
    	    	add("CHAPECOENSE");
    	    	add("COLATINA");
    	    	add("COLORADO");
    	    	add("COMERCIAL-MS");
    	    	add("COMERCIAL-SP");
    	    	add("CONFIAN�A");
    	    	add("CORINTHIANS");
    	    	add("CORITIBA");
    	    	add("CORUMBAENSE");
    	    	add("CRB");
    	    	add("CRICI�MA");
    	    	add("CRUZEIRO");
    	    	add("CSA");
    	    	add("DESPORTIVA");
    	    	add("DOM BOSCO");
    	    	add("FAST");
    	    	add("FERROVI�RIA");
    	    	add("FERROVI�RIO");
    	    	add("FIGUEIRENSE");
    	    	add("FLAMENGO");
    	    	add("FLAMENGO-PI");
    	    	add("FLUMINENSE");
    	    	add("FLUMINENSE-BA");
    	    	add("FORTALEZA");
    	    	add("FRANCANA");
    	    	add("GAL�CIA");
    	    	add("GAMA");
    	    	add("GOI�NIA");
    	    	add("GOI�S");
    	    	add("GOYTACAZ");
    	    	add("GR�MIO");
    	    	add("GR�MIO MARING�");
    	    	add("GR�MIO PRUDENTE");
    	    	add("GUAR�");
    	    	add("GUARANI");
    	    	add("INTERNACIONAL");
    	    	add("INTERNACIONAL-SM");
    	    	add("INTERNACIONAL-SP");
    	    	add("IPATINGA");
    	    	add("ITABAIANA");
    	    	add("ITABUNA");
    	    	add("ITUMBIARA");
    	    	add("JOINVILLE");
    	    	add("JUVENTUDE");
    	    	add("JUVENTUS");
    	    	add("LE�NICO");
    	    	add("LONDRINA");
    	    	add("MALUTROM");
    	    	add("MARANH�O");
    	    	add("MARING�");
    	    	add("MIXTO");
    	    	add("MOTO CLUB");
    	    	add("NACIONAL");
    	    	add("N�UTICO");
    	    	add("NOROESTE");
    	    	add("NOVO HAMBURGO");
    	    	add("OLARIA");
    	    	add("OPER�RIO-MS");
    	    	add("OPER�RIO-MT");
    	    	add("OPER�RIO-PR");
    	    	add("PALMEIRAS");
    	    	add("PARAN�");
    	    	add("PAYSANDU");
    	    	add("PIAU�");
    	    	add("PINHEIROS");
    	    	add("PONTE PRETA");
    	    	add("PORTUGUESA");
    	    	add("POTIGUAR");
    	    	add("REMO");
    	    	add("RIO BRANCO");
    	    	add("RIO NEGRO");
    	    	add("R�VER");
    	    	add("SAMPAIO CORR�A");
    	    	add("SANTA CRUZ");
    	    	add("SANTO ANDR�");
    	    	add("SANTOS");
    	    	add("S�O BENTO");
    	    	add("S�O CAETANO");
    	    	add("S�O JOS�");
    	    	add("S�O PAULO");
    	    	add("S�O PAULO-RS");
    	    	add("SERGIPE");
    	    	add("SOBRADINHO");
    	    	add("SPORT");
    	    	add("TAGUATINGA");
    	    	add("TIRADENTES");
    	    	add("TREZE");
    	    	add("TUNA LUSO");
    	    	add("UBERABA");
    	    	add("UBERL�NDIA");
    	    	add("UNI�O S�O JO�O");
    	    	add("VASCO");
    	    	add("VILA NOVA-GO");
    	    	add("VILLA NOVA-MG");
    	    	add("VIT�RIA-BA");
    	    	add("VIT�RIA-ES");
    	    	add("VOLTA REDONDA");
    	    	add("XV DE JA�");
    	    	add("XV DE PIRACICABA");
    	    }});
	
	

}
