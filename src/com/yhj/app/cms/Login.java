package com.yhj.app.cms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yhj.app.cms.controller.Waiting;

public class Login extends Activity {
	
	// login button
	private Button mLoginBtn = null;
	
	private EditText mUsername = null;
	
	private EditText mPassword = null;
	
	//login request code
	private final int LOGIN_REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        this.mUsername = (EditText) this.findViewById(R.id.username);
        
        this.mPassword = (EditText) this.findViewById(R.id.password);
        
        this.mLoginBtn = (Button) this.findViewById(R.id.loginBtn);
        
        this.mLoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Login.this,Waiting.class);
				
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				
				startActivityForResult(intent, LOGIN_REQUEST_CODE);
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
