package com.parse.starter;


import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVAnalytics;
public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//ID 53p6sa5rm7jwt9ygbsm7ng6pjqf8kxots31emagen3wvhbn4
		//KEY 1rl24m3nfmal9tua1zlib21sg9vhqom5tq3eh1gg3id507lo
		
		AVOSCloud.initialize(this, "53p6sa5rm7jwt9ygbsm7ng6pjqf8kxots31emagen3wvhbn4", "1rl24m3nfmal9tua1zlib21sg9vhqom5tq3eh1gg3id507lo");
	
	}

}
