package br.com.estatisticasdobrasileirao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.fragment.statistics.HomeStatisticPerYearFragment;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;

public class StatisticsPerYearActivity extends SherlockFragmentActivity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics_per_year_home);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Estats. por ano");
		
		
		if (null == new TeamsDAO().getFavorite(getBaseContext())) 
			startActivity(new Intent(getBaseContext(), FavoriteTeamActivity.class));
		else
			openFirstStatisticPerYearFragment();
	
}
	
	
	private void openFirstStatisticPerYearFragment(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_statistics_per_year_home, new HomeStatisticPerYearFragment(),"homeStatisticPerYearFragment");
		transaction.commit();
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