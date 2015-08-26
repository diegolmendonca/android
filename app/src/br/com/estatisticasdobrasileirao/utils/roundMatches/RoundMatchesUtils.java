package br.com.estatisticasdobrasileirao.utils.roundMatches;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import android.content.Context;
import br.com.estatisticasdobrasileirao.db.GamesDAO;
import br.com.estatisticasdobrasileirao.db.RoundMatchesDAO;
import br.com.estatisticasdobrasileirao.entity.Game;

import com.google.common.collect.Lists;

public class RoundMatchesUtils {
	
	// Primeiro tenta pegar do cache, senao vai pro servidor.
	public static List<Game> getHistoryFromFavorite(Context context, String favoriteHomeTeam, String favoriteAwayTeam) {
	
		List<Game> allFavoriteGames = new GamesDAO().getAllGames(context);
		if (CollectionUtils.isNotEmpty(allFavoriteGames))return allFavoriteGames;

		List<Game> games = new RoundMatchesDAO().getRoundGamesStatis(context, favoriteHomeTeam, favoriteAwayTeam);
		updateGamesFromFavorite(context,games);
		
		return Lists.newArrayList(games);

	}
	
	private static void updateGamesFromFavorite(Context context,List<Game> games){
		GamesDAO gamesDAO = new GamesDAO();
		
		if(CollectionUtils.isNotEmpty(gamesDAO.getAllGames(context))) return;  // antes de inserir, verificar se ja existe dado, para nao duplicar.
		
		for (Game game : games) gamesDAO.insertGame(context, game);
	}
	
}
