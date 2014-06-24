package com.djchen.database;

import java.util.ArrayList;

import com.djchen.model.Statement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseManipulation {
	
	public class DateCountPair {
		public int count;
		public String date;
		public DateCountPair(int count, String date) {
			this.count = count;
			this.date = date;
		}
		
		public String toString() {
			return date + " " + count;
		}
	}
	
	public Context mcontext;
	private DataBaseHelper dbHelper;
	public DataBaseManipulation(Context icontext) {
		
		this.mcontext = icontext;
		dbHelper = new DataBaseHelper(mcontext);
	}
	
	public SQLiteDatabase openReadableDB()	{
		return dbHelper.getReadableDatabase();
	}
	
	public SQLiteDatabase openWritableDB() {
		return dbHelper.getWritableDatabase();
	}
	
	public void closeDB() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		if (db != null && db.isOpen()) 
			db.close();
	}
	
	public long addRecord(Record record) {
		ContentValues value = new ContentValues();
		value.put(Record.RECORD_TYPE, record.getRecordType());
		value.put(Record.RECORD_AMOUNT, record.getAmount());
		value.put(Record.RECORD_ACCOUNT, record.getAccountType());
		value.put(Record.RECORD_DATE, record.getDate());
		value.put(Record.RECORD_COMMENT, record.getComment());
		value.put(Record.RECORD_CATEGORY, record.getCategory());
		
		SQLiteDatabase db = this.openWritableDB();
		long id = db.insert(DataBaseHelper.TABLE_RECORD, null, value);
		db.close();
		
		return id;
		
	}
	
	public Record getRecord(long record_id) {
		String selectQuery = " SELECT * FROM " + DataBaseHelper.TABLE_RECORD 
				+ " WHERE " + DataBaseHelper.KEY_ID + " = " + record_id;
		Log.i("DATABASE", selectQuery);
		
		SQLiteDatabase db = this.openReadableDB();
		Cursor c = db.rawQuery(selectQuery, null);
		if(c != null)
			c.moveToFirst();
		Record record = new Record(c.getInt(c.getColumnIndex(DataBaseHelper.KEY_ID)),
				c.getInt(c.getColumnIndex(Record.RECORD_TYPE)),
				c.getDouble(c.getColumnIndex(Record.RECORD_AMOUNT)),
				c.getString(c.getColumnIndex(Record.RECORD_DATE)),
				c.getString(c.getColumnIndex(Record.RECORD_COMMENT)),
				c.getInt(c.getColumnIndex(Record.RECORD_ACCOUNT)),
				c.getString(c.getColumnIndex(Record.RECORD_CATEGORY)));
		
		c.close();
		db.close();
		return record;
	}
	
	public void dropTable(String tableName) {
		this.openWritableDB().execSQL("DROP TABLE IF EXISTS " + tableName);
	}
	
	//get statement from database according to date period
	public ArrayList<Statement> queryStatements(String fromDate, String toDate) {
		String querySql = " SELECT " + Record.RECORD_TYPE + ", " + Record.RECORD_AMOUNT
				+ ", " + Record.RECORD_ACCOUNT + ", " + Record.RECORD_CATEGORY + ", " + Record.RECORD_DATE + " FROM " +
				DataBaseHelper.TABLE_RECORD + " WHERE " + Record.RECORD_DATE + " BETWEEN '" 
				+ fromDate + "'" + "" + " AND '" + toDate + "'" + "" + " ORDER BY " + Record.RECORD_DATE;
		
		//get record number of each day first
		ArrayList<DateCountPair> recordNumber = this.getCountByDate(fromDate, toDate);
		SQLiteDatabase db = this.openReadableDB();
		Cursor c = db.rawQuery(querySql, null);
		if (c.getCount() == 0)
			return new ArrayList<Statement>();		

		c.moveToFirst();
		//group records according to date
		int count;
		ArrayList<Statement> statements = new ArrayList<Statement>();
		for (int i = 0; i < recordNumber.size(); ++i) {
			count = recordNumber.get(i).count;
			Statement dayStatement = new Statement(recordNumber.get(i).date);	
			for(int j = 0; j < count; ++j) {
				Record record = new Record(0,
						c.getInt(c.getColumnIndex(Record.RECORD_TYPE)),
						c.getDouble(c.getColumnIndex(Record.RECORD_AMOUNT)),
						c.getString(c.getColumnIndex(Record.RECORD_DATE)),
						"",
						c.getInt(c.getColumnIndex(Record.RECORD_ACCOUNT)),
						c.getString(c.getColumnIndex(Record.RECORD_CATEGORY)));
				dayStatement.addCurrentUserRecord(record);		
				c.moveToNext();
			}
			statements.add(dayStatement);
		}
		
		//TODO get associated user record from database
		c.close();		
		db.close();
		return statements;
	}
	
	//get record count for each date between fromDate and toDate
	public ArrayList<DateCountPair> getCountByDate(String fromDate, String toDate) {
		String queryString = "SELECT COUNT(*) ," + Record.RECORD_DATE + " FROM " + DataBaseHelper.TABLE_RECORD
				+ " WHERE " + Record.RECORD_DATE + " BETWEEN '" + fromDate + "' " + " AND '" + toDate +"' " 
				+ " GROUP BY " + Record.RECORD_DATE + " ORDER BY " + Record.RECORD_DATE;
		SQLiteDatabase db = this.openReadableDB();
		Cursor c = db.rawQuery(queryString, null);
		
		if (c.getCount() == 0) 
			return null;
		
		c.moveToFirst();		
		ArrayList<DateCountPair> array = new ArrayList<DateCountPair>();
		do {
			DateCountPair dcp = new DateCountPair(c.getInt(0), c.getString(1));
			array.add(dcp);
			Log.i("DJ", dcp.toString());
		}while(c.moveToNext());
		
		c.close();
		db.close();
		return array;
	}
	
	public void contentTest() {
		String querySql = " SELECT " + " * " + " FROM " +
				DataBaseHelper.TABLE_RECORD;
		
		SQLiteDatabase db = this.openReadableDB();
		Cursor c = db.rawQuery(querySql, null);
		if (c != null)
			c.moveToFirst();
		
		do {
			Record record = new Record(c.getInt(c.getColumnIndex(DataBaseHelper.KEY_ID)),
					c.getInt(c.getColumnIndex(Record.RECORD_TYPE)),
					c.getDouble(c.getColumnIndex(Record.RECORD_AMOUNT)),
					c.getString(c.getColumnIndex(Record.RECORD_DATE)),
					"",
					c.getInt(c.getColumnIndex(Record.RECORD_ACCOUNT)),
					c.getString(c.getColumnIndex(Record.RECORD_CATEGORY)));
			Log.i("DJTest", record.toString());
			
		}while(c.moveToNext());
		c.close();
		db.close();
		
	}
	
	
}
