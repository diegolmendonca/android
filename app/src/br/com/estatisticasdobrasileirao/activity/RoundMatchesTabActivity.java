package br.com.estatisticasdobrasileirao.activity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.AwayMatchesFragment;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.CounterGoalsAwayMatchesFragment;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.CounterGoalsHomeMatchesFragment;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.GoalsAwayMatchesFragment;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.GoalsHomeMatchesFragment;
import br.com.estatisticasdobrasileirao.activity.championship.stats.fragments.HomeMatchesFragment;
import br.com.estatisticasdobrasileirao.adapter.RoundMatchesTabAdapter;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.fragment.roundMatches.RoundGoalsGraphsFragment;
import br.com.estatisticasdobrasileirao.fragment.roundMatches.RoundMatchesConfrontationFragment;
import br.com.estatisticasdobrasileirao.fragment.roundMatches.RoundMatchesFragment;
import br.com.estatisticasdobrasileirao.fragment.roundMatches.RoundMatchesGraphsFragment;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.common.collect.Lists;

public class RoundMatchesTabActivity extends SherlockFragmentActivity  implements ActionBar.TabListener {
	private ViewPager viewPager;
	private RoundMatchesTabAdapter mAdapter;
	private ActionBar actionBar;
	private Map<String, List<Fragment>> fragmentsMap = new HashMap<String, List<Fragment>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (null == new TeamsDAO().getFavorite(getBaseContext())) 
			startActivity(new Intent(getBaseContext(), FavoriteTeamActivity.class));
		else{
			setContentView(R.layout.activity_round_matches);
			
			
			populateMap();
			
	
			// Initilization
			viewPager = (ViewPager) findViewById(R.id.pager);
			actionBar = getSupportActionBar();
			
			mAdapter = new RoundMatchesTabAdapter(getSupportFragmentManager(),fragmentsMap.get(getIntent().getStringExtra("from")));
	
			viewPager.setAdapter(mAdapter);
			actionBar.setHomeButtonEnabled(true);
			actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
			actionBar.setTitle("Histórico e Jogos");
			
			
	
			// Adding Tabs
			for (String tab_name : getProperTabNames()) {
				actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
			}
	
			/**
			 * on swiping the viewpager make respective tab selected
			 * */
			viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	
				@Override
				public void onPageSelected(int position) {
					// on changing the page
					// make respected tab selected
					actionBar.setSelectedNavigationItem(position);
				}
	
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}
	
				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			});
		}
	}

	private void populateMap() {
		fragmentsMap.put("button_confronto_direto", Lists.newArrayList(new RoundMatchesFragment(),
				new RoundMatchesConfrontationFragment(), new RoundMatchesGraphsFragment(), new RoundGoalsGraphsFragment()));
		
		fragmentsMap.put("button_champ_stats", Lists.newArrayList(new HomeMatchesFragment(),
																	new AwayMatchesFragment(), 
																	new GoalsHomeMatchesFragment(),
																	new GoalsAwayMatchesFragment(),
																	new CounterGoalsHomeMatchesFragment(),
																	new CounterGoalsAwayMatchesFragment()));
		
	}

	private String[] getProperTabNames() {
		String[] stringFieldValue = null;
		try {
			Field stringField = R.array.class.getField( getIntent().getStringExtra("from"));
			int stringFieldId = stringField.getInt(null);
			stringFieldValue = getResources().getStringArray(stringFieldId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringFieldValue;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
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
