package com.parse.starter;


import java.util.ArrayList;
import com.djchen.View.RecordChartContainer;
import com.djchen.database.DataBaseManipulation;
import com.djchen.model.BarChartAdapter;
import com.djchen.model.RecordPresentationEntry;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomePagerFragment extends CustomFragment {

	enum USER{CURRENT, ASSOCIATE, BOTH};
	private RecordChartContainer chartContainer;
	private ListView barView;
	
	private int user_type;
	DataBaseManipulation db;
	
	
	//we use type to determin which user info the fragment should show,
	//0 for current user, 1 for associate user, 2 for both of them
	private static final String USER_TYPE = "usertype";
	
	public static CustomFragment newInstance(int argc) {
		HomePagerFragment fragment = new HomePagerFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(USER_TYPE, argc);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			user_type = arguments.getInt(USER_TYPE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_home_page, null);

		//TODO we need to read data from database according to arguments stored in bundle
		ArrayList<RecordPresentationEntry> entries = this.tempData();
		
		chartContainer = (RecordChartContainer)view.findViewById(R.id.chart_view_container);
		chartContainer.setTotalAmount(10000);
		chartContainer.setSpentAmount(5000);
		chartContainer.startAnimation();
				
		barView = (ListView)view.findViewById(R.id.bar_view);
		barView.setAdapter(new BarChartAdapter(this.getActivity(), 0, entries));
		return view; 
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		db = new DataBaseManipulation(getActivity());
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	private ArrayList<RecordPresentationEntry> tempData() {
		 int[] colorArray = {R.color.Chart_Blue, R.color.Chart_Darkgray, R.color.Chart_Green, R.color.Chart_Purple, R.color.Chart_Red, R.color.Chart_Yellow};
		String[] mcategories = getResources().getStringArray(R.array.record_categories);
		ArrayList<RecordPresentationEntry> entries = new ArrayList<RecordPresentationEntry>();
		for(int i = 0; i < 6; ++i) {
			RecordPresentationEntry entry = new RecordPresentationEntry(mcategories[i], i * 100 + 100, colorArray[i] );
			entries.add(entry);
		}
		return entries;		
	}
	
}
