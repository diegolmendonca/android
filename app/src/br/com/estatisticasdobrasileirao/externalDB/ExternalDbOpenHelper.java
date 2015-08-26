package br.com.estatisticasdobrasileirao.externalDB;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ExternalDbOpenHelper extends SQLiteAssetHelper {

	
	private static final String DATABASE_NAME = "static_biblia.s3db";
    private static final int DATABASE_VERSION = 4;

	public ExternalDbOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		setForcedUpgrade();
	}

}
