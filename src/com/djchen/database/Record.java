package com.djchen.database;

public class Record {
	
	//TABLE_RECORD
	public final static String RECORD_TYPE = "type";
	public final static String RECORD_AMOUNT = "amount";
	public final static String RECORD_ACCOUNT = "account";
	public final static String RECORD_DATE = "create_at";
	public final static String RECORD_COMMENT = "comment";
	public final static String RECORD_CATEGORY = "category";
	
	public final static int COST = 0;
	public final static int INCOME = 1;
	
	public final static int CASH = 2;
	public final static int CARD = 3;
	public final static int WEB = 4;
	
	private int id;
	private int recordType;
	private double amount;
	private String date;
	private String comment;
	private int accountType;
	private String category;
	
	
	public Record(int id) {
		this.setId(id);
	}
	
	public Record (int id, int type, double amount, String date, int accountType, String category) {
		this.setId(id);
		this.setRecordType(type);
		this.setAmount(amount);
		this.setDate(date);
		this.setComment("");
		this.setAccountType(accountType);
		this.setCategory(category);
	}
	
	public Record(int id, int type, double amount, String date, String comment, int accountType, String category) {
		this.setId(id);
		this.setRecordType(type);
		this.setAmount(amount);
		this.setDate(date);
		this.setComment(comment);
		this.setAccountType(accountType);
		this.setCategory(category);
	}
	
	public Record(int type, double amount, String date, String comment, int accountType, String category) {
		this.setRecordType(type);
		this.setAmount(amount);
		this.setDate(date);
		this.setComment(comment);
		this.setAccountType(accountType);
		this.setCategory(category);
	}
	
	public Record(int type, double amount, String date)//, String comment, int accountType)
	{
		this.setRecordType(type);
		this.setAmount(amount);
		this.setDate(date);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String toString() {
		return this.id + " " + this.amount + " " + this.accountType + " " + this.comment + " " + this.date + " " + this.category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
