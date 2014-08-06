package com.djchen.View;

import com.parse.starter.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomTab {
	public Button button;
	public View indicator;
	public TabChangeListener mListener;
	private Context context;
	int pos;
	
	public CustomTab(View view, int btnId, int indicatorId, int pos, String btnTitle, TabChangeListener listener, Context context) {
		button = (Button)view.findViewById(btnId);
		indicator = (View)view.findViewById(indicatorId);
		this.pos = pos;
		button.setText(btnTitle);
		this.mListener = listener;
		this.context = context;
		button.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View arg0) {
				mListener.OnTabChanged(CustomTab.this.pos);
			}
			
		});
	}
	
	public void makeHighlight() {
		this.button.setTextColor(this.context.getResources().getColor(R.color.tab_indicator_highlightcolor));
		this.indicator.setBackgroundColor(this.context.getResources().getColor(R.color.tab_indicator_highlightcolor));
	}
	
	public void unDoHighlight() {
		this.button.setTextColor(this.context.getResources().getColor(R.color.tab_text_color));
		this.indicator.setBackgroundColor(this.context.getResources().getColor(R.color.app_body_color));
		
	}
		
}
