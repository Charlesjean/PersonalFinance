package com.djchen.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

public class RecordBarView extends View implements AnimatorUpdateListener{

	private int color_id;
	private RectF rect;
	private Paint paint;
	private double percent;
	private long duration = 3000;
	private ValueAnimator animator;
	private double animatedPercent;
	public RecordBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		rect = new RectF();
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		rect.set(0, 0, (int)(this.getWidth() * this.animatedPercent), this.getHeight());
		paint.setColor(this.getResources().getColor(color_id));
		canvas.drawRect(rect, paint);
		super.onDraw(canvas);
	}
	
	public void setColorRes(int resId)
	{
		this.color_id = resId;
	}
	
	public void setPercent(double percent)
	{
		this.percent = percent;
	}

	public void startAnimation() {
		animator = ValueAnimator.ofFloat(0.0f, (float)percent);
		animator.setDuration(duration);
		animator.addUpdateListener(this);
		animator.start();
	}
	;
	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		this.animatedPercent = (Float)animation.getAnimatedValue();
		this.invalidate();
	}
	

}
