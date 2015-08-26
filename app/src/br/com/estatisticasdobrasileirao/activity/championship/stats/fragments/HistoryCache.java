package br.com.estatisticasdobrasileirao.activity.championship.stats.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import br.com.estatisticasdobrasileirao.entity.ChampionshipStatis;

public class HistoryCache {

	public static void writeToCache(Activity activity, String fileName, ChampionshipStatis[] object) {
			activity.getIntent().putParcelableArrayListExtra(fileName, new ArrayList<ChampionshipStatis>(Arrays.asList(object)));
	}
	
	public static List<ChampionshipStatis> readFromCache(Activity activity,String fileName)  {
		List<ChampionshipStatis> jsonObject =  activity.getIntent().getParcelableArrayListExtra(fileName);
		return jsonObject;
	}
	

}
