package com.parse.starter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.djchen.View.DatePickFragment;
import com.djchen.View.CategorySelectDialog;
import com.djchen.database.DataBaseHelper;
import com.djchen.database.DataBaseManipulation;
import com.djchen.database.Record;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.DatePickerDialog;

public class AddRecordFragment extends CustomFragment implements DatePickerDialog.OnDateSetListener, Serializable, CategorySelectDialog.CategorySelectListener{

	private static final long serialVersionUID = 1L;
	private String[] mcategories;
	private String[] maccountTypes;
	Button mSetDateBtn;
	Button mSetCategoryBtn;
	Button mSetAccountTypeBtn;
	Button mResponderView;
	Button mSaveAndAgainBtn;
	EditText mAmount;
	int mSelectedCategory = 0;
	int mSelectedAccountType = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_add_record_screen, null);
		
		this.getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//date
		mSetDateBtn = (Button)view.findViewById(R.id.record_set_date);
		mSetDateBtn.setOnClickListener(clickListener);
		
		//category
		mcategories = getResources().getStringArray(R.array.record_categories);
		mSetCategoryBtn = (Button)view.findViewById(R.id.record_set_category);
		mSetCategoryBtn.setOnClickListener(clickListener);
		
		//account type
		mSetAccountTypeBtn = (Button)view.findViewById(R.id.record_set_account_type);
		maccountTypes = getResources().getStringArray(R.array.record_account_types);
		mSetAccountTypeBtn.setOnClickListener(clickListener);
		
		//amount
		mAmount = (EditText)view.findViewById(R.id.record_edit_amount);
		setHasOptionsMenu(true);
		
		this.resetBtns();
		mSaveAndAgainBtn = (Button)view.findViewById(R.id.record_save_and_again);
		mSaveAndAgainBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mAmount.getText().toString().equals(""))
					showAlertDialog();
				else {
					saveRecord();
					resetBtns();
				}
			}
			
		});
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
			//dimiss keyboard if necessary
			InputMethodManager imm = (InputMethodManager)this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(mAmount.getWindowToken(), 0);
			if (mAmount.getText().toString().equals(""))
				showAlertDialog();	
			else
			{
				this.saveRecord();
				this.getFragmentManager().popBackStack();
			}
			
		}
		//this.getActivity().deleteDatabase("personalfinance");
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
	public void onItemSelected(ArrayList<Integer> items) {
		if(items.isEmpty())
			return;
		if(mResponderView == mSetCategoryBtn) {
			mResponderView.setText(mcategories[items.get(0)]);
			mResponderView = null;
			mSelectedCategory = items.get(0);
		} else if(mResponderView == mSetAccountTypeBtn) {
			mResponderView.setText(maccountTypes[items.get(0)]);
			mResponderView = null;
			mSelectedAccountType = items.get(0);
			
		}
	}
	
	private void showAlertDialog() {
		new AlertDialog.Builder(this.getActivity()).setMessage(this.getResources().getString(R.string.record_warning))
		.setCancelable(true).setPositiveButton(R.string.record_warning_dialog_button, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		}).show();
	}
	
	public void resetBtns() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		mSetDateBtn.setText(dateFormat.format(new Date()));
		mSetCategoryBtn.setText(mcategories[0]);
		mSetAccountTypeBtn.setText(maccountTypes[0]);
		mAmount.setText("");
		mAmount.clearFocus();
	}
	
	public void saveRecord() {
		//write record to database
		DataBaseManipulation db = new DataBaseManipulation(getActivity());
		Record record = new Record(Record.COST, Double.parseDouble(mAmount.getText().toString()), mSetDateBtn.getText().toString(), "hello", Record.CASH , mSetCategoryBtn.getText().toString());
		long id = db.addRecord(record);
		Log.i("DJ", db.getRecord(id).toString());	
	}
	
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mResponderView = (Button)v;
			if(v == mSetDateBtn) {
				SimpleDateFormat dataF = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
				Calendar c = null;
				try {
					Date date = dataF.parse(mSetDateBtn.getText().toString());
					c = Calendar.getInstance();
					c.setTime(date);
					Log.i("DJ", c.get(Calendar.YEAR) + " " + c.get(Calendar.MONTH) + " " + c.get(Calendar.DAY_OF_MONTH));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				DatePickFragment dateDialog = DatePickFragment.getNewInstance(AddRecordFragment.this, c);
				dateDialog.show(AddRecordFragment.this.getFragmentManager(), "Choose_date");
								
			} else if(v == mSetCategoryBtn) {
				CategorySelectDialog dialog = CategorySelectDialog.newInstance(AddRecordFragment.this, mcategories, mSelectedCategory);
				dialog.show(getChildFragmentManager(), "Choose_category");
				
			} else if(v == mSetAccountTypeBtn) {
				CategorySelectDialog dialog = CategorySelectDialog.newInstance(AddRecordFragment.this, maccountTypes, mSelectedAccountType);
				dialog.show(getChildFragmentManager(), "Choose_account_type");
			}

		}
	};
}
