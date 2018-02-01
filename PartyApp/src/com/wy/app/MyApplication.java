package com.wy.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wy.model.UserVo;




public class MyApplication extends Application {
	
	private static MyApplication mApplication;
	
	public final String PREF_USERNAME = "zhu_id";
	private static SharedPreferences.Editor editor;
	private int zhu_id = -1;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication = this;
	}
	public static MyApplication getInstance() {
		
		return mApplication;
	}

	public int getZhID() {
		if (zhu_id == -1) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mApplication);
			zhu_id = preferences.getInt(PREF_USERNAME, -1);
		}
		return zhu_id;
	}
	public void setZhID(int zhu_id) {
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mApplication);
		editor = preferences.edit();
		
		editor.putInt(PREF_USERNAME, zhu_id);
		editor.commit();
		
	}
	private UserVo userVo;
	public UserVo getUser(){
		return userVo;
	}
	
	
	public void setUser(UserVo userVo){
		this.userVo = userVo;
	}
	
	
	
	
	
	
}
