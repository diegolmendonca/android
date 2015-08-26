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

public class LotogolListViewAdapter extends ArrayAdapter<LotecaResults> {

	List<LotecaResults> lotecaResults;
	Context context;
	int resource;

	public LotogolListViewAdapter(Context context, int resource, List<LotecaResults> lotecaResults) {
		super(context, resource, lotecaResults);
		this.context = context;
		this.resource = resource;
		this.lotecaResults = lotecaResults;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(resource, parent, false);
		}

		final LotecaResults lotecaResult = (LotecaResults) super.getItem(position);

		((TextView) gridView.findViewById(R.id.lotogol_time_1)).setText(lotecaResult.getHomeTeam());
		((TextView) gridView.findViewById(R.id.lotogol_time_2)).setText(lotecaResult.getAwayTeam());

		int wins = Integer.parseInt(lotecaResult.getWins());
		int draws = Integer.parseInt(lotecaResult.getDraws());
		int losses = Integer.parseInt(lotecaResult.getLosses());
		double totalGames = wins + draws + losses;

		double goalsMade = Double.parseDouble(lotecaResult.getGoalsMade());
		double goalsTaken = Double.parseDouble(lotecaResult.getGoalsTaken());

		// eh serie B ou nao tem dados
		if (lotecaResult.isSerieB()
				|| (Integer.parseInt(lotecaResult.getWins()) == 0 && Integer.parseInt(lotecaResult.getDraws()) == 0 && Integer.parseInt(lotecaResult
						.getLosses()) == 0)) {
			gridView.setBackgroundColor(Color.RED);

			((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.numeros_lotogol);
			((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
		}

		else {

			int averageGoalsMade = (int) Math.round(goalsMade / totalGames);
			int averageGoalsTaken = (int) Math.round(goalsTaken / totalGames);

			if (averageGoalsMade == 0) {
				((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.lotogol_res_0);
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
			}

			else if (averageGoalsMade == 1) {
				((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.lotogol_res_1);
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
			}

			else if (averageGoalsMade == 2) {
				((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.lotogol_res_2);
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
			}

			else if (averageGoalsMade == 3) {
				((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.lotogol_res_3);
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
			}

			else if (averageGoalsMade >= 4) {
				((ImageView) gridView.findViewById(R.id.lotogol_image1)).setImageResource(R.drawable.lotogol_res_plus);
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.numeros_lotogol);
			}

			if (averageGoalsTaken == 0) {
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.lotogol_res_0);
			}

			else if (averageGoalsTaken == 1) {
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.lotogol_res_1);
			}

			else if (averageGoalsTaken == 2) {
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.lotogol_res_2);
			}

			else if (averageGoalsTaken == 3) {
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.lotogol_res_3);
			}

			else if (averageGoalsTaken >= 4) {
				((ImageView) gridView.findViewById(R.id.lotogol_image2)).setImageResource(R.drawable.lotogol_res_plus);
			}

		}
		
		return gridView;
	}

	@Override
	public int getCount() {
		return lotecaResults != null ? lotecaResults.size() : 0;
	}

}
