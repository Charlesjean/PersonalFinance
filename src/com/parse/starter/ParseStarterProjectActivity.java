package com.parse.starter;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVObject;
import com.djchen.model.DrawerListAdapter;
import com.djchen.model.DrawerListItem;


public class ParseStarterProjectActivity extends FragmentActivity {
	/** Called when the activity is first created. */
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;	
	private ActionBarDrawerToggle mDrawerToggle;
    private Button mLoginBtn;
	
	
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
		this.setUpFragment(0);

        mLoginBtn = (Button)frame.findViewById(R.id.sign_in_btn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParseStarterProjectActivity.this, LoginActivity.class);
                ParseStarterProjectActivity.this.startActivity(intent);
            }
        });
		
		AVAnalytics.trackAppOpened(getIntent());
		
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
        String listItemStr[] = this.getResources().getStringArray(R.array.drawer_list_item);
		listItem.add(new DrawerListItem(R.drawable.icon_1, listItemStr[0]));
		listItem.add(new DrawerListItem(R.drawable.icon_2, listItemStr[1]));
		listItem.add(new DrawerListItem(R.drawable.icon_3, listItemStr[2]));
		listItem.add(new DrawerListItem(R.drawable.icon_4, listItemStr[3]));
		listItem.add(new DrawerListItem(R.drawable.icon_5, listItemStr[4]));
		listItem.add(new DrawerListItem(R.drawable.icon_6, listItemStr[5]));
		mDrawerList.setAdapter(new DrawerListAdapter(this, R.layout.drawer_list_row, listItem));
		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				((DrawerListAdapter)mDrawerList.getAdapter()).setSelectedIndex(pos);
				ParseStarterProjectActivity.this.getActionBar().setTitle(((DrawerListAdapter)mDrawerList.getAdapter()).getSelectedItemTitle());
				mDrawerLayout.closeDrawers();
				setUpFragment(pos);
			}
		});
	}
	
	private void setUpActionBar() {
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setHomeButtonEnabled(true);
		actionbar.setTitle(R.string.app_name);
	}
	
	private void setUpFragment(int pos) {

		CustomFragment fragment = null;
		switch(pos) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			break;
		case 2:
			fragment  = new StatementFragment();
			break;
		case 3:
			break;
		case 4:
			fragment = new BudgetFragment();
			break;
		case 5:
			break;
		case 6:
			break;
		
		}
		if (fragment != null) {
			FragmentManager fm = this.getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.container, fragment);
			ft.commit();
		}
	}	
	
}
