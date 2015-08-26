package br.com.estatisticasdobrasileirao.activity.statistics;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.RoundMatchesListViewAdapter;
import br.com.estatisticasdobrasileirao.entity.Game;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.components.Line;

public class DetailedResultsStatisticsPerYearActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_round_matches);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#127826")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Resultados detalhados");
		
		((LinearLayout) findViewById(R.id.service_history_circles)).setVisibility(View.GONE);
		((Line) findViewById(R.id.line1)).setVisibility(View.GONE);
		((Line) findViewById(R.id.line2)).setVisibility(View.GONE);
		
		ListView a = ((ListView) findViewById(R.id.games_list));
		
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		a.setLayoutParams(layoutParams);
		
		
		ArrayList<Game> games =  getIntent().getParcelableArrayListExtra("games");

		RoundMatchesListViewAdapter adapter = new RoundMatchesListViewAdapter(getBaseContext(), R.layout.fragment_round_matches_list, games);
		a.setAdapter(adapter);

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
