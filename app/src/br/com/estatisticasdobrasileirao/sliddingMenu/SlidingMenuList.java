package br.com.estatisticasdobrasileirao.sliddingMenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.com.estatisticasdobrasileirao.R;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public final class SlidingMenuList {

	/**
	 * Building a list that will be used as a data for list fragment, which is a
	 * sliding menu content.
	 * 
	 * @param activity
	 * @return List filled with SlidingMenuListItem objects.
	 */
	public static final List<SlidingMenuListItem> getSlidingMenu(
			Activity activity) {

		List<SlidingMenuListItem> list = new ArrayList<SlidingMenuListItem>();

		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_rodada_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_rodada_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_rodada_icon)));

		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_ano_a_ano_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_ano_a_ano_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_ano_a_ano_icon)));

		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_historico_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_historico_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_historico_icon)));
		
		
		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_brasileirao_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_brasileirao_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_brasileirao_icon)));
		
		list.add(new SlidingMenuListItem(R.slidingmenu.list_loteria_id,
				activity.getResources().getString(
						R.slidingmenu.list_loteria_label), activity
						.getResources().getString(
								R.slidingmenu.list_loteria_icon)));

//		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_meu_time_id,
//				activity.getResources().getString(
//						R.slidingmenu.list_item_meu_time_label), activity
//						.getResources().getString(
//								R.slidingmenu.list_item_meu_time_icon)));
		
		
		
//		list.add(new SlidingMenuListItem(R.slidingmenu.list_cruzamento_id,
//				activity.getResources().getString(
//						R.slidingmenu.list_cruzamento_label), activity
//						.getResources().getString(
//								R.slidingmenu.list_cruzamento_icon)));


		return list;
	}
}