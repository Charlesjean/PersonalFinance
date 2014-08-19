package com.parse.starter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;

import com.djchen.View.EditTextBackEvent;
import com.djchen.database.DataBaseManipulation;
import com.djchen.model.BudgetAdapter;
import com.djchen.model.BudgetEntry;
import com.parse.starter.R;

public class BudgetFragment extends CustomFragment {

	//widgets on this fragment
	ListView mBudgetList;
	EditTextBackEvent mEditView;
	TextView mEditCategoryName;
	BudgetAdapter mbudgetListAdapter;
	LinearLayout mEditLayoutContainer;
	DataBaseManipulation db = new DataBaseManipulation(getActivity());
	//helper instance to hide/show the attach view of keyboard
	private CheckPostionRunnable mPositionRunnable = new CheckPostionRunnable();
	public Handler mhandler = new Handler();
	
	public int selected_Budget_Type_Pos;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate layout and get viewer handle
		LayoutInflater inflate = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_budget, null);
		mBudgetList = (ListView)view.findViewById(R.id.budget_subcatgory_list);
		mEditLayoutContainer = (LinearLayout)view.findViewById(R.id.edit_layout_container);
		mEditView = (EditTextBackEvent)view.findViewById(R.id.budget_edit);
		mEditCategoryName = (TextView)view.findViewById(R.id.budget_edit_category_name);
		
		//set listener to listen to IME_ACTION_DONE
		//when done is pressed on keyboard, we dismiss keyboard,
		//read user's input and write it into database
		mEditView.setOnEditorActionListener(new TextView.OnEditorActionListener() {	
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE) {
					InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					inputManager.hideSoftInputFromWindow(mEditView.getWindowToken(), 0);
					db.updateOrInsertBudget(mbudgetListAdapter.getItem(selected_Budget_Type_Pos).getCategory(), Double.parseDouble(mEditView.getText().toString()));
					mbudgetListAdapter.getItem(selected_Budget_Type_Pos).setBudgetAmount(Double.parseDouble(mEditView.getText().toString()));
					mbudgetListAdapter.notifyDataSetChanged();
				}
				return false;
			}
		});
		this.setFakedListData();
		mBudgetList.setAdapter(mbudgetListAdapter);
		//populate the budget listView, and listen to click event to pop up keyboard and attach view.
		mBudgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				if(inputManager != null) {
					selected_Budget_Type_Pos = position;
					mEditCategoryName.setText(mbudgetListAdapter.getItem(position).getCategory());
					mEditLayoutContainer.setVisibility(View.VISIBLE);
				    startCheckPosition();
					mEditView.setFocusableInTouchMode(true);
					mEditView.requestFocus();
					inputManager.showSoftInput(mEditView, InputMethodManager.SHOW_IMPLICIT);
				}		
			}
		});
		return view;
	}	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		db = new DataBaseManipulation(getActivity());
		
		
	}



	@Override
	public void onDetach() {
		super.onDetach();
		db.closeDB();
	}

	//TODO we just use faked data now, will read from database and replace this method
	private void setFakedListData() {
		ArrayList<BudgetEntry> listEntry = new ArrayList<BudgetEntry>();
		String[] categories = this.getResources().getStringArray(R.array.record_categories);
		for(int i = 0; i < categories.length; ++i) {
			BudgetEntry entry = db.queryBudgetAmountAndCost(categories[i], "2014-08-01", "2014-08-31");
			listEntry.add(entry);
		}
		mbudgetListAdapter = new BudgetAdapter(this.getActivity(), R.layout.budget_category_row, listEntry);
		
	}
	
	
	/**
	 * Inner class to check the position of the linearLayout above the keyboard,
	 * we need to use this position to decide when to hide the layout.
	 */
    public void startCheckPosition() {
    	
    	this.mPositionRunnable.stop();
    	this.mPositionRunnable.start();
    	new Thread(this.mPositionRunnable).start();
    }
    public void stopCheckPosition() {
    	
    	this.mPositionRunnable.stop();
    }

    public class CheckPostionRunnable implements Runnable {

    	private int previousTop = -1;
    	private volatile boolean needToCheck = false;
    	
    	public void start()	{
    		needToCheck  = true;
    		
    	}
    	public void stop() {
    		needToCheck  = false;
    	}
    	
		@Override
		public void run() {
			this.previousTop = -1;
			while(needToCheck) {
				
				if(previousTop == -1 || previousTop == 0) {
					previousTop =mEditLayoutContainer.getTop();
				}
				else {
					int currenttop = mEditLayoutContainer.getTop();
					if( currenttop > previousTop ) {// editText is going downward, we should hide it
					
						Log.i("DJ", "current: " + currenttop + "previous: " + previousTop);
						previousTop = currenttop;
						mhandler.post(new Runnable() {

							@Override
							public void run() {
								mEditLayoutContainer.setVisibility(View.GONE);	
							}
						});
						this.needToCheck = false;
					}
					else {//editText is going upward
					
						previousTop = currenttop;	
					}
					if(currenttop != previousTop) {
						Log.i("DJ", "current: " + currenttop + "previous: " + previousTop);
						previousTop = currenttop;
					}
				}	
			}
		}	
    }
}
