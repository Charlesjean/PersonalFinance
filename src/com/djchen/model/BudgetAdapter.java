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

public class BudgetAdapter extends ArrayAdapter<BudgetEntry> {

	private int resource;
	public BudgetAdapter(Context context, int resource,	List<BudgetEntry> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView  = (View)inflater.inflate(this.resource, null);
		}
		
		TextView category = (TextView)convertView.findViewById(R.id.budget_category);
		category.setText(this.getItem(position).getCategory());
		TextView amount = (TextView)convertView.findViewById(R.id.budget_category_amount);
		amount.setText(""+ this.getItem(position).getBudgetAmount());
		TextView left = (TextView)convertView.findViewById(R.id.budget_category_let);
		left.setText(""+ (this.getItem(position).getBudgetAmount() - this.getItem(position).getBudgetUsed()));
		
			
		RecordBarView barView = (RecordBarView)convertView.findViewById(R.id.budget_category_used_to_total);
		if(this.getItem(position).getBudgetAmount() == 0.0)
			barView.setPercent(0);
		else
			barView.setPercent((this.getItem(position).getBudgetAmount() - this.getItem(position).getBudgetUsed()) / this.getItem(position).getBudgetAmount());
		barView.setColorRes(R.color.budget_bar_chart_color);
		barView.startAnimation();
		return convertView;
	}
	
	public double getTotalBudget() {
		double total = 0;
		for(int i = 0; i < this.getCount(); ++i) {
			total += this.getItem(i).getBudgetAmount();
		}
		return total;
	}
	
	public double getTotalSpent() {
		double spent = 0;
		for(int i = 0; i < this.getCount(); ++i) {
			spent += this.getItem(i).getBudgetUsed();
		}
		
		return spent;
		
	}

}
