package com.djchen.model;

import android.graphics.Color;

public class RecordPresentationEntry {
	private String category = null;
	private double amount = 0.0;
	private int color ;
	
	public RecordPresentationEntry() {
		
	}
	public RecordPresentationEntry(String category, double amount, int color) {
		this.category = category;
		this.amount = amount;
		this.color = color;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

}
