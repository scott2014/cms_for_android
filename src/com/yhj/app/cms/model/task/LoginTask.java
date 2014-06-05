package com.yhj.app.cms.model.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;

import com.yhj.app.cms.R;
import com.yhj.app.cms.controller.Home;
import com.yhj.app.cms.controller.Waiting;
import com.yhj.app.cms.model.constant.LoginStatus;
import com.yhj.app.cms.model.constant.UrlConst;

public class LoginTask extends AsyncTask<String,Integer,Integer> {
	
	private Context mContext = null;
	
	private TextView mTipView = null;
	
	private Resources mRs = null;
	
	public LoginTask(Context context,TextView tipView) {
		this.mContext = context;
		this.mTipView = tipView;
		this.mRs = context.getResources();
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		
		String username = arg0[0];
		String password = arg0[1];
		
		HttpPost post = new HttpPost(UrlConst.LOGIN);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		NameValuePair um = new BasicNameValuePair("username", username);
		NameValuePair up = new BasicNameValuePair("password", password);
		
		params.add(um);
		params.add(up);
		
		HttpClient client = new DefaultHttpClient();
		
		HttpEntity entity = null;
		
		HttpResponse response = null;
		
		StringBuilder result  = new StringBuilder("");
		
		int code = -1;
		
		try {
			entity = new UrlEncodedFormEntity(params,"utf-8");
			
			post.setEntity(entity);
			
			response = client.execute(post);
			
			InputStream is = response.getEntity().getContent();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String str = null;
			
			while ((str = br.readLine()) != null) {
				result.append(str);
			}
			
			br.close();
			is.close();
			
			JSONObject json = new JSONObject(result.toString());
			
			code = json.getInt("resultCode");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}

	@Override
	protected void onPostExecute(Integer code) {
		super.onPostExecute(code);
		
		if (code == LoginStatus.CONNECT_FAIL) {
			Waiting.instance.finish();
			this.mTipView.setText(this.mRs.getString(R.string.connection_fail));
		}
		
		if (code == LoginStatus.USER_NOT_EXIST) {
			Waiting.instance.finish();
			this.mTipView.setText(this.mRs.getString(R.string.username_not_exist));
		}
		
		if (code == LoginStatus.PASSWORD_NOT_CORRECT) {
			Waiting.instance.finish();
			this.mTipView.setText(this.mRs.getString(R.string.password_not_correct));
		}
		
		if (code == LoginStatus.LOGIN_OK) {
			Waiting.instance.finish();
			Intent intent = new Intent(this.mContext,Home.class);
			
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.mContext.startActivity(intent);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		Intent intent = new Intent(mContext,Waiting.class);
		
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.mContext.startActivity(intent);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	
}
