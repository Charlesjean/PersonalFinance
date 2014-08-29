package com.parse.starter;

import com.djchen.database.DataBaseManipulation;
import com.djchen.model.StatementListAdapter;
import com.djchen.model.StatementListAdapter2;
import com.djchen.View.AnimatedExpandableListView;
import com.djchen.View.SwipeDateView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class StatementFragment extends CustomFragment implements SwipeDateView.DateChangeListener{

	StatementListAdapter2 listAdapter = null;
	AnimatedExpandableListView list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_statements_screen, null);
		list = (AnimatedExpandableListView)view.findViewById(R.id.statement_day_list);
		list.setGroupIndicator(null);
		
		list.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if(list.isGroupExpanded(groupPosition))
					list.collapseGroupWithAnimation(groupPosition);
				else 
					list.expandGroupWithAnimation(groupPosition);
					
				return true;
			}
			
		}) ;
		
		SwipeDateView sdv = (SwipeDateView)view.findViewById(R.id.statement_date_view);
		sdv.mListener = this;
		DataBaseManipulation db = new DataBaseManipulation(this.getActivity());
		listAdapter = new StatementListAdapter2(getActivity(), db.queryStatements(sdv.getStartDate(), sdv.getEndDate()), R.layout.statement_group_view, R.layout.statement_child_view, 0);
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
