package com.djchen.View;

import java.io.Serializable;
import java.util.Calendar;

import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

public class DatePickFragment extends DialogFragment {

	public static final String DATE_SET_LISTENER = "DateSetListener";
	public static final String DATE_DEFAULT = "DefaultDate";
	
	public static DatePickFragment getNewInstance(DatePickerDialog.OnDateSetListener listener, Calendar defaultDate) {
		DatePickFragment dateFrag = new DatePickFragment();
		Bundle b = new Bundle();
		b.putSerializable(DATE_SET_LISTENER, (Serializable)listener);
		b.putSerializable(DATE_DEFAULT, defaultDate);
		dateFrag.setArguments(b);
		return dateFrag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener)this.getArguments().getSerializable(DATE_SET_LISTENER);
		Calendar c = (Calendar)this.getArguments().getSerializable(DATE_DEFAULT);
		if (c == null)
			c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
	
		DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), listener, year, month, day );
		dateDialog.setTitle("»’∆⁄…Ë÷√");
		return dateDialog;
	}
	

}
