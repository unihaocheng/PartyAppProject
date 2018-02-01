package com.wy.ui;

//edit personal information 

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wy.R;
import com.wy.app.MyApplication;
import com.wy.model.UserVo;
import com.wy.util.HttpUtil;

public class ActivityEdit extends Activity implements OnClickListener{
	
	
	private EditText register_name;
	private EditText register_location;
	private EditText register_party;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit);
		
		
		
		findViewById();
		
	}
	private void findViewById(){
	
		
		
		register_name = (EditText)findViewById(R.id.register_account);
		register_party = (EditText)findViewById(R.id.register_party);
		register_location = (EditText)findViewById(R.id.register_location);
		UserVo userVo = MyApplication.getInstance().getUser();
		
		register_name.setText(userVo.nickName);
		register_party.setText(userVo.birtyday);
		register_location.setText(userVo.address);
		
		
		
		((Button)findViewById(R.id.register_register)).setOnClickListener(this);
		((Button)findViewById(R.id.register_back)).setOnClickListener(this);
	}
	

	private class GetDataTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			
			String username = register_name.getText().toString();
			String party = register_party.getText().toString();
			String location = register_location.getText().toString();

			String result = query(username,party,location);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null && result.equals("1")) {
				showDialog("Success to edit");
				UserVo userVo = MyApplication.getInstance().getUser();
				userVo.address = register_location.getText().toString();
				userVo.birtyday = register_party.getText().toString();
				userVo.nickName = register_name.getText().toString();
				MyApplication.getInstance().setUser(userVo);
				register_name.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ActivityEdit.this.finish();
					}
				}, 2000);
				
				
				
			} else {
				showDialog("Fail to edit, try it again");
			}
			
			
			
			

		}
	}

	
	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	// 根据用户名称密码查询
	private String query(String username,String party,String location) {
		String queryString = "op=8&username=" + username + "&party=" + party + "&location=" + location + "&id=" +MyApplication.getInstance().getUser().id;
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForPost(url);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.register_back:
			ActivityEdit.this.finish();
			break;
		
		case R.id.register_register:
				new GetDataTask().execute();
			break;
		
		}
	}
}
