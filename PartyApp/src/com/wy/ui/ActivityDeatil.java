package com.wy.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.wy.R;
import com.wy.adapter.CLAdapter;
import com.wy.app.MyApplication;
import com.wy.model.GroupVo;
import com.wy.model.MsgVo;
import com.wy.util.HttpUtil;
import com.wy.util.JsonUtils;
import com.wy.util.StringUtils;

/**
 * Created by Cheng Hao on 2017-1-20 下午11:08:48
 * Describe : Party detail
 */
public class ActivityDeatil extends Activity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d);
		
		GroupVo groupVo = (GroupVo) getIntent().getSerializableExtra("group");
		
		TextView payty_d_tv = (TextView)findViewById(R.id.payty_d_tv);
	
		String str = "Party Topic:" + groupVo.name ;
		 str = str + "\nParty Introduction:" + groupVo.introduct ;
		 str = str +"\nParty Place:" + groupVo.address ;
		 str = str + "\nParty Start Time:" + groupVo.start_time ;
		 str =  str + "\nParty End Time:" + groupVo.end_time ;
		payty_d_tv.setText(str);
	}
	
	

}
