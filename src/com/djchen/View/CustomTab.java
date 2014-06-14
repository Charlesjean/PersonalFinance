package com.djchen.View;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomTab {
	public final String hightlightColor = "#ff0099cc";
	public final String normalTextColor = "#000000";
	public final String normalIndicatorColor = "#ffffff";
	public Button button;
	public View indicator;
	public TabChangeListener mListener;
	int pos;
	
	public CustomTab(View view, int btnId, int indicatorId, int pos, String btnTitle, TabChangeListener listener) {
		button = (Button)view.findViewById(btnId);
		indicator = (View)view.findViewById(indicatorId);
		this.pos = pos;
		button.setText(btnTitle);
		this.mListener = listener;
		button.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View arg0) {
				mListener.OnTabChanged(CustomTab.this.pos);
			}
			
		});
	}
	
	public void makeHighlight() {
		this.button.setTextColor(Color.parseColor(this.hightlightColor));
		this.indicator.setBackgroundColor(Color.parseColor(this.hightlightColor));
	}
	
	public void unDoHighlight() {
		this.button.setTextColor(Color.parseColor(this.normalTextColor));
		this.indicator.setBackgroundColor(Color.parseColor(this.normalIndicatorColor));
		
	}
		
}
