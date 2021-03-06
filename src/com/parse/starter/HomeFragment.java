package com.parse.starter;

import java.util.ArrayList;

import com.djchen.View.CustomTab;
import com.djchen.View.TabChangeListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Intent;

public class HomeFragment extends CustomFragment implements TabChangeListener{
	
	public static final int PAGENUM = 3;
	public  ArrayList<String> tabs = new ArrayList<String>();
	public Activity mActivity;
	public ViewPager mPager;
	private CustomTab btnTabs[];
	public int mcurrentPage = 0;
	public ImageButton mRecordBtn;
	
	public HomeFragment() {
		super();
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
		String str1 = this.getResources().getString(R.string.home_tab_me);
		tabs.add(str1);
		str1 = this.getResources().getString(R.string.homt_tab_other);
		tabs.add(str1);
		str1 = this.getResources().getString(R.string.homt_tab_total);
		tabs.add(str1);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate =(LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_home, null);
		mPager = (ViewPager)view.findViewById(R.id.pager);
		mPager.setAdapter(new HomePageAdapter(this.getChildFragmentManager(), tabs));
		mPager.setCurrentItem(mcurrentPage);
		this.setUpCustomTab(view);
		mRecordBtn = (ImageButton)view.findViewById(R.id.compose_button);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
			@Override
			public void onPageSelected(int arg0) {
				btnTabs[mcurrentPage].unDoHighlight();
				btnTabs[arg0].makeHighlight();
				mcurrentPage = arg0;
			}			
		});
		
		mRecordBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AddRecordFragment addRecord = new AddRecordFragment();
				FragmentTransaction ft = HomeFragment.this.getFragmentManager().beginTransaction();
				ft.replace(R.id.container, addRecord);
				ft.addToBackStack(null);
				ft.commit();

			}
		});
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		this.clearViews();
		super.onDestroyView();
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
	private void setUpCustomTab(View view) {
		this.btnTabs = new CustomTab[PAGENUM];
		this.btnTabs[0] = new CustomTab(view, R.id.btn_user, R.id.btn_user_indicator, 0, tabs.get(0), this, this.getActivity());
		this.btnTabs[1] = new CustomTab(view, R.id.btn_associate, R.id.btn_associate_indicator, 1, tabs.get(1), this, this.getActivity());
		this.btnTabs[2] = new CustomTab(view, R.id.btn_total, R.id.btn_total_indicator, 2, tabs.get(2), this, this.getActivity());
		this.btnTabs[mcurrentPage].makeHighlight();
		
	}

	@Override
	public void OnTabChanged(int pos) {
		mPager.setCurrentItem(pos);
		this.btnTabs[this.mcurrentPage].unDoHighlight();
		this.btnTabs[pos].makeHighlight();
		this.mcurrentPage = pos;		
	}	
	
	private void clearViews() {
		this.mPager = null;
		this.mRecordBtn = null;
		this.btnTabs = null;
		this.mcurrentPage = 0;
	}
}
