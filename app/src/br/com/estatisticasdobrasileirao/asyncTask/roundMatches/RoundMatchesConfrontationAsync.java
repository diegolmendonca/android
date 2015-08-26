package br.com.estatisticasdobrasileirao.asyncTask.roundMatches;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.RoundMatchesListViewAdapter;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.utils.roundMatches.RoundMatchesUtils;

public class RoundMatchesConfrontationAsync extends AsyncTask<Void, Void, List<Game>> {

	private Activity activity;
	private ProgressDialog progressDialog;
	private View view;

	private ListView listView;
	RoundMatchesListViewAdapter adapter;

	public RoundMatchesConfrontationAsync(Activity activity, View view) {
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
	protected List<Game> doInBackground(Void... unused) {

		if (null == activity.getIntent().getExtras().getString("homeTeam") || null == activity.getIntent().getExtras().getString("awayTeam"))
			return null;
		List<Game> farvoriteHistoryGames = RoundMatchesUtils.getHistoryFromFavorite(activity.getBaseContext(),
				activity.getIntent().getExtras().getString("homeTeam"), activity.getIntent().getExtras().getString("awayTeam"));
		

		listView = (ListView) view.findViewById(R.id.services_history_list);
		adapter = new RoundMatchesListViewAdapter(activity, R.layout.fragment_round_matches_list, farvoriteHistoryGames);

		return farvoriteHistoryGames;

	}

	@Override
	protected void onPostExecute(List<Game> results) {
		if (!CollectionUtils.isEmpty(results))
			listView.setAdapter(adapter);
		else {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Não há histórico para esse jogo");
			builder.setCancelable(true);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setMessage("Este jogo é inédito, nunca foi disputado em campeonatos brasileiros, desde 1971 ou sua conexão de internet falhou na inicialização do aplicativo. Assegure-se de que tenha internet caso seu time esteja na série A");
			builder.setPositiveButton("OK",null);
			final AlertDialog alert = builder.create();
			alert.show();
			
		}
		progressDialog.dismiss();
	}

}