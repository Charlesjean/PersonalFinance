package com.parse.starter;


import java.util.ArrayList;

import com.djchen.View.RecordChartContainer;
import com.djchen.database.DataBaseManipulation;
import com.djchen.model.BarChartAdapter;
import com.djchen.model.BudgetEntry;
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
	private double total_budget;
	private double total_used;
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
		ArrayList<RecordPresentationEntry> entries = this.getSpentInfo();
		
		chartContainer = (RecordChartContainer)view.findViewById(R.id.chart_view_container);
		chartContainer.setTotalAmount(this.total_budget);
		chartContainer.setSpentAmount(this.total_used);
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
	
	/**
	 * This method is used to get spent infomation for each category
	 * @return
	 */
	private ArrayList<RecordPresentationEntry> getSpentInfo() {
		
		//TODO we need to set colors for each category
		this.total_budget = 0;
		this.total_used = 0;
		 int[] colorArray = {R.color.Chart_Blue, R.color.Chart_Darkgray, R.color.Chart_Green, R.color.Chart_Purple, R.color.Chart_Red, R.color.Chart_Yellow};
		 ArrayList<RecordPresentationEntry> entries = new ArrayList<RecordPresentationEntry>();
		 String[] categories = this.getResources().getStringArray(R.array.record_categories);
		 for(int i = 0; i < categories.length; ++i) {
			 BudgetEntry entry = db.queryBudgetAmountAndCost(categories[i], "2014-08-01", "2014-08-31");
			 this.total_budget += entry.getBudgetAmount();
			 this.total_used += entry.getBudgetUsed();

			 RecordPresentationEntry recordEntry = new RecordPresentationEntry(categories[i], entry.getBudgetUsed(), colorArray[i]);
			 entries.add(recordEntry);
		}
			return entries;
	}
	
}
