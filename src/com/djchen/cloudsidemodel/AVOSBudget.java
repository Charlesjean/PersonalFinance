package com.djchen.cloudsidemodel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("budget")
public class AVOSBudget extends AVObject {
	final static String BUDGET_TYPE = "type";
	final static String BUDGET_AMOUNT="amout";
	
	public AVOSBudget() {
		
	}
	
	public String getType() {
		return this.getString(AVOSBudget.BUDGET_TYPE);
	}
	
	public void setType(String value) {
		this.put(AVOSBudget.BUDGET_TYPE, value);
	}
	
	public double getAmount() {
		return this.getDouble(AVOSBudget.BUDGET_AMOUNT);
	}
	
	public void setAmount(double value) {
		this.put(AVOSBudget.BUDGET_AMOUNT, value);
	}
	
}
