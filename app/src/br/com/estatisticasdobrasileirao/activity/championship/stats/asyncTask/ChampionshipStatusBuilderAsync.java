package br.com.estatisticasdobrasileirao.activity.championship.stats.asyncTask;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.ChampStatisAdapter;
import br.com.estatisticasdobrasileirao.db.HistoryDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.ChampionshipStatis;
import br.com.estatisticasdobrasileirao.entity.Team;

public class ChampionshipStatusBuilderAsync extends AsyncTask<Void, Void, List<ChampionshipStatis>> {

	private Activity activity;
	private ProgressDialog progressDialog;

	private ListView listView;
	ChampStatisAdapter champStatisAdapter;

	public ChampionshipStatusBuilderAsync(Activity activity) {
		this.activity = activity;
		this.progressDialog = new ProgressDialog(activity);
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Por favor, aguarde...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	@Override
	protected List<ChampionshipStatis> doInBackground(Void... unused) {
	//	ChampionshipStatis[] gameStatis = new Gson().fromJson(result, ChampionshipStatis[].class);
		
		final Team favorite = new TeamsDAO().getFavorite(activity);
		List<ChampionshipStatis> resultList = new HistoryDAO().getAwayMatches(activity, StringUtils.stripAccents(favorite.getName().toLowerCase().trim())
				.replaceAll("-", ""));

		listView = (ListView) activity.findViewById(R.id.services_history_list);

		return resultList;
	}

	@Override
	protected void onPostExecute(List<ChampionshipStatis> gameStatis) {
		listView = (ListView) activity.findViewById(R.id.services_history_list);
			((TextView) activity.findViewById(R.id.titledsfsd)).setText("Número de jogos em casa, de 1971 a 2013");
			champStatisAdapter = new ChampStatisAdapter(activity.getBaseContext(), R.layout.fragment_round_matches_list, gameStatis, "away");
			listView.setAdapter(champStatisAdapter);

			progressDialog.dismiss();

		}

}