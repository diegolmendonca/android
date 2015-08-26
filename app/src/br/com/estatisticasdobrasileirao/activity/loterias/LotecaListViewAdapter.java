package br.com.estatisticasdobrasileirao.activity.loterias;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.LotecaResults;

public class LotecaListViewAdapter extends ArrayAdapter<LotecaResults>  {
	
	List<LotecaResults> lotecaResults;
	Context context;
	int resource;

	public LotecaListViewAdapter(Context context, int resource, List<LotecaResults> lotecaResults) {
		super(context,resource, lotecaResults);
		this.context = context;
		this.resource  = resource;
		this.lotecaResults = lotecaResults;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
    	View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(resource, parent, false);
		}
		
		
		final LotecaResults lotecaResult = (LotecaResults) super.getItem(position);
		
		((TextView) gridView.findViewById(R.id.loteca_jogo_numero)).setText(Integer.toString(position + 1));
		((TextView) gridView.findViewById(R.id.loteca_time_1)).setText(lotecaResult.getHomeTeam());
		((TextView) gridView.findViewById(R.id.loteca_time_2)).setText(lotecaResult.getAwayTeam());
		
		
		int wins =  Integer.parseInt(lotecaResult.getWins());
		int draws =  Integer.parseInt(lotecaResult.getDraws());
		int losses =  Integer.parseInt(lotecaResult.getLosses());
		
		
		//mais vitorias
		if (wins > draws &&  wins > losses){
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle);
		}

		//mais derrotas
		else if (losses > draws &&  losses > wins){
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle);
		}
		
		// mais empates
		else if (draws > wins &&  draws > losses){
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle);
		}
		
		// vitorias == empates == derrotas
		else if (Integer.parseInt(lotecaResult.getWins()) == Integer.parseInt(lotecaResult.getDraws())
				&& Integer.parseInt(lotecaResult.getWins()) == Integer.parseInt(lotecaResult.getLosses())) {
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle_red);
		}
		
		// vitorias == empates
		else if (Integer.parseInt(lotecaResult.getWins()) ==  Integer.parseInt(lotecaResult.getDraws())){
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle);
		}
		
		// vitorias == derrotas
		else if (Integer.parseInt(lotecaResult.getWins()) == Integer.parseInt(lotecaResult.getLosses())) {
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle);
		}
		
		// empates == derrotas
		else if (Integer.parseInt(lotecaResult.getDraws()) == Integer.parseInt(lotecaResult.getLosses())) {
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle_red);
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle);
		}
		
		else {
			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle);
		}
		
		// eh serie B ou nao tem dados
		if (lotecaResult.isSerieB()
				|| (Integer.parseInt(lotecaResult.getWins()) == 0 && Integer.parseInt(lotecaResult.getDraws()) == 0 && Integer.parseInt(lotecaResult
						.getLosses()) == 0)) {
			//gridView.setBackgroundColor(Color.parseColor("#388E8E"));

			((ImageView) gridView.findViewById(R.id.rectimage1)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage2)).setImageResource(R.drawable.rectangle);
			((ImageView) gridView.findViewById(R.id.rectimage3)).setImageResource(R.drawable.rectangle);
		}

		else
			gridView.setBackgroundColor(Color.parseColor("#ECF0EF"));
		
	
		return gridView;
    }
	
	 @Override
     public int getCount(){
           return lotecaResults!=null ? lotecaResults.size() : 0;
     }

}
