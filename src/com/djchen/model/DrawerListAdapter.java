package com.djchen.model;

import java.util.List;

import com.parse.starter.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DrawerListAdapter extends ArrayAdapter<DrawerListItem> {

	private int selectedIndex = -1;
	
	public DrawerListAdapter(Context context, int resource,
			List<DrawerListItem> objects) {
		super(context, resource, objects);
		
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
			LayoutInflater  inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.drawer_list_row, null);
		}
		TextView title = (TextView)row.findViewById(R.id.list_item_title);
		title.setText(this.getItem(arg0).title);
		ImageView image = (ImageView)row.findViewById(R.id.list_item_image);
		image.setBackgroundResource(this.getItem(arg0).resId);
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

	public void setSelectedIndex(int index)	{
		this.selectedIndex = index;
	}
	
	public int getSelectedIndex() {
		return this.selectedIndex;		
	}
	
	public String getSelectedItemTitle() {
		if (this.selectedIndex != -1)
			return this.getItem(this.selectedIndex).title;
		return null;
	}
}
