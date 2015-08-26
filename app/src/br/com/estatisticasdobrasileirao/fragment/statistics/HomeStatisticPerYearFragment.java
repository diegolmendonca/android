package br.com.estatisticasdobrasileirao.fragment.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.activity.statistics.ResultStatisticsPerYearActivity;
import br.com.estatisticasdobrasileirao.db.StatisticsPerYearDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Team;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

public class HomeStatisticPerYearFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.home_statistic_per_year_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ImageView image = (ImageView) getView().findViewById(R.id.teamLogo);

		final Team favoriteTeam = new TeamsDAO().getFavorite(getActivity());
		image.setImageResource(getResources().getIdentifier(favoriteTeam.getSymbolResource(), "drawable", getActivity().getPackageName()));

		getView().findViewById(R.id.button_search).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity().getBaseContext(), ResultStatisticsPerYearActivity.class);
				String year = UIUtils.getSpinnerText(HomeStatisticPerYearFragment.this, R.id.spinnerYear);
				
				if (UIUtils.getSpinnerText(HomeStatisticPerYearFragment.this, R.id.SpinnerType).equalsIgnoreCase("mandante")){
					intent.putExtra("statistics", new StatisticsPerYearDAO().getHomeMatches(getActivity().getBaseContext(), favoriteTeam.getName(),year));
				}
				
				else if (UIUtils.getSpinnerText(HomeStatisticPerYearFragment.this, R.id.SpinnerType).equalsIgnoreCase("visitante")){
					intent.putExtra("statistics", new StatisticsPerYearDAO().getAwayMatches(getActivity().getBaseContext(), favoriteTeam.getName(),year));
				}
				
				else 
					intent.putExtra("statistics", new StatisticsPerYearDAO().getBothMatches(getActivity().getBaseContext(), favoriteTeam.getName(),year));
					
				startActivity(intent);

			}

		});

	}

}
