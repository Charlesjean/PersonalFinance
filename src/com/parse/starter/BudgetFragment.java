package com.parse.starter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.djchen.View.EditTextBackEvent;
import com.djchen.model.BudgetAdapter;
import com.djchen.model.BudgetEntry;
import com.parse.starter.R;
public class BudgetFragment extends CustomFragment {

	ListView budgetList;
	EditTextBackEvent editView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_budget, null);
		budgetList = (ListView)view.findViewById(R.id.budget_subcatgory_list);
		editView = (EditTextBackEvent)view.findViewById(R.id.budget_edit);
		editView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				Log.i("DJ", editView.getText().toString());
				editView.setVisibility(View.GONE);
				return false;
			}
		});
		//TODO we need to get budget info from database
		this.setFakedListData();
		budgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				if(inputManager != null)
				{
					editView.setVisibility(View.VISIBLE);
					editView.setFocusableInTouchMode(true);
					editView.requestFocus();
					inputManager.showSoftInput(editView, InputMethodManager.SHOW_IMPLICIT);
				}
					
			}
			
		});
		return view;
	}
	
	
	
	private void setFakedListData() {
		ArrayList<BudgetEntry> listEntry = new ArrayList<BudgetEntry>();
		String[] categories = this.getResources().getStringArray(R.array.record_categories);
		for(int i = 0; i < categories.length; ++i)
		{
			BudgetEntry entry = new BudgetEntry(categories[i], 500, 100);
			listEntry.add(entry);
		}
		
		budgetList.setAdapter(new BudgetAdapter(this.getActivity(), R.layout.budget_category_row, listEntry));
	}
}
