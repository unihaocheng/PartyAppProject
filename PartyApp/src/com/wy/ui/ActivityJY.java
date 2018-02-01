package com.wy.ui;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wy.R;
import com.wy.app.MyApplication;
import com.wy.model.GroupVo;
import com.wy.util.HttpUtil;

/**
 * Created by Cheng Hao on 2017-1-20 下午11:08:48
 * Describe :comment party
 */
public class ActivityJY extends Activity {
	private EditText bxEt;
	private Button bxbt;
	GroupVo groupVo; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jy);
		bxEt = (EditText)findViewById(R.id.bx_et);
		bxbt = (Button)findViewById(R.id.bx_bt);
		groupVo = (GroupVo) getIntent().getSerializableExtra("group");
	    bxbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AddDataTask().execute();
			}
		});
	    
	    
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	private class AddDataTask extends AsyncTask<Void, Void, String> {
		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.

			String result = add();
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null && result.equals("1")) {
				bxEt.setText("");
				Toast.makeText(ActivityJY.this, "Succeed to submit", Toast.LENGTH_SHORT).show();
				
			}else {
				Toast.makeText(ActivityJY.this, "Fail to submit, try it later", Toast.LENGTH_SHORT).show();
			}

		}
	}
	
//	private String add() {
//		String bx = bxEt.getText().toString();
//		
//		String queryString =  "op=7&groupid=" +  groupVo.id + "&com="+bx +"&userid="+MyApplication.getInstance().getUser().id;
//		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
//				+ queryString;
//		return HttpUtil.queryStringForGet(url);
//	}
	private String add() {
		String bx = bxEt.getText().toString();
		
		String queryString = null;
		try {
			queryString = "op=7&groupid=" +  groupVo.id + "&com="+URLEncoder.encode(bx,"UTF-8") +"&userid="+MyApplication.getInstance().getUser().id;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForGet(url);
	}
	
	
}
