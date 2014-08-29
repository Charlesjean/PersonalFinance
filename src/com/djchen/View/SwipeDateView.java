package com.djchen.View;

import java.util.Calendar;

import com.parse.starter.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djchen.model.DateTimeHelper;

public class SwipeDateView extends LinearLayout implements GestureDetector.OnGestureListener{

	public interface DateChangeListener {
		void onDateChanged(String s, String e);
	}
	Button left;
	Button right;
	TextView date;
	DateTimeHelper dateTime;
	GestureDetector gesture;
	public DateChangeListener mListener = null;
	
	public SwipeDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.swipe_date_view, this, true);
		
		LinearLayout container = (LinearLayout)this.getChildAt(0);
		
		left = (Button)container.getChildAt(0);
		right = (Button)container.getChildAt(2);
		date = (TextView)container.getChildAt(1);
		dateTime = new DateTimeHelper(Calendar.getInstance());
		date.setText(dateTime.getReadableDateString());
		gesture = new GestureDetector(context,this);
		gesture.setIsLongpressEnabled(false);
		
		this.addBtnClickState();
		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				previousMonth();	
			}
			
		});
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextMonth();
			}
			
		});
	}
	
	public SwipeDateView(Context context) {
		this(context, null);

	}
	
	
	private void nextMonth() {
		dateTime.nextMonth();
		date.setText(dateTime.getReadableDateString());
		Log.i("DJ", dateTime.getFirstDateOfMonth() + " " + dateTime.getLastDateOfMonth());
		this.mListener.onDateChanged(dateTime.getFirstDateOfMonth(), dateTime.getLastDateOfMonth());
	}
	
	private void previousMonth() {
		dateTime.previousMonth();
		date.setText(dateTime.getReadableDateString());
		Log.i("DJ", dateTime.getFirstDateOfMonth() + " " + dateTime.getLastDateOfMonth());
		this.mListener.onDateChanged(dateTime.getFirstDateOfMonth(), dateTime.getLastDateOfMonth());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		gesture.onTouchEvent(event);
		return true;
	}

	private void addBtnClickState() {
		int[][] states = { 
				new int[]{android.R.attr.state_pressed},
				StateSet.WILD_CARD};
		int[] colors = {this.getResources().getColor(R.color.LightSalmon),
				this.getResources().getColor(R.color.app_primary)};
		ColorStateList colorList = new ColorStateList(states, colors);
		left.setTextColor(colorList);
		right.setTextColor(colorList);
	}
	
	//OnGestureListener Interface Implementation  
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(e1.getX() - e2.getX() > 0)
		{
			this.nextMonth();
		}
		else if (e1.getX() - e2.getX() < 0)
		{
			this.previousMonth();
		}
		return false;
	}
	
	public String getStartDate() {
		return dateTime.getFirstDateOfMonth();
	}
	
	public String getEndDate() {
		return dateTime.getLastDateOfMonth();
	}
}
