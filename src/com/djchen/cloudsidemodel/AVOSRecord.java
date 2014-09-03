package com.djchen.cloudsidemodel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import com.djchen.database.Record;

@AVClassName("record")
public class AVOSRecord extends AVObject {

	public AVOSRecord() {
		
	}
	
	public int getRecordType() {
		return this.getInt(Record.RECORD_TYPE);
	}
	
	public void setRecordType(int type) {
		this.put(Record.RECORD_TYPE, type);
	}
	
	public double getAmount() {
		return this.getDouble(Record.RECORD_AMOUNT);
	}
	
	public void setAmount(double amount) {
		this.put(Record.RECORD_AMOUNT, amount);
	}
	
	public String getDate() {
		return this.getString(Record.RECORD_DATE);
	}
	
	public void setDate(String date) {
		this.put(Record.RECORD_DATE, date);
	}
	
	public String getComment() {
		return this.getString(Record.RECORD_COMMENT);
	}
	
	public void setComment(String comment) {
		this.put(Record.RECORD_COMMENT, comment);
	}
	
	public int getAccountType() {
		return this.getInt(Record.RECORD_ACCOUNT);
	}
	
	public void setAccountType(int value) {
		this.put(Record.RECORD_ACCOUNT, value);
	}
	
	
	
}
