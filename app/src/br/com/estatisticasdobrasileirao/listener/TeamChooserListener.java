package br.com.estatisticasdobrasileirao.listener;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.adapter.ChooseTeamAdapter;
import br.com.estatisticasdobrasileirao.db.GamesDAO;
import br.com.estatisticasdobrasileirao.db.TeamsDAO;
import br.com.estatisticasdobrasileirao.entity.Team;
import br.com.estatisticasdobrasileirao.utils.UIUtils;

public class TeamChooserListener implements OnClickListener {
	private List<Team> teams;
	private Activity context;
	private final int defaultOption = -1;

	public TeamChooserListener(Activity context, List<Team> teams) {
		this.teams = teams;
		this.context = context;
	}

	private void showNumberDialog(final List<Team> teams) {

		new AlertDialog.Builder(context).setTitle("Escolha seu time").setPositiveButton("Fechar", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}

		}).setSingleChoiceItems(new ChooseTeamAdapter(context, 1, teams), defaultOption, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, int which) {
				Team team = teams.get(which);
				UIUtils.setTextViewText(context, R.id.team, team.getName());
				UIUtils.setImageResource(context, R.id.teamSymbol, team.getSymbolId());
				new TeamsDAO().updateFavorite(context, team.getName());
				new GamesDAO().deleteAll(context);  // necessario pra nao misturar jogos do time anterior com o escolhido agora
				
				dialog.dismiss();
				
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Time escolhido!");
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.setMessage("Parabéns, seu time agora é: " +  team.getName());
				builder.setPositiveButton("Ok",null);
				final AlertDialog alert = builder.create();
				alert.show();
				
				
			}

		}).create().show();
	}

	@Override
	public void onClick(View v) {
		showNumberDialog(teams);
	}

}
