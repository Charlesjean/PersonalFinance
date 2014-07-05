package com.djchen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;

public class RecordChartModel {
	
	private ArrayList<RecordPresentationEntry> mEntryList;
	private ArrayList<Double> mEntryPercent;
	
	public RecordChartModel(ArrayList<RecordPresentationEntry> list) {
		mEntryList =  list;
		this.init();
	}
	
	public void updateModel(ArrayList<RecordPresentationEntry> newList) {
		mEntryList = newList;
		this.init();
	}
	
	//calculate percentage of each category
	private void init() {
		if(mEntryList == null || mEntryList.size() == 0)
			return;
		double totalAmount = this.getTotalAmount();
		if(totalAmount <= 0)
			return;
		
		this.mEntryPercent = new ArrayList<Double>();
		for (int i = 0; i < mEntryList.size(); ++i) {
			double percent = mEntryList.get(i).getAmount() / totalAmount;
			this.mEntryPercent.add(percent);
		}
	}
	
	public double getTotalAmount() {
		double totalAmount = 0;
		for (RecordPresentationEntry entry : this.mEntryList) 
			totalAmount += entry.getAmount();
		
		return totalAmount;
	}
	
	public ArrayList<Double> getEntryPercent() {
		return this.mEntryPercent;
	}
	
	public RecordPresentationEntry getEntry(int pos) {
		return this.mEntryList.get(pos);
	}
	
	public HashMap<Double, Color> getPercentAndColor() {
		HashMap<Double, Color> tmpMap = new HashMap<Double, Color>();
		double subtotal = 0;
		for (int i = 0; i < this.mEntryPercent.size(); ++i) {
			subtotal += this.mEntryPercent.get(i);
			Color color = this.mEntryList.get(i).getColor();
			tmpMap.put(subtotal, color);
		}
		return tmpMap;		
	}
	
}
