package com.parse.starter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.djchen.View.DatePickFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;

public class AddRecordFragment extends CustomFragment implements DatePickerDialog.OnDateSetListener, Serializable{

	private static final long serialVersionUID = 1L;
	Button mSetDateBtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater inflate = (LayoutInflater)this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.layout_add_record_screen, null);
		mSetDateBtn = (Button)view.findViewById(R.id.record_set_date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		mSetDateBtn.setText(dateFormat.format(new Date()));
		mSetDateBtn.setOnClickListener(clickListener);
		return view;
	}
	
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			DatePickFragment dateDialog = DatePickFragment.getNewInstance(AddRecordFragment.this);
			dateDialog.show(AddRecordFragment.this.getFragmentManager(), "Choose_date");
		}
		
	};
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		this.mSetDateBtn.setText(dateFormat.format(new Date(year - 1900, monthOfYear, dayOfMonth)));
	}

}
