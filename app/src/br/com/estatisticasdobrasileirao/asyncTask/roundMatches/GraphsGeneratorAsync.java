package br.com.estatisticasdobrasileirao.asyncTask.roundMatches;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Game;
import br.com.estatisticasdobrasileirao.entity.Team;
import br.com.estatisticasdobrasileirao.utils.roundMatches.RoundMatchesUtils;

public class GraphsGeneratorAsync extends AsyncTask<Void, Void, Void> {
	
	private Activity activity;
	private Context context;
	
	private  int[] colors ;  
	private  String[] title;  
	private  String source;
	
	private DefaultRenderer renderer;
	private CategorySeries mSeries;
	private static String START_YEAR = "1971";
	private static String END_YEAR = "2013";
	
	
    public GraphsGeneratorAsync(Activity activity, Context context , int[] colors, String[] title, String source) {
        this.activity = activity;
        this.context = context;
        this.colors = colors;
        this.title = title;
        this.source = source;
    }

	@Override
	protected Void doInBackground(Void... params) {

		renderer = buildRenderer();
		mSeries = new CategorySeries("");
		int[] results;

		if (null == activity.getIntent().getExtras().getString("homeTeam") || null == activity.getIntent().getExtras().getString("awayTeam")) {
			if (source.equalsIgnoreCase("games")) {
				results = new int[] { 0, 0, 0 };
			} else
				results = new int[] { 0, 0 };
		} else
			results = getGamesData(
					RoundMatchesUtils.getHistoryFromFavorite(activity.getBaseContext(), activity.getIntent().getExtras().getString("homeTeam"), activity
							.getIntent().getExtras().getString("awayTeam")), new TeamsDAO().getFavorite(activity));

		if (null == results)
			return null;

		for (int i = 0; i < results.length; i++) {
			mSeries.add(title[i] + " " + results[i], results[i]);
			renderer.addSeriesRenderer(buildSeriesRenderer(i));
		}
		return null;

	}

	private SimpleSeriesRenderer buildSeriesRenderer(int index) {
		SimpleSeriesRenderer simpleRenderer = new SimpleSeriesRenderer();
		simpleRenderer.setColor(colors[index]);
		return simpleRenderer;
	}

	private DefaultRenderer buildRenderer() {
		DefaultRenderer renderer = new DefaultRenderer();
		
		renderer.setApplyBackgroundColor(true);  
		renderer.setBackgroundColor(Color.argb(100, 50, 50, 50));  
		renderer.setChartTitleTextSize(40);
		renderer.setChartTitle("Período entre " + START_YEAR + " e " + END_YEAR);
		renderer.setShowLabels(false);
		renderer.setInScroll(true);
		renderer.setLegendTextSize(30);  
		renderer.setZoomButtonsVisible(true);  
		renderer.setStartAngle(90);  
		renderer.setClickEnabled(true);  
		renderer.setSelectableBuffer(10);  
		return renderer;
	}

	private int[] getGamesData(List<Game> games, Team team) {
		if (CollectionUtils.isEmpty(games)) return null;

		Integer wins = 0;
		Integer draws = 0;
		Integer losses = 0;
		Integer goalsMade = 0;
		Integer goalsTaken = 0;
		
		for (Game g : games) {

			if (Integer.parseInt(g.getHomeResult()) > Integer.parseInt(g.getAwayResult())) {
				wins++;
			} else if (Integer.parseInt(g.getHomeResult()) == Integer.parseInt(g.getAwayResult())) {
				draws++;
			} else {
				losses++;
			}
			
			goalsMade += Integer.parseInt(g.getHomeResult());
			goalsTaken += Integer.parseInt(g.getAwayResult());

		}
		
		
		String homeTeamName = StringUtils.stripAccents(games.get(0).getHomeTeam().toLowerCase().trim()).replaceAll("-", "");
		String favoriteTeamName = StringUtils.stripAccents(team.getName().toLowerCase().trim()).replaceAll("-", "");
		
		
		if (homeTeamName.equalsIgnoreCase(favoriteTeamName))
			return source.equalsIgnoreCase("games") ? new int[] { wins, draws, losses } :new int[] { goalsMade, goalsTaken };
			
			
		 return source.equalsIgnoreCase("games") ?	new int[] { losses, draws, wins } : new int[] { goalsTaken, goalsMade };
		
		
	}
	
	@Override
	protected void onPostExecute(Void unused) {
		LinearLayout layout = null;
		
		if (source.equalsIgnoreCase("games"))  layout = (LinearLayout) activity.findViewById(R.id.chart);
		else  layout = (LinearLayout) activity.findViewById(R.id.chart_goals);
		
		if(layout != null){
			layout.removeAllViews();
			layout.addView(ChartFactory.getPieChartView(context, mSeries, renderer));
		}
	}
	


}
