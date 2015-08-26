package br.com.estatisticasdobrasileirao.activity.loterias;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.entity.LotecaResults;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class LotecaMatchesFragment extends SherlockFragmentActivity {

	Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_round_matches_graphs_loteria);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F71713")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Loteca - Hist. de Jogos");
		
		LotecaResults result = (LotecaResults) getIntent().getParcelableExtra("lotecaResults");
		
		mContext = LotecaMatchesFragment.this;
		

		UIUtils.setTextViewText(this, R.id.nome_dos_times, result.getHomeTeam() + " X " + result.getAwayTeam());
		
		if (result.isSerieB()) {

			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle("Esse jogo é da série B");
			builder.setCancelable(true);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setMessage("Infelizmente só temos histórico para jogos da série A do brasileirão! ");
			builder.setPositiveButton("OK",null);

			final AlertDialog alert = builder.create();
			alert.show();
		}
		
		else if (Integer.parseInt(result.getWins()) == 0 && Integer.parseInt(result.getDraws()) == 0 && Integer.parseInt(result.getLosses()) == 0) {

			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle("Não há histórico para esse jogo");
			builder.setCancelable(true);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setMessage("Este jogo é inédito, nunca foi disputado em campeonatos brasileiros, desde 1971.");
			builder.setPositiveButton("OK",null);
			final AlertDialog alert = builder.create();
			alert.show();
		}

		else {

			findViewById(R.id.linearLoteria).setBackgroundColor(Color.argb(100, 50, 50, 50));
			findViewById(R.id.linearLoteria2).setBackgroundColor(Color.argb(100, 50, 50, 50));

			new LotecaGraphsGeneratorAsync(this, new int[] { Color.GREEN, Color.YELLOW, Color.RED }, new String[] { "VITÓRIAS", "EMPATES", "DERROTAS" },
					result, "games").execute();

		}

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}