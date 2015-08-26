package br.com.estatisticasdobrasileirao.fragment.roundMatches;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.asyncTask.roundMatches.GraphsGeneratorAsync;

public class RoundMatchesGraphsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		new GraphsGeneratorAsync(getActivity(),getActivity() , 
								new int[] { Color.GREEN,Color.YELLOW, Color.RED }, 
								new String[] { "VITÓRIAS", "EMPATES", "DERROTAS" }, 
								"games").execute();

		return  inflater.inflate(R.layout.fragment_round_matches_graphs, container, false);

	}


}