package br.com.estatisticasdobrasileirao.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RoundMatchesTabAdapter extends FragmentPagerAdapter {

	private List<? extends Fragment> fragments;

	public RoundMatchesTabAdapter(FragmentManager fm , List<? extends Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int index) {
		return fragments.get(index);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
