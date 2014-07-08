package com.djchen.model;

import java.util.List;

import com.djchen.View.RecordBarView;
import com.parse.starter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BarChartAdapter extends ArrayAdapter<RecordPresentationEntry> {

	LayoutInflater inflater;
	double totalAmount = 0;
	public BarChartAdapter(Context context, int resource, List<RecordPresentationEntry> objects) {
		super(context, resource, objects);
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.getTotalAmount();
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		if(row == null) {
			row = inflater.inflate(R.layout.layout_bar_view_row, null);
		}
		
		TextView category = (TextView)row.findViewById(R.id.bar_chart_category);
		category.setText(this.getItem(position).getCategory());
		TextView amount = (TextView)row.findViewById(R.id.bar_chart_amount);
		amount.setText(this.getItem(position).getAmount() + "");
		RecordBarView colorView = (RecordBarView)row.findViewById(R.id.bar_chart_color_view);
		colorView.setColor(this.getItem(position).getColor());	
		colorView.setPercent(this.getItem(position).getAmount() / totalAmount);
		colorView.startAnimation();
		return row;
	}

	private void getTotalAmount() {
		for (int i = 0; i < this.getCount(); ++i)
		{
			this.totalAmount += this.getItem(i).getAmount();
		}
	}
}
