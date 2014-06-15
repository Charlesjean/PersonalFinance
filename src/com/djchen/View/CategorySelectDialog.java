package com.djchen.View;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class CategorySelectDialog extends DialogFragment {
	public interface CategorySelectListener {
		public void onItemSelected(ArrayList<String> items);
	}

	public final static String CATEGORY_LISTENER = "category_select_listener";
	public final static String ITEMS = "ITEMS";
	public final static String SELECTED_ITEM = "SELECTED_ITEM";
	
	private CategorySelectListener mListener;
	public ArrayList<String> mSelectedItem;
	
	public static CategorySelectDialog newInstance(CategorySelectListener listener, String[] items, int selectedItem) {
		CategorySelectDialog dialog = new CategorySelectDialog();
		
		Bundle bundle = new Bundle();
		bundle.putSerializable(CATEGORY_LISTENER, (Serializable)listener);
		bundle.putStringArray(ITEMS, items);
		bundle.putInt(SELECTED_ITEM, selectedItem);
		dialog.setArguments(bundle);		
		return dialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		this.mSelectedItem = new ArrayList<String>();
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle("选择类型");
		
		final String[] items = this.getArguments().getStringArray(ITEMS);
		mListener = (CategorySelectListener)this.getArguments().getSerializable(CATEGORY_LISTENER);
		int defaultItem = this.getArguments().getInt(SELECTED_ITEM);
		builder.setSingleChoiceItems(items, defaultItem, new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				mSelectedItem.add(items[arg1]);
			}
			
		});
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				mListener.onItemSelected(mSelectedItem);
				
			}
			
		});
		
		return builder.create();
	}
	
	
}
