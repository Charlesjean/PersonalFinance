package com.djchen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	private final static String DATA_BASE_NAME = "personalfinance";
	private final static int DATA_BASE_VERSION = 1;
	
	//table names
	public final static String TABLE_RECORD = "records";
	public final static String TABLE_BUDGET = "budgets";
	
	//table budget colums
	public final static String BUDGET_TYPE = "budget_name";
	public final static String BUDGET_AMOUNT="amount";
	
	//common columns
	public final static String KEY_ID = "id";
	
	//create table sql 
	private final String CREATE_TABLE_RECORD = "CREATE TABLE "
			+ TABLE_RECORD + " ( " + KEY_ID + " INTEGER PRIMARY KEY," +
			Record.RECORD_TYPE + " INTEGER," + Record.RECORD_CATEGORY + " TEXT," + Record.RECORD_AMOUNT + " REAL," 
			+ Record.RECORD_ACCOUNT + " TEXT," 
			+ Record.RECORD_COMMENT + " TEXT," + Record.RECORD_DATE + " TEXT " + ")";
	
	private final String CREATE_BUDGET_TABLE = "CREATE TABLE "
			+ TABLE_BUDGET + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + 
			BUDGET_TYPE + " TEXT, " + BUDGET_AMOUNT + " REAL )";
			;

	public DataBaseHelper(Context context) {
		super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_RECORD);
		db.execSQL(CREATE_BUDGET_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
	}

}
