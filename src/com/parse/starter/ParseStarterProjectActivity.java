package com.parse.starter;

import java.util.ArrayList;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.djchen.model.DrawerListAdapter;
import com.djchen.model.DrawerListItem;
import com.parse.ParseAnalytics;

public class ParseStarterProjectActivity extends FragmentActivity {
	/** Called when the activity is first created. */
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;	
	private ActionBarDrawerToggle mDrawerToggle;
	
	
	//Tmp use variable
	ArrayList<DrawerListItem> listItem;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mDrawerLayout = (DrawerLayout)this.findViewById(R.id.drawer_layout);
		View frame = (View)this.findViewById(R.id.drawer_frame);
		mDrawerList = (ListView)frame.findViewById(R.id.drawer_list);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawopen, R.string.drawclose) {
		
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// TODO Auto-generated method stub
				super.onDrawerSlide(drawerView, slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				// TODO Auto-generated method stub
				super.onDrawerStateChanged(newState);
			}
			
		};
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		this.setUpDrawerListItem();
		this.setUpActionBar();
		this.setUpHomeFragment();
		
		ParseAnalytics.trackAppOpened(getIntent());
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		mDrawerToggle.syncState();
		super.onPostCreate(savedInstanceState);
	}
	
	private void setUpDrawerListItem() {
		listItem = new ArrayList<DrawerListItem>();
		listItem.add(new DrawerListItem(R.drawable.icon_1, "主页"));
		listItem.add(new DrawerListItem(R.drawable.icon_2, "账本"));
		listItem.add(new DrawerListItem(R.drawable.icon_3, "流水"));
		listItem.add(new DrawerListItem(R.drawable.icon_4, "统计"));
		listItem.add(new DrawerListItem(R.drawable.icon_5, "预算"));
		listItem.add(new DrawerListItem(R.drawable.icon_6, "设置"));
		mDrawerList.setAdapter(new DrawerListAdapter(this, R.layout.drawer_list_row, listItem));
		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				((DrawerListAdapter)mDrawerList.getAdapter()).setSelectedIndex(arg2);
				ParseStarterProjectActivity.this.getActionBar().setTitle(((DrawerListAdapter)mDrawerList.getAdapter()).getSelectedItemTitle());
				mDrawerLayout.closeDrawers();
				
			}
		});
	}
	
	private void setUpActionBar() {
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setHomeButtonEnabled(true);
		actionbar.setTitle(R.string.app_name);
	}
	
	private void setUpHomeFragment() {
		FragmentManager fm = this.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment homeFrag = new HomeFragment();
		ft.add(R.id.container, homeFrag);
		ft.commit();
	}	
}
