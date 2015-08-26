package br.com.estatisticasdobrasileirao.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.asyncTask.roundMatches.RetrieveCurrentRound;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.dialog.AboutDialog;
import br.com.estatisticasdobrasileirao.entity.Team;
import br.com.estatisticasdobrasileirao.listener.TeamChooserListener;
import br.com.estatisticasdobrasileirao.sliddingMenu.ActivityBase;
import br.com.estatisticasdobrasileirao.sliddingMenu.SlidingMenuBuilderConcrete;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FavoriteTeamActivity extends ActivityBase {
	TeamsDAO teamDAO = new TeamsDAO();
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setLogo(R.drawable.ic_drawer);
		
		new RetrieveCurrentRound(this).retrieveCurrentRound(); // pega rodada
																// atual em
																// background
		
		
		
		setContentView(R.layout.activity_favorite_team);
		
		mAdView = (AdView) findViewById(R.id.adViewFavorite);
		mAdView.loadAd(new AdRequest.Builder().build());

		Team favoriteTeam = teamDAO.getFavorite(getBaseContext());
		if (null != favoriteTeam) updateFavoriteTeam(favoriteTeam);

		findViewById(R.id.button_change_team).setOnClickListener(new TeamChooserListener(this, getAllTeams()));
	}

	private List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<Team>();
		for (Team team : teamDAO.getAllTeamsList(getBaseContext())) {
			updateSymbolId(team);
			teams.add(team);
		}
		return teams;
	}

	private void updateFavoriteTeam(Team favorite) {
		updateSymbolId(favorite);
		UIUtils.setImageResource(this, R.id.teamSymbol, favorite.getSymbolId());
		UIUtils.setTextViewText(this, R.id.team, favorite.getName());
		
	}

	private void updateSymbolId(Team favorite) {
		favorite.setSymbolId(getResources().getIdentifier(favorite.getSymbolResource(), "drawable", getPackageName()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.about) {
			AboutDialog about = new AboutDialog(this);
			about.setCanceledOnTouchOutside(true);
			about.setTitle("Sobre");
			about.show();
			return true;
		} else 
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
	
	@Override
	public Class<?> setSlidingMenu() {
		// Each activity can have it's own sliding menu controlling builder
		// class.
		return SlidingMenuBuilderConcrete.class;
	}
	
	@Override
	public boolean enableHomeIconActionSlidingMenu() {
		return true;
	}

}
