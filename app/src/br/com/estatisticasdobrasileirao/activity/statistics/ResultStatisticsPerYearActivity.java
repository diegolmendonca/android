package br.com.estatisticasdobrasileirao.activity.statistics;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.entity.Statistics;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;


public class ResultStatisticsPerYearActivity extends SherlockActivity {

	Statistics statistics = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_round_matches_statistics);
		statistics = (Statistics) getIntent().getParcelableExtra("statistics");
		
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Resultados ano a ano");
		
		
		UIUtils.setTextViewText(ResultStatisticsPerYearActivity.this, R.id.victories, ("Vitórias: " + statistics.getWins()));
		UIUtils.setTextViewText(ResultStatisticsPerYearActivity.this, R.id.draws, ("Empates: " + statistics.getDraws()));
		UIUtils.setTextViewText(ResultStatisticsPerYearActivity.this, R.id.defeats, ("Derrotas: " + statistics.getLosses()));
		UIUtils.setTextViewText(ResultStatisticsPerYearActivity.this, R.id.goals_pro, ("Gols Pró: " + statistics.getGoalsMade()));
		UIUtils.setTextViewText(ResultStatisticsPerYearActivity.this, R.id.goals_counter, ("Gols Contra: " + statistics.getGoalsTaken()));
		
		
		defineClickEventAction(R.id.linear1,statistics.getWinGames());   // botao de vitorias
		defineClickEventAction(R.id.linear2,statistics.getDrawGames());   // botao de empates
		defineClickEventAction(R.id.linear3,statistics.getLossGames());   // botao de derrotas
		
	}
	
	
	private void defineClickEventAction(int imageViewId, final ArrayList<Game> games) {
		findViewById(imageViewId).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), DetailedResultsStatisticsPerYearActivity.class);
				intent.putParcelableArrayListExtra("games", games);
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

}
