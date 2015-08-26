package br.com.estatisticasdobrasileirao.classification;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.Header;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.ClassificationListViewAdapter;
import br.com.estatisticasdobrasileirao.entity.Classification;
import br.com.estatisticasdobrasileirao.http.BrasileiraoHttpClient;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ClassificationActivity extends SherlockFragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classification);
		
		final ProgressDialog progressDialog = new ProgressDialog(this);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Classificão 2014");
		
		
		Classification[] classificationCache = ClassificationCache.readFromCache(getBaseContext(),"classification");

		if(null != classificationCache && !isCacheExpired(Arrays.asList(classificationCache))) 
			buildView(Arrays.asList(classificationCache));
			
		else{

		BrasileiraoHttpClient.get(buildServerUrl(), null, new AsyncHttpResponseHandler() {
			
			@Override
			public void onStart() {
				
				progressDialog.setMessage("Por favor, aguarde");
				progressDialog.setCancelable(false);
                progressDialog.show();
			}

			@Override
			public void onSuccess(String result) {

				Classification[] classification = new Gson().fromJson(result, Classification[].class);
				buildView(Arrays.asList(classification));
				ClassificationCache.writeToCache(getBaseContext(), "classification" , result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				for (int i=0; i < 2; i++)
				{
					Toast.makeText(
							getBaseContext(),
							"Problemas com a conexão de internet.Não foi possível buscar a classificacao atual. " +
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
		
	private boolean isCacheExpired(List<Classification> classificationList) {
		if (CollectionUtils.isEmpty(classificationList)) return false;

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dateTimeFromLastTimeItWentToServer = formatter.parseDateTime(classificationList.get(0).getRodadaDate());
		return new DateTime().isAfter((dateTimeFromLastTimeItWentToServer.plusDays(1)));
	}

	private void buildView(List<Classification> classification) {
		ListView listView = (ListView) findViewById(R.id.classification_list);
		ClassificationListViewAdapter adapter = new ClassificationListViewAdapter(getBaseContext(), R.layout.classification_adapter_list, classification);
		listView.setAdapter(adapter);
	}

	private String buildServerUrl() {
		StringBuilder urlBuider = new StringBuilder();
		urlBuider.append("classificacao");
		return urlBuider.toString();

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

}
