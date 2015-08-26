package br.com.estatisticasdobrasileirao.sliddingMenu;

import android.content.Intent;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.activity.FavoriteTeamActivity;
import br.com.estatisticasdobrasileirao.activity.LoteriasActivity;
import br.com.estatisticasdobrasileirao.activity.RoundMatchesTabActivity;
import br.com.estatisticasdobrasileirao.activity.StatisticsPerYearActivity;
import br.com.estatisticasdobrasileirao.classification.ClassificationActivity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 *         This is base abstract builder class, which is responsible for
 *         creating sliding menu and implementing it's default list items click
 *         actions.
 * 
 */
public abstract class SlidingMenuBuilderBase {
	protected SherlockFragmentActivity activity;
	protected SlidingMenu menu = null;

	/**
	 * This method creates sliding out menu from the left screen side. It uses
	 * external "SlidingMenu" library for creation. When menu is attached to the
	 * activity, it places a list fragment inside the menu as it's content.
	 * 
	 * @param activity
	 *            This is Activity to which sliding menu is attached.
	 * 
	 */
	public void createSlidingMenu(SherlockFragmentActivity activity) {
		this.activity = activity;
		// For actual sliding menu creation we use an external open source
		// Android library called "SlidingMenu". It can be found at
		// "https://github.com/jfeinstein10/SlidingMenu".
		// We configure the SlidingMenu to our needs.
		menu = new SlidingMenu(activity);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.sliding_menu_shadow_width);
		menu.setShadowDrawable(R.drawable.sliding_menu_shadow);
		menu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(activity, SlidingMenu.SLIDING_WINDOW);
		menu.setMenu(R.layout.sliding_menu_frame);
		
		SlidingMenuListFragment slidingMenuListFragment = new SlidingMenuListFragment();
		slidingMenuListFragment.setMenuBuilder(this);
		
		

		// We replace a FrameLayout, which is a content of sliding menu, with
		// created list fragment filled with data from menu builder.
		activity.getSupportFragmentManager().beginTransaction()
				.replace(R.id.sliding_menu_frame, slidingMenuListFragment)
				.commit();
	}

	public SlidingMenu getSlidingMenu() {
		return menu;
	}

	// It is our base builder which can be extended, so we can define default
	// actions, which will be called when we press on separate list items.
	public void onListItemClick(SlidingMenuListItem selectedSlidingMenuListItem) {
		switch (selectedSlidingMenuListItem.Id) {

		case R.slidingmenu.list_item_ano_a_ano_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), StatisticsPerYearActivity.class).putExtra("from", "button_estatisticas"));
			break;
			
		case R.slidingmenu.list_item_historico_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), RoundMatchesTabActivity.class).putExtra("from", "button_champ_stats"));
			break;
			
		case R.slidingmenu.list_item_rodada_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), RoundMatchesTabActivity.class).putExtra("from", "button_confronto_direto"));
			break;
		case R.slidingmenu.list_item_meu_time_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), FavoriteTeamActivity.class));
			break;
		case R.slidingmenu.list_item_brasileirao_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), ClassificationActivity.class).putExtra("from", "button_brasileirao"));
			break;
			
		case R.slidingmenu.list_loteria_id:
			menu.toggle();
			activity.startActivity(new Intent(activity.getBaseContext(), LoteriasActivity.class));
			break;		
			
//		case R.slidingmenu.list_cruzamento_id:
//			menu.toggle();
//			activity.startActivity(new Intent(activity.getBaseContext(), DataCrossingActivity.class));
//			break;	
			
			
			
		default:
			break;
		}
	}

}
