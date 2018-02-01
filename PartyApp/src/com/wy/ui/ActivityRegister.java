package com.wy.ui;

//register the app
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wy.R;
import com.wy.util.HttpUtil;

public class ActivityRegister extends Activity implements OnClickListener{
	
	
	private EditText register_name;
	private EditText register_password;
	private EditText register_conf_pwd;
	private EditText register_phone;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		
		
		
		findViewById();
		
	}
	private void findViewById(){
	
		
		
		register_name = (EditText)findViewById(R.id.register_account);
		register_password = (EditText)findViewById(R.id.register_pwd);
		register_conf_pwd = (EditText)findViewById(R.id.register_config_pwd);
		register_phone = (EditText)findViewById(R.id.register_phone);
		
		
		
		
		
		((Button)findViewById(R.id.register_register)).setOnClickListener(this);
		((Button)findViewById(R.id.register_back)).setOnClickListener(this);
	}
	

	private class GetDataTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			
			String username = register_name.getText().toString();
			String pwd = register_password.getText().toString();
			String phone = register_phone.getText().toString();

			String result = query(username,pwd,phone,1);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null && result.equals("1")) {
				showDialog("Succeed to register");
				register_name.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ActivityRegister.this.finish();
					}
				}, 2000);
				
				
				
			} else {
				showDialog("Fail to register, try it later");
			}
			
			
			
			

		}
	}

	// 验证方法
	private boolean validate() {
		String username = register_name.getText().toString();
		if (username.equals("")) {

			showDialog("Username couldn't be empty");
			return false;
		}
		String pwd = register_password.getText().toString();
		if (pwd.equals("")) {
			showDialog("Password couldn't be empty");
			return false;
		}
		
		String conpwd = register_conf_pwd.getText().toString();
		if (conpwd.equals("")) {
			showDialog("Password Again couldn't be empty");
			return false;
		}
		if(!pwd.equals(conpwd)){
			showDialog("Two passwords are not same");
			return false;
		}
		
		
		
		return true;
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
	private String query(String username,String pwd,String phone,int sex) {
		String queryString = "username=" + username + "&pwd=" + pwd + "&phone=" + phone + "&sex=" + sex ;
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
			ActivityRegister.this.finish();
			break;
		
		case R.id.register_register:
			if(validate()){
				new GetDataTask().execute();
			}
			break;
		
		}
	}
}
