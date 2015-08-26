package br.com.estatisticasdobrasileirao.activity.loterias;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.LotecaResults;

public class LotecaGraphsGeneratorAsync extends AsyncTask<Void, Void, Void> {
	
	private Activity activity;
	
	private  int[] colors ;  
	private  String[] title;  
	private  String source;
	private LotecaResults result;
	
	private DefaultRenderer renderer;
	private CategorySeries mSeries;
	private static String START_YEAR = "1971";
	private static String END_YEAR = "2013";
	
	
    public LotecaGraphsGeneratorAsync(Activity activity , int[] colors, String[] title, LotecaResults result, String source) {
        this.activity = activity;
        this.colors = colors;
        this.title = title;
        this.result = result;
        this.source = source;
    }

	@Override
	protected Void doInBackground(Void... params) {

		renderer = buildRenderer();
		mSeries = new CategorySeries("");
		
		double[] results = null;
		int wins = Integer.parseInt(result.getWins());
		int draws = Integer.parseInt(result.getDraws());
		int losses = Integer.parseInt(result.getLosses());
		
		if (source.equalsIgnoreCase("games"))
			results = new double[] {wins,draws, losses};
		
		else {
			
			
			double totalGames = wins + draws + losses;
			double averageGoalsMade =(double) Math.round(Double.parseDouble(result.getGoalsMade()) / totalGames * 100) / 100;
			double averageGoalsTaken = (double)Math.round(Double.parseDouble(result.getGoalsTaken()) / totalGames * 100) / 100;
			results = new double[] {averageGoalsMade,averageGoalsTaken};
			
		}

		for (int i = 0; i < results.length; i++) {
			mSeries.add(title[i] + " " + results[i], results[i]);
			renderer.addSeriesRenderer(buildSeriesRenderer(i));
		}
		
		
//		
		
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
	
	@Override
	protected void onPostExecute(Void unused) {
		LinearLayout layout = null;
		
		if (source.equalsIgnoreCase("games"))  layout = (LinearLayout) activity.findViewById(R.id.chart);
		else  layout = (LinearLayout) activity.findViewById(R.id.chart_goals);
		
		if(layout != null){
			layout.removeAllViews();
			layout.addView(ChartFactory.getPieChartView(activity.getBaseContext(), mSeries, renderer));
		}
	}

}
