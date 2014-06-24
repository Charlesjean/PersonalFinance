package com.djchen.model;

import java.util.ArrayList;

import com.djchen.database.Record;
public class Statement {
	private String date;
	private double totalCost;
	private ArrayList<Record> userRecords = null;
	private ArrayList<Record> associatedUserRecords = null;
	
	public Statement(String _date) {
		this.date = _date;
		this.totalCost = 0;
		this.userRecords = new ArrayList<Record>();
		this.associatedUserRecords = new ArrayList<Record>();
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getTotalCose() {
		
		this.totalCost = 0;
		if (this.userRecords.size() > 0)
			for (Record record : this.userRecords) {
				this.totalCost += record.getAmount();
			}
		if (this.associatedUserRecords.size() > 0) {
			for (Record record : this.associatedUserRecords) {
				this.totalCost += record.getAmount();
			}
		}
		return totalCost;
	}
	
	public void setTotalCose(double totalCose) {
		this.totalCost = totalCose;
	}
	
	public ArrayList<Record> getUserRecords() {
		return userRecords;
	}
	
	public void setUserRecords(ArrayList<Record> userRecords) {
		this.userRecords = userRecords;
	}
	
	public ArrayList<Record> getAssociatedUserRecords() {
		return associatedUserRecords;
	}
	
	public void setAssociatedUserRecords(ArrayList<Record> associatedUserRecords) {
		this.associatedUserRecords = associatedUserRecords;
	}
	
	public void addCurrentUserRecord(Record record) {
		this.userRecords.add(record);
	}
	
	public void addAssociateUserRecord(Record record) {
		this.associatedUserRecords.add(record);
	}
	
	public int getRecordCount() {
		return this.userRecords.size() + this.associatedUserRecords.size();
	}
	
	public double getUserTotalAmount() {
		
		double totalCost = 0;
		if (this.userRecords.size() > 0)
			for (Record record : this.userRecords) {
				totalCost += record.getAmount();
			}
		return totalCost;
	}
	
	public double getAssociateUserAmount() {
		double totalCost = 0;
		if (this.associatedUserRecords.size() > 0) {
			for (Record record : this.associatedUserRecords) {
				totalCost += record.getAmount();
			}
		}
		
		return totalCost;
	}
	


}
