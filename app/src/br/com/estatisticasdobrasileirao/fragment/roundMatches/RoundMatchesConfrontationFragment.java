package br.com.estatisticasdobrasileirao.fragment.roundMatches;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.estatisticasdobrasileirao.R;
import br.com.estatisticasdobrasileirao.asyncTask.roundMatches.RoundMatchesConfrontationAsync;

import com.components.Line;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class RoundMatchesConfrontationFragment extends Fragment {

	private InterstitialAd interstitial;
	private Button showButton;
	private static final String pattern = "E MM/dd/yyyy HH:mm:ss.SSS";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.activity_services_history, container, false);
		
		final FragmentActivity fragmentActivity = getActivity();

		interstitial = new InterstitialAd(fragmentActivity);
		interstitial.setAdUnitId("ca-app-pub-2728430253641042/3293184013");

		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				showButton.setEnabled(true);

				SharedPreferences sp = fragmentActivity.getSharedPreferences("First_share_memory", Activity.MODE_PRIVATE);

				if (sp.contains("nextHour")) {
					DateTime currentTime = new DateTime();
					if (currentTime.isAfter(getLastTimeAdWasDisplayed(sp))){
						interstitial.show();
						updatNextTimeToDisplayTheAdd(sp);
					}
				}
				else {
					interstitial.show();
					updatNextTimeToDisplayTheAdd(sp);
				}

			}

			private void updatNextTimeToDisplayTheAdd(SharedPreferences sp) {
				sp.edit().putString("nextHour", new DateTime().plusMinutes(15).toString(pattern)).commit();
			}

			private DateTime getLastTimeAdWasDisplayed(SharedPreferences sp) {
				DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
				String string = sp.getString("nextHour", "");
				return  formatter.parseDateTime(string);
			}
			@Override
			public void onAdFailedToLoad(int errorCode) {
				showButton.setEnabled(false);
			}
		});

		showButton = (Button) rootView.findViewById(R.id.showButton);
		showButton.setOnClickListener(loadButtonOnClick);
		showButton.performClick();

		((Line) rootView.findViewById(R.id.line)).setVisibility(View.GONE);
		((TextView) rootView.findViewById(R.id.titledsfsd)).setVisibility(View.GONE);
		ListView a = ((ListView) rootView.findViewById(R.id.services_history_list));
		((ImageView) rootView.findViewById(R.id.toricda)).setVisibility(View.GONE);

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		a.setLayoutParams(layoutParams);
		
		new RoundMatchesConfrontationAsync(fragmentActivity, rootView).execute();

		return rootView;

	}

	private OnClickListener loadButtonOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			AdRequest adRequest = new AdRequest.Builder().build();

			interstitial.loadAd(adRequest);
		}
	};

}
