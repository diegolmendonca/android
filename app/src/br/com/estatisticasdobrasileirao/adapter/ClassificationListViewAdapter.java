package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.Classification;
 
public class ClassificationListViewAdapter extends ArrayAdapter<Classification> {
	private List<Classification> classificationList;
	private int resourceId;
	private Context context;
	
    public ClassificationListViewAdapter(Context context, int resourceId, List<Classification> classificationList) {
        super(context, resourceId, classificationList);
        this.classificationList = classificationList;
        this.resourceId = resourceId;
        this.context = context;
    }
 

	public View getView(int position, View convertView, ViewGroup parent) {
    	View gridView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(resourceId, parent, false);
		}
		Classification classification = classificationList.get(position);
		
		
		 if(position % 2 == 0){  
			 gridView.setBackgroundColor(Color.rgb(18, 120, 38)); //#127826
			   }
			   else {
				   gridView.setBackgroundColor(Color.rgb(126, 172, 100));  //#7eac64
			   }
		
		
		
		
		((ImageView) gridView.findViewById(R.id.homeSymbolClassification)).setImageResource(context.getResources().getIdentifier(StringUtils.stripAccents(classification.getTeamName().toLowerCase().trim()).replaceAll("-", "").replaceAll(" ", ""), "drawable", context.getPackageName()));
		((TextView) gridView.findViewById(R.id.posicao)).setText(Integer.toString(classification.getPosicao()));
		((TextView) gridView.findViewById(R.id.jogos)).setText(Integer.toString(classification.getJogos()));
		((TextView) gridView.findViewById(R.id.pontos)).setText(Integer.toString(classification.getPontos()));
		((TextView) gridView.findViewById(R.id.vitorias)).setText(Integer.toString(classification.getVitorias()));
		((TextView) gridView.findViewById(R.id.empates)).setText(Integer.toString(classification.getEmpates()));
		((TextView) gridView.findViewById(R.id.derrotas)).setText(Integer.toString(classification.getDerrotas()));
		
		return gridView;
    	
    }
}