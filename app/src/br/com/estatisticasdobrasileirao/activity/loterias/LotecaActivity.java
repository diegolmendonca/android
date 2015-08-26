package br.com.estatisticasdobrasileirao.activity.loterias;

import java.util.List;

import org.apache.http.Header;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.Loteca;
import br.com.estatisticasdobrasileirao.entity.LotecaResults;
import br.com.estatisticasdobrasileirao.http.BrasileiraoHttpClient;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class LotecaActivity extends SherlockFragmentActivity {
	private AdView mAdView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loteca);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		
		mAdView = (AdView) findViewById(R.id.adViewLoteca);
		mAdView.loadAd(new AdRequest.Builder().build());

		final ProgressDialog progressDialog = new ProgressDialog(this);

		Loteca cache = LotecaCache.readFromCache(getBaseContext(), "loteca");

		if (null != cache && !isCacheExpired(cache)) {
			actionBar.setTitle("Loteca #" + cache.getSorteioNumber() + " DATA:" + cache.getSorteioDate());
			buildView(cache.getLotecaResults());
		}
		else 
			getLotecaFromServer(actionBar, progressDialog);
	}

	private boolean isCacheExpired(Loteca cache) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dateTimeFromLastTimeItWentToServer = formatter.parseDateTime(cache.getSorteioDate());
		return new DateTime().isAfter((dateTimeFromLastTimeItWentToServer.plusDays(1)));
	}

	private void getLotecaFromServer(final ActionBar actionBar, final ProgressDialog progressDialog) {
		BrasileiraoHttpClient.get(buildServerUrl(), null, new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {

				progressDialog.setMessage("Por favor, aguarde");
				progressDialog.setCancelable(false);
				progressDialog.show();
			}

			@Override
			public void onSuccess(String result) {

				Loteca loteca = new Gson().fromJson(result, Loteca.class);
				buildView(loteca.getLotecaResults());

				actionBar.setTitle("Loteca #" + loteca.getSorteioNumber() + " DATA:" + loteca.getSorteioDate());
				LotecaCache.writeToCache(getBaseContext(), "loteca", result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				
				for (int i=0; i < 2; i++)
				{
					Toast.makeText(
							getBaseContext(),
							"Problemas com a conexão de internet.Não foi possível buscar a loteca da semana. " +
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

	private String buildServerUrl() {
		StringBuilder urlBuider = new StringBuilder();
		urlBuider.append("loteca");
		return urlBuider.toString();
	}

	private void buildView(final List<LotecaResults> lotecaResults) {

		ListView listView = (ListView) findViewById(R.id.loteca_game_list);
		LotecaListViewAdapter adapter = new LotecaListViewAdapter(getBaseContext(), R.layout.loteca_list, lotecaResults);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(getBaseContext(), LotecaMatchesFragment.class);
				intent.putExtra("lotecaResults", lotecaResults.get(position));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

}
