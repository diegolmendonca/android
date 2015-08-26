package br.com.estatisticasdobrasileirao.fragment.roundMatches;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.asyncTask.roundMatches.RoundMatchesAsync;

public class RoundMatchesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	// PEGAR A RODADA ATUAL
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_round_matches, container, false);
        
		new RoundMatchesAsync(getActivity(), rootView).execute();
        
        return rootView;
         
    }
    
    
}
