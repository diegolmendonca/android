package br.com.estatisticasdobrasileirao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.activity.loterias.LotecaActivity;
import br.com.estatisticasdobrasileirao.activity.loterias.LotogolActivity;
import br.com.estatisticasdobrasileirao.sliddingMenu.ActivityBase;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;

public class LoteriasActivity extends ActivityBase  {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loteria);
		addActionToButtons(R.id.button_loteca, LotecaActivity.class);
		addActionToButtons(R.id.button_lotogol, LotogolActivity.class);
		
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Loteria Esportiva - Previsões");
		

	}

	private void addActionToButtons(int buttonId, final Class<?> class1) {
		findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), class1));
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
