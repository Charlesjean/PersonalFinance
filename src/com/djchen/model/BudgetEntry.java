package com.djchen.model;

public class BudgetEntry {

	private String budget_category;
	private double budget_amount;
	private double budget_used;
	
	public BudgetEntry(String category, double amount, double used) {
		this.budget_category = category;
		this.budget_amount = amount;
		this.budget_used = used;
	}
	
	public String getCategory() {
		return this.budget_category;
	}
	
	public double getBudgetAmount() {
		return this.budget_amount;
	}
	
	public double getBudgetUsed() {
		return this.budget_used;
	}
}
