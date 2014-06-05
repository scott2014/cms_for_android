package com.yhj.app.cms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yhj.app.cms.controller.Waiting;
import com.yhj.app.cms.model.task.LoginTask;

public class Login extends Activity {
	
	// login button
	private Button mLoginBtn = null;
	
	private EditText mUsername = null;
	
	private EditText mPassword = null;
	
	private TextView mTipView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        this.mUsername = (EditText) this.findViewById(R.id.username);
        
        this.mPassword = (EditText) this.findViewById(R.id.password);
        
        this.mLoginBtn = (Button) this.findViewById(R.id.loginBtn);
        
        this.mTipView = (TextView) this.findViewById(R.id.login_tip);
        
        this.mLoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				Log.i("username",username);
				Log.i("password",password);
				
				LoginTask task = new LoginTask(Login.this,mTipView);
				task.execute(username,password);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}


}
