package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Team;
 
public class TeamListViewAdapter extends ArrayAdapter<Team> {
 
    private Context context;
    private Activity activity;
 
    public TeamListViewAdapter(Context context, Activity activity, int resourceId, List<Team> items) {
        super(context, resourceId, items);
        this.context = context;
        this.activity = activity;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView teamSymbol;
        TextView teamName;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Team rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_favorite_team_list, null);
            holder = new ViewHolder();
            holder.teamName = (TextView) convertView.findViewById(R.id.teamName);
            holder.teamSymbol = (ImageView) convertView.findViewById(R.id.teamSymbol);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.teamSymbol.setImageResource(rowItem.getSymbolId());
        holder.teamName.setText(rowItem.getName());
        
        
        final OnClickListener modelListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl = (RelativeLayout)v.getParent();
                TextView tv = (TextView)rl.getChildAt(1);
                String team =tv.getText().toString();
                TeamsDAO teamDAO = new TeamsDAO();
                teamDAO.updateFavorite(context, team);
             
                Intent intent = activity.getIntent();
                activity.finish();
                activity.startActivity(intent);
//                Toast toast = Toast.makeText(activity.getApplicationContext(),
//                        "Item " + tv.getText() + ", " + fav,
//                        Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//                    toast.show();
            }
       };   
       holder.teamName.setOnClickListener(modelListener);
        
      
       holder.teamSymbol.setOnClickListener(modelListener);
        
        
        return convertView;
    }
    
   
}