package com.yhj.app.cms.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.yhj.app.cms.R;
import com.yhj.app.cms.model.service.LoginService;
import com.yhj.app.cms.model.service.LoginService.LoginBinder;

public class Waiting extends Activity {
	
	private LoginService.LoginBinder mBinder = null;
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			mBinder = (LoginBinder) binder;
			mBinder.login();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.waiting);
		
		Intent intent = new Intent(this,LoginService.class);
		startService(intent);
	}
}
