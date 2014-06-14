package com.djchen.model;
import java.util.ArrayList;

import com.parse.starter.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class TestAdapter implements ListAdapter {

	private Context mContext;
	private ArrayList<String> mListItem;
	private int[] resources = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, 
			R.drawable.icon_5, R.drawable.icon_6};
	public TestAdapter(Context context)
	{
		mContext = context;
		mListItem = new ArrayList<String>();
		mListItem.add("主页");
		mListItem.add("流水");
		mListItem.add("统计");
		mListItem.add("预算");
		mListItem.add("设置");
	}
	
	@Override
	public int getCount() {
		
		return  mListItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return mListItem.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View row = arg1;
		if (row == null) {
			LayoutInflater  inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.drawer_list_row, null);
		}
		TextView title = (TextView)row.findViewById(R.id.list_item_title);
		title.setText(mListItem.get(arg0));
		ImageView image = (ImageView)row.findViewById(R.id.list_item_image);
		image.setBackgroundResource(this.resources[arg0]);
		return row;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

}
