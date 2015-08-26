package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.Game;
 
public class RoundMatchesListViewAdapter extends ArrayAdapter<Game> {
	private List<Game> games;
	private int resourceId;
	private Context context;
	
    public RoundMatchesListViewAdapter(Context context, int resourceId, List<Game> games) {
        super(context, resourceId, games);
        this.games = games;
        this.resourceId = resourceId;
        this.context = context;
    }
 

	public View getView(int position, View convertView, ViewGroup parent) {
    	View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(resourceId, parent, false);
		}
		
		Game game = games.get(position);
		
		if (game.getAwayResult() != null && game.getHomeResult() != null) ((TextView) gridView.findViewById(R.id.gameResult)).setText(game.getHomeResult() + " x " + game.getAwayResult());
		
		else ((TextView) gridView.findViewById(R.id.gameResult)).setText("x");
		
		
		((ImageView) gridView.findViewById(R.id.homeSymbolRound)).setImageResource(context.getResources().getIdentifier(StringUtils.stripAccents(game.getHomeTeam().toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""), "drawable", context.getPackageName()));
		((ImageView) gridView.findViewById(R.id.awaySymbolRound)).setImageResource(context.getResources().getIdentifier(StringUtils.stripAccents(game.getAwayTeam().toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""), "drawable", context.getPackageName()));
		((TextView) gridView.findViewById(R.id.descricao)).setText(game.getGameDate());
		((TextView) gridView.findViewById(R.id.awayTeamName)).setText(game.getAwayTeam());
		((TextView) gridView.findViewById(R.id.homeTeamName)).setText(game.getHomeTeam());
		
		return gridView;
    	
    }
}