package com.djchen.View;

import com.djchen.model.RecordChartModel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.animation.*;
import android.animation.ValueAnimator.AnimatorUpdateListener;

import com.parse.starter.R;

public class RecordChartView extends View {

	private RecordChartModel mModel = null; 

	//for animation
	private ValueAnimator animator;
	private float percent;
	private int duration = 3000;
	RectF rect = new RectF();
	Paint paint = new Paint();
	private int strokeSize;// = 60;
	private int[] colorArray = {R.color.Chart_Blue, R.color.Chart_Darkgray, R.color.Chart_Green, R.color.Chart_Purple, R.color.Chart_Red, R.color.Chart_Yellow};
	private float[] percents = {50f, 90f, 120f, 210f, 360f};
	
	public RecordChartView(Context context) {
		super(context);
		this.strokeSize = this.getResources().getDimensionPixelSize(R.dimen.circle_width);

	}
	
	public RecordChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.strokeSize = this.getResources().getDimensionPixelSize(R.dimen.circle_width);
	}
	
	public RecordChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.strokeSize = this.getResources().getDimensionPixelSize(R.dimen.circle_width);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int width = this.getWidth();
		int height = this.getHeight();
		int radus = Math.min(width, height) / 2 - strokeSize;
		rect.set(width / 2 - radus, height / 2 - radus, width / 2 + radus, height / 2 + radus);
	    paint.setStrokeWidth(this.strokeSize);
	    paint.setAntiAlias(true);
	    paint.setStrokeCap(Paint.Cap.BUTT);
	    paint.setStyle(Paint.Style.STROKE);
	    
	    for(int i = 0; i < this.percents.length; ++i) 
	    {
    		paint.setColor(this.getResources().getColor(this.colorArray[i]));
	    	if(this.percent*360 > this.percents[i])
	    	{
	    		canvas.drawArc(rect, i==0?0:this.percents[i-1], this.percents[i] - (i==0?0:this.percents[i-1]) , false, paint);
	    	}
	    	else 
	    	{
	    		canvas.drawArc(rect, i==0?0:this.percents[i-1], this.percent * 360 - (i==0?0:this.percents[i-1])  , false, paint);
	    		break;
	    	}
	    }
		super.onDraw(canvas);
	}

	
	public RecordChartModel getmModel() {
		return mModel;
	}

	public void setmModel(RecordChartModel mModel) {
		this.mModel = mModel;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setPercent(float percent) {
		this.percent = percent;
		this.invalidate();
	}

	

}
