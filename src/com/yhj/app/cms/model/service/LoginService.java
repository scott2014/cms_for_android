package com.yhj.app.cms.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LoginService extends Service {
	
	private LoginBinder mBinder = new LoginBinder();
	
	private String mUsername = null;
	private String mPassword = null;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}


	@Override
	public IBinder onBind(Intent intent) {
		return this.mBinder;
	}
	
	
	public class LoginBinder extends Binder {
		
		public void login() {
			
		}
	}
}
