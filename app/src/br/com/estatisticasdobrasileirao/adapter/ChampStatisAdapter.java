package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.ChampionshipStatis;
import br.com.estatisticasdobrasileirao.entity.Team;

public class ChampStatisAdapter extends ArrayAdapter<ChampionshipStatis> {
	
	List<ChampionshipStatis> champStatis;
	String type;
	Context context;
	TeamsDAO teamsDAO = new TeamsDAO() ;

	public ChampStatisAdapter(Context context, int resource,List<ChampionshipStatis> champStatis, String type) {
		super(context, resource, champStatis);
		this.context = context;
		this.champStatis = champStatis;
		this.type = type;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
    	View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(R.layout.championship_stat_matches_list, parent, false);
		}
		ChampionshipStatis statis = champStatis.get(position);
		
		
		if (type.equals("home")) ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getTotalGame().toString());
		else if(type.equals("goalsHome")) ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getHomeResult().toString());
		else if(type.equals("goalsAway")) ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getAwayResult().toString());
		else if(type.equals("counterGoalsHome")) ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getAwayResult().toString());
		else if(type.equals("counterGoalsAway")) ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getHomeResult().toString());
		else ((TextView) gridView.findViewById(R.id.jogos_em_casa)).setText(statis.getTotalGame().toString());
			
		((TextView) gridView.findViewById(R.id.posicao)).setText("Ranking: " + statis.getId()) ;
		
		
		Team currentTeam = teamsDAO.getTeamByName(context, WordUtils.capitalizeFully(statis.getTeam().toLowerCase().trim()));
		
		((ImageView) gridView.findViewById(R.id.homeSymbolStatis)).setImageResource(context.getResources().getIdentifier(currentTeam.getSymbolResource(), "drawable", context.getPackageName()));
		
		return gridView;
    	
    }

}
