package com.wy.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wy.R;
import com.wy.app.MyApplication;
import com.wy.util.HttpUtil;

/**
 * Created by Cheng Hao on 2017-1-20 下午11:08:48
 * Describe : party information UI
 */
public class ActivityBX extends Activity {
    
	private EditText titleEt,insEt,group_end_tv,group_start_tv,group_address_tv;
	private Button bxbt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bx);
		titleEt = (EditText)findViewById(R.id.group_title_tv);
		insEt = (EditText)findViewById(R.id.group_ins_tv);
		group_address_tv = (EditText)findViewById(R.id.group_address_tv);
		group_start_tv = (EditText)findViewById(R.id.group_start_tv);
		group_end_tv = (EditText)findViewById(R.id.group_end_tv);
		bxbt = (Button)findViewById(R.id.bx_bt);
	    bxbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AddDataTask().execute();
			}
		});
	}
	
	
	private class AddDataTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.

			String result = add();
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null && result.equals("1")) {
				showDialog("发起Party成功");
				
			}else {
				Toast.makeText(ActivityBX.this, "Launch Party，try it later", Toast.LENGTH_SHORT).show();
			}

		}
	}
	
	private String add() {
		String title = titleEt.getText().toString();
		String ins = insEt.getText().toString();
		String address = group_address_tv.getText().toString();
		String start = group_start_tv.getText().toString();
		String end = group_end_tv.getText().toString();
		
		String queryString = null;
		try {
			queryString = "op=2"+"&id=" + MyApplication.getInstance().getUser().id + "&title="+URLEncoder.encode(title,"UTF-8") +"&ins="+URLEncoder.encode(ins,"UTF-8")
					+"&address=" + URLEncoder.encode(address,"UTF-8") +"&start="+URLEncoder.encode(start,"UTF-8") + "&end="+URLEncoder.encode(end,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForGet(url);
	}
	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						ActivityBX.this.finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
