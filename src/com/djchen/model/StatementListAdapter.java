package com.djchen.model;

import java.util.ArrayList;

import com.parse.starter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class StatementListAdapter extends BaseExpandableListAdapter {

	public ArrayList<Statement> statements;
	public int groupResId;
	public int childResId;
	public int childHeadResId;
	public Context mContext;
	public StatementListAdapter(Context context, ArrayList<Statement> _statements, int groupId, int childId, int childHeadId) {
		this.statements = _statements;
		this.groupResId = groupId;
		this.childResId = childId;
		this.childHeadResId = childHeadId;
		this.mContext = context;
	}
	@Override
	public int getGroupCount() {
		return this.statements.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.statements.get(groupPosition).getRecordCount() ;//+ 2;//including two child head view
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.statements.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View group = convertView;
		if(group == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			group = inflater.inflate(R.layout.statement_group_view, null);
		}
		//TextView dateText = (TextView)group.findViewById(R.id.group_date);
		//dateText.setText(this.statements.get(groupPosition).getDate());
		TextView amountText = (TextView)group.findViewById(R.id.group_total_amount);
		amountText.setText("??" + this.statements.get(groupPosition).getTotalCose()+"");
		TextView userAmount = (TextView)group.findViewById(R.id.group_user_total);
		userAmount.setText("??" + this.statements.get(groupPosition).getUserTotalAmount()+"");
		TextView associateUserAmount = (TextView)group.findViewById(R.id.group_associateuser_total);
		associateUserAmount.setText("??" + this.statements.get(groupPosition).getAssociateUserAmount()+"");
		return group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View child = convertView;
		if (child == null ) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = inflater.inflate(R.layout.statement_child_view, null);
			
		}
		TextView typeText = (TextView)child.findViewById(R.id.child_record_type);
		typeText.setText(this.statements.get(groupPosition).getUserRecords().get(childPosition).getCategory()+"");
		TextView recordAmount = (TextView)child.findViewById(R.id.child_record_amount);
		recordAmount.setText("??" + this.statements.get(groupPosition).getUserRecords().get(childPosition).getAmount()+"");

		return child;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
