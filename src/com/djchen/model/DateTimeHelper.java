package com.djchen.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeHelper {
	public int year;
	public int month;
	
	public DateTimeHelper(Calendar date) {
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH);
	}
	
	public void nextMonth() {
		GregorianCalendar gc = new GregorianCalendar(this.year, this.month, 1);
		gc.add(Calendar.MONTH, 1);
		this.year = gc.get(Calendar.YEAR);
		this.month = gc.get(Calendar.MONTH);
	}
	
	public void previousMonth() {
		GregorianCalendar gc = new GregorianCalendar(this.year, this.month, 1);
		gc.add(Calendar.MONTH, -1);
		this.year = gc.get(Calendar.YEAR);
		this.month = gc.get(Calendar.MONTH);
	}
	
	public String getReadableDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINESE);
		return sdf.format(new Date(this.year - 1900, this.month + 1, 0));
	}

	public String getFirstDateOfMonth() {
		GregorianCalendar gc = new GregorianCalendar(this.year, this.month, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMinimum(Calendar.DAY_OF_MONTH));
		return sdf.format(gc.getTime());
	}
	
	public String getLastDateOfMonth() {
		GregorianCalendar gc = new GregorianCalendar(this.year, this.month, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(gc.getTime());
	}
}
