package com.djchen.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djchen.View.AnimatedExpandableListView.AnimatedExpandableListAdapter;
import com.parse.starter.R;

public class StatementListAdapter2 extends AnimatedExpandableListAdapter {

	public ArrayList<Statement> statements;
	public int groupResId;
	public int childResId;
	public int childHeadResId;
	public Context mContext;	
	
	public StatementListAdapter2 (Context context, ArrayList<Statement> _statements, int groupId, int childId, int childHeadId) {
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
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
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
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public View getRealChildView(int groupPosition, int childPosition,
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
	public int getRealChildrenCount(int groupPosition) {
		return this.statements.get(groupPosition).getRecordCount();
	}

}
