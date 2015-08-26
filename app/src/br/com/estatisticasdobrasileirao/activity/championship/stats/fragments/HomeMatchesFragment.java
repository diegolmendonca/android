package br.com.estatisticasdobrasileirao.activity.championship.stats.fragments;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.ChampStatisAdapter;
import br.com.estatisticasdobrasileirao.db.HistoryDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.ChampionshipStatis;
import br.com.estatisticasdobrasileirao.entity.Team;

public class HomeMatchesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.activity_services_history, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final Team favorite = new TeamsDAO().getFavorite(getActivity());
		List<ChampionshipStatis> cache = new HistoryDAO().getHomeMatches(getActivity(), StringUtils.stripAccents(favorite.getName().toLowerCase().trim())
				.replaceAll("-", ""));

		buildView(cache);

	}

	private void buildView(List<ChampionshipStatis> cache) {
		ListView listView = (ListView) getView().findViewById(R.id.services_history_list);
		
		((TextView) getView().findViewById(R.id.titledsfsd)).setText("Número de jogos em casa(1971-2013)");
		
		listView.setAdapter(new ChampStatisAdapter(getActivity().getBaseContext(), R.layout.fragment_round_matches_list, cache,"home"));
	}

}
