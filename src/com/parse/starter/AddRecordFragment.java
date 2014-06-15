package com.parse.starter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.djchen.View.DatePickFragment;
import com.djchen.View.CategorySelectDialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;

public class AddRecordFragment extends CustomFragment implements DatePickerDialog.OnDateSetListener, Serializable, CategorySelectDialog.CategorySelectListener{

	private static final long serialVersionUID = 1L;
	private String[] mcategories;
	private String[] maccountTypes;
	Button mSetDateBtn;
	Button mSetCategoryBtn;
	Button mSetAccountTypeBtn;
	Button mResponderView;
	EditText mAmount;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_add_record_screen, null);
		
		//date
		mSetDateBtn = (Button)view.findViewById(R.id.record_set_date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		mSetDateBtn.setText(dateFormat.format(new Date()));
		mSetDateBtn.setOnClickListener(clickListener);
		
		//category
		mcategories = getResources().getStringArray(R.array.record_categories);
		mSetCategoryBtn = (Button)view.findViewById(R.id.record_set_category);
		mSetCategoryBtn.setText(mcategories[0]);
		mSetCategoryBtn.setOnClickListener(clickListener);
		
		//account type
		mSetAccountTypeBtn = (Button)view.findViewById(R.id.record_set_account_type);
		maccountTypes = getResources().getStringArray(R.array.record_account_types);
		mSetAccountTypeBtn.setText(maccountTypes[0]);
		mSetAccountTypeBtn.setOnClickListener(clickListener);
		
		//amount
		mAmount = (EditText)view.findViewById(R.id.record_edit_amount);
		setHasOptionsMenu(true);
		return view;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {

		super.onPrepareOptionsMenu(menu);
	}


	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.save) {
			Log.i("DJ", "Save clicked");
		}
		return super.onOptionsItemSelected(item);
	}
	

	//Interface API OnDateSetListener
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		this.mSetDateBtn.setText(dateFormat.format(new Date(year - 1900, monthOfYear, dayOfMonth)));
	}

	//Interface API CategorySelectListener
	@Override
	public void onItemSelected(ArrayList<String> items) {
		mResponderView.setText(items.get(0));
		mResponderView = null;
	}
	
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mResponderView = (Button)v;
			if(v == mSetDateBtn) {
				DatePickFragment dateDialog = DatePickFragment.getNewInstance(AddRecordFragment.this);
				dateDialog.show(AddRecordFragment.this.getFragmentManager(), "Choose_date");
			} else if(v == mSetCategoryBtn) {
				int defaultItem = 0;
				CategorySelectDialog dialog = CategorySelectDialog.newInstance(AddRecordFragment.this, mcategories, defaultItem);
				dialog.show(getChildFragmentManager(), "Choose_category");
				
			} else if(v == mSetAccountTypeBtn) {
				CategorySelectDialog dialog = CategorySelectDialog.newInstance(AddRecordFragment.this, maccountTypes, 0);
				dialog.show(getChildFragmentManager(), "Choose_account_type");
			}

		}
		
	};

}
