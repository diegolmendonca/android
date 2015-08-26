package br.com.estatisticasdobrasileirao.datacrossing;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import br.com.estatisticasdobrasileirao.R;

public class DataCrossingActivity extends FragmentActivity {
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_crossing_init);
		
		
		Animation animation = new TranslateAnimation(0,0, 100, -100);
		
		animation.setDuration(1500);
		
		
		
//		animation.setAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationEnd(Animation animation) {
//			FrameLayout fl = (FrameLayout) findViewById(R.id.instruction);
//			fl.setVisibility(fl.GONE);
//			}
//			@Override
//			public void onAnimationRepeat(Animation animation) {}
//			@Override
//			public void onAnimationStart(Animation animation) {}
//			}
//			fl.startAnimation(slideUp);
		
		
		// Start animating the image
		final ImageView splash = (ImageView) findViewById(R.id.homeTeamDataCrossing);
		splash.startAnimation(animation);

		// Later.. stop the animation
		//splash.setAnimation(null);
		

}
	
}
