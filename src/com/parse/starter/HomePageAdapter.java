package com.parse.starter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class HomePageAdapter extends FragmentPagerAdapter {

	public ArrayList<String> mPageTitle;
	public HomePageAdapter(FragmentManager fm,  ArrayList<String> pageTitle) {
		super(fm);
		this.mPageTitle = pageTitle;
	}

	@Override
	public Fragment getItem(int arg0) {
		HomePagerFragment fragment = new HomePagerFragment();
		return fragment;
	}

	@Override
	public int getCount() {
		return mPageTitle.size();
	}


}
