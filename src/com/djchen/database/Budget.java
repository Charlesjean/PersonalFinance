package com.djchen.database;


public class Budget {
	
	private String budget_type;
	private double budget_amount;
	
	public Budget(String type, double amount) {
		this.setBudgetType(type);
		this.setBudgetAmount(amount);
	}

	public String getBudgetType() {
		return budget_type;
	}

	public void setBudgetType(String budget_type) {
		this.budget_type = budget_type;
	}

	public double getBudgetAmount() {
		return budget_amount;
	}

	public void setBudgetAmount(double budget_amount) {
		this.budget_amount = budget_amount;
	}
	
	
}
