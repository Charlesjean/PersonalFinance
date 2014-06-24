package com.parse.starter;

import com.djchen.database.DataBaseManipulation;
import com.djchen.model.StatementListAdapter;
import com.djchen.View.SwipeDateView;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class StatementFragment extends CustomFragment implements SwipeDateView.DateChangeListener{

	StatementListAdapter listAdapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_statements_screen, null);
		ExpandableListView list = (ExpandableListView)view.findViewById(R.id.statement_day_list);
		list.setGroupIndicator(null);
		SwipeDateView sdv = (SwipeDateView)view.findViewById(R.id.statement_date_view);
		sdv.mListener = this;
		DataBaseManipulation db = new DataBaseManipulation(this.getActivity());
		listAdapter = new StatementListAdapter(getActivity(), db.queryStatements(sdv.getStartDate(), sdv.getEndDate()), R.layout.statement_group_view, R.layout.statement_child_view, 0);
		list.setAdapter(listAdapter);

		return view;
	}

	@Override
	public void onDateChanged(String s, String e) {
		DataBaseManipulation db = new DataBaseManipulation(this.getActivity());
		listAdapter.statements = db.queryStatements(s, e);
		listAdapter.notifyDataSetChanged();
		
	}
	
}
