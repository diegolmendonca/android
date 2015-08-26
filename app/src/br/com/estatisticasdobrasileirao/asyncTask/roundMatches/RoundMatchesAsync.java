package br.com.estatisticasdobrasileirao.asyncTask.roundMatches;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.activity.FavoriteTeamActivity;
import br.com.estatisticasdobrasileirao.adapter.RoundMatchesListViewAdapter;
import br.com.estatisticasdobrasileirao.db.RoundGamesDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.entity.Team;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

public class RoundMatchesAsync extends AsyncTask<Void, Void, Game> {
		
    private Activity activity;
    private ProgressDialog progressDialog ;
    private View view;
    
    private ListView listView;
    RoundMatchesListViewAdapter adapter;
    

    public RoundMatchesAsync(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
        this.progressDialog = new ProgressDialog(activity);
    }


	@Override
    protected void onPreExecute() {
    	progressDialog.setMessage("Por favor, aguarde...");
    	progressDialog.setCancelable(false);
    	progressDialog.show();
    }

    @Override
    protected Game doInBackground(Void... unused) {
    	
        List<Game> currentRoundGames = new RoundGamesDAO().getAllGames(activity);
        Game game = findFavoriteTeamGame(new TeamsDAO().getFavorite(activity), currentRoundGames);   //seta o jogo destaque do time principal
        
        if (null != game) currentRoundGames.remove(game);
        adapter = new RoundMatchesListViewAdapter(activity, R.layout.fragment_round_matches_list, currentRoundGames);
    	
    	return game;  
    	
    }


	private Game findFavoriteTeamGame(Team favorite, List<Game> currentRoundGames) {
		Iterator<Game> it = currentRoundGames.iterator();
        while(it.hasNext()){
        	Game game = it.next();
        	if(game.getHomeTeam().equalsIgnoreCase(favorite.getName()) ||game.getAwayTeam().equalsIgnoreCase(favorite.getName())) {
        		activity.getIntent().putExtra("homeTeam", game.getHomeTeam());
    			activity.getIntent().putExtra("awayTeam", game.getAwayTeam());
        		
        		return game;
        	}
        }
		return null;  // vai cair aqui quando o time que o cara escolheu nao jogar na rodada
	}

    @Override
    protected void onPostExecute(Game result) {
		if (null != result) {

			listView = (ListView) view.findViewById(R.id.games_list);
			listView.setAdapter(adapter);

			UIUtils.setTextViewText(view, R.id.homeTeamName, (result.getHomeTeam()));
			UIUtils.setImageResource(view, R.id.homeSymbolRound, activity.getResources().getIdentifier(StringUtils.stripAccents(result.getHomeTeam().toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""), "drawable", activity.getPackageName()));
			UIUtils.setTextViewText(view, R.id.awayTeamName, (result.getAwayTeam()));
			UIUtils.setImageResource(view, R.id.awaySymbolRound, activity.getResources().getIdentifier(StringUtils.stripAccents(result.getAwayTeam().toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""), "drawable", activity.getPackageName()));
			UIUtils.setTextViewText(view, R.id.descricao, (result.getGameDate()));
			
			if (result.getAwayResult() != null && result.getHomeResult() != null){
				UIUtils.setTextViewText(view, R.id.gameResult, result.getHomeResult() + " x " + result.getAwayResult());
			}
			else{
				UIUtils.setTextViewText(view, R.id.gameResult,  " x " );
			}
		}
		
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Sem histórico");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setMessage("Seu time não joga nessa rodada ou não está na série A deste ano ou sua conexão de internet falhou na inicialização do aplicativo. Assegure-se de que tenha internet caso seu time esteja na série A! \n ");
			builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					activity.startActivity(new Intent(activity, FavoriteTeamActivity.class));
				}
			}); 
			final AlertDialog alert = builder.create();
			alert.show();
    	
		}   
		
		progressDialog.dismiss();
    }

}