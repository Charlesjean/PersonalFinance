package com.djchen.View;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class EditTextBackEvent extends EditText {



    public EditTextBackEvent(Context context) {
        super(context);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
    	if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {// this will be called when back pressed
    	    return false;  
    	  }
    	  return super.dispatchKeyEvent(event);
    }
      
    
}
