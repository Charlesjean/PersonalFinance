package com.parse.starter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

/**
 * Created by Administrator on 2014/9/1.
 */
public class SignFragment extends Fragment {

    EditText mUserName;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mLoginBtn;
    Button mRegisterBtn;
    boolean mIsRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.sign_in_screen, container, false);
        mUserName = (EditText)view.findViewById(R.id.user_name);
        mPassword = (EditText)view.findViewById(R.id.user_passwd);
        mConfirmPassword = (EditText)view.findViewById(R.id.user_passwd2);
        mLoginBtn = (Button)view.findViewById(R.id.login_confirm);
        mRegisterBtn = (Button)view.findViewById(R.id.register);


        mLoginBtn.setOnClickListener(new loginClickListener());
        mRegisterBtn.setOnClickListener(new registerListener());
        return view;
    }

     class loginClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            String userName = mUserName.getText().toString();
            String password = mPassword.getText().toString();
            //TODO need to confirm with user to verigy user info
            if (mIsRegister) {

            }
            else {
                
            }

        }
    }

     class registerListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO need to show register screen
            mConfirmPassword.setVisibility(View.VISIBLE);
            mLoginBtn.setText(getActivity().getResources().getString(R.string.register));
            mIsRegister = true;
        }
    }
}
