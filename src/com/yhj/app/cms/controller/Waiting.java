package com.yhj.app.cms.controller;

import android.app.Activity;
import android.os.Bundle;

import com.yhj.app.cms.R;

public class Waiting extends Activity {
	
	public static Waiting instance = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.waiting);
		instance = this;
	}
}
