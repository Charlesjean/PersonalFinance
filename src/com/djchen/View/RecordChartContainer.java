package com.djchen.View;

import java.text.DecimalFormat;

import com.parse.starter.R;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordChartContainer extends FrameLayout{
	
	private RecordChartView chartView;
	private TextView totalText;
	private TextView spentText;
	private TextView percentText;
	public CirclelChartFillAnimation animation;
	private double totalAmount = -1;
	private double spentAmount = 0;
	private double spent_total_percent = 0;
	DecimalFormat df = new DecimalFormat("##.00");
	int simpleFrameControl = 0;
	
	public class CirclelChartFillAnimation implements AnimatorUpdateListener
	{
		private ValueAnimator animator;
		public long duration = 3000;
		
		public CirclelChartFillAnimation() {
			this.duration = 3000;
		}
		public CirclelChartFillAnimation(long dura) {
			this.duration = dura;
		}		
		
		public void startAnimation()
		{
			this.animator = ValueAnimator.ofFloat(0, 1.0f);
			this.animator.setDuration(duration);
			this.animator.addUpdateListener(this);
			this.animator.start();
		}
		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public RecordChartContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_home_page_chart_container, this, true);
		
		FrameLayout container = (FrameLayout)this.getChildAt(0);
		chartView = (RecordChartView)container.getChildAt(0);
		LinearLayout textContainer = (LinearLayout)container.getChildAt(1);
		
		totalText = (TextView)textContainer.getChildAt(1);
		spentText = (TextView)textContainer.getChildAt(3);
		percentText = (TextView)textContainer.getChildAt(4);		
	}

	public void startAnimation() {
		this.simpleFrameControl = 0;
		this.spent_total_percent = this.spentAmount / this.totalAmount * 100;
		this.animation = new CirclelChartFillAnimation(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
								
				float percent =  (Float)animation.getAnimatedValue();
				RecordChartContainer.this.updateAnimation(percent);	
			}
		};
		this.animation.startAnimation();
	}

	public void updateAnimation(float percent) {
		this.simpleFrameControl ++;
		this.chartView.setPercent(percent);
		if(simpleFrameControl % 5 == 0 || percent == 1.0)
		{
			if(this.totalAmount != -1)
			{
				double value1 = Double.parseDouble(df.format(this.totalAmount * percent));
				this.totalText.setText("" + value1);
				double value2 = Double.parseDouble(df.format(this.spentAmount * percent));
				this.spentText.setText("" + value2);
				this.percentText.setText(""+ (int)(spent_total_percent*percent)+"%");
			}
			
		}

	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getSpentAmount() {
		return spentAmount;
	}

	public void setSpentAmount(double spentAmount) {
		this.spentAmount = spentAmount;
	}

}
