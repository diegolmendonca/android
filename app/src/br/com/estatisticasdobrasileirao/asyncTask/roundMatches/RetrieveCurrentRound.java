package br.com.estatisticasdobrasileirao.asyncTask.roundMatches;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.Header;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import br.com.estatisticasdobrasileirao.db.RoundGamesDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.http.BrasileiraoHttpClient;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RetrieveCurrentRound  {
	
	private Context context;
	private ProgressDialog progressDialog ;
	
	public RetrieveCurrentRound(Context context) {
		this.context = context;
		this.progressDialog = new ProgressDialog(context);
	}

	public void retrieveCurrentRound(){
		
		Game[] gamesCache = CurrentRoundCache.readFromCache(context, "currentRound");
		
		if (null != gamesCache && !isCacheExpired(Arrays.asList(gamesCache))) 
			buildView(gamesCache);
		
		else{
		
		BrasileiraoHttpClient.get(buildServerUrl(), null, new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				progressDialog.setMessage("Por favor, aguarde");
				progressDialog.setCancelable(true);
                progressDialog.show();
			}

			@Override
			public void onSuccess(String result) {
				Game[] games = new Gson().fromJson(result, Game[].class);
				CurrentRoundCache.writeToCache(context, "currentRound", result);
				
				buildView(games);
			}
			
			 @Override
		     public void onRetry() {
		         super.onRetry();
		     }

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				
				for (int i=0; i < 2; i++)
				{
					Toast.makeText(
							context,
							"Problemas com a conexão de internet.Não foi possível buscar a rodada da semana. Os dados da rodada da semana podem estar desatualizados e/ou errados. " +
							"Tente novamente quando tiver com conexão de internet para ter uma navegação normal",
							Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onFinish() {
				progressDialog.dismiss();
			}

		});
		
	}
		
	}
	
	private boolean isCacheExpired(List<Game> gamesCache) {
		if (CollectionUtils.isEmpty(gamesCache)) return false;
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
		DateTime dateTimeFromLastTimeItWentToServer = formatter.parseDateTime(gamesCache.get(gamesCache.size()-1).getGameDate());
		return new DateTime().isAfter((dateTimeFromLastTimeItWentToServer.plusDays(1)));
	}
	
	
	private void buildView(Game[] games) {
		RoundGamesDAO rgDAO = new RoundGamesDAO();
    	rgDAO.deleteAll(context);
    	Map<String, String> teams = new TeamsDAO().getAllTeams(context);
    	for(Game g: games){
    		//busca simbolo do time
        	String symbolName = teams.get(g.getHomeTeam());
        	if(symbolName == null){
        		symbolName ="cu"; //simbolo default
        	}
        	int h = context.getResources().getIdentifier(symbolName, "drawable", context.getPackageName());
        	String awaysymbolName = teams.get(g.getAwayTeam());
        	if(awaysymbolName == null){
        		awaysymbolName ="cu"; //simbolo default
        	}
        	int a = context.getResources().getIdentifier(awaysymbolName, "drawable", context.getPackageName());
        	
        	g.setHomeSymbolId(h);
        	g.setAwaySymbolId(a);
    		rgDAO.insertGame(context, g);
		
    	}
	}
	
	private String buildServerUrl() {
		StringBuilder urlBuider = new StringBuilder();
		urlBuider.append("currentRound");
		return urlBuider.toString();
	}

}