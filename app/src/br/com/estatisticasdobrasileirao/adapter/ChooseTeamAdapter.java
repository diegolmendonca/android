package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.Team;

public class ChooseTeamAdapter  extends ArrayAdapter<Team> {
	
	List<Team> teams;
	
    public ChooseTeamAdapter(Context context, int resourceId, List<Team> teams) {
        super(context, resourceId, teams);
        this.teams = teams;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
    	View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(R.layout.choose_team, parent, false);
		}
		Team team = teams.get(position);
		
		((TextView) gridView.findViewById(R.id.teamName)).setText(team.getName());
		((ImageView) gridView.findViewById(R.id.symbol)).setImageResource(team.getSymbolId());
		
		return gridView;
    	
    }

}
