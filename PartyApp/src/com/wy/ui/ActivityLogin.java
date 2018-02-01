package com.wy.ui;

//Login the app
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wy.R;
import com.wy.app.MyApplication;
import com.wy.model.UserVo;
import com.wy.util.HttpUtil;
import com.wy.util.JsonUtils;

public class ActivityLogin extends Activity implements OnClickListener{
	
	private ImageView back, addImg;
	private TextView  headTitle;
	
	private EditText login_name;
	private EditText login_password;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		
		
		findViewById();
		
	}
	private void findViewById(){
	
		back = (ImageView)findViewById(R.id.back);
	
		back.setOnClickListener(this);
		
		
		login_name = (EditText)findViewById(R.id.login_account);
		login_password = (EditText)findViewById(R.id.login_password);
		
		
		((TextView)findViewById(R.id.login_btn)).setOnClickListener(this);
		((TextView)findViewById(R.id.registbtn)).setOnClickListener(this);
	}
	

	private class GetDataTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			String username = login_name.getText().toString();
			String pwd = login_password.getText().toString();

			String result = query(username, pwd);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			//showDialog(result);
			if (result != null ) {
				try {
					JSONObject object = new JSONObject(result);
					int error = object.getInt("error");
					if(-1 == error){
						new AlertDialog.Builder(ActivityLogin.this).setTitle("Error")
						.setMessage("Username or password is wrong, try again").create().show();
						
					}else if(1 == error) {
						String  userData = object.getString("user");
						UserVo userVo = (UserVo) JsonUtils.StringFromJson2(userData, UserVo.class);
						MyApplication.getInstance().setUser(userVo);
					Intent i = new Intent(ActivityLogin.this, MainActivity.class);
					startActivity(i);
					ActivityLogin.this.finish();
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				new AlertDialog.Builder(ActivityLogin.this).setTitle("Error")
						.setMessage("Username or password is wrong, try again").create().show();
			}

		}
	}

	// 验证方法
	private boolean validate() {
		String username = login_name.getText().toString();
		if (username.equals("")) {

			showDialog("Username couldn't be empty");
			return false;
		}
		String pwd = login_password.getText().toString();
		if (pwd.equals("")) {
			showDialog("Password couldn't be empty");
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
	private String query(String account, String password) {
		String queryString = "account=" + account + "&password=" + password;
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppLoginServlet?"
				+ queryString;
		return HttpUtil.queryStringForPost(url);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.back:
			ActivityLogin.this.finish();
			break;
		case R.id.registbtn:
			Intent  intent = new Intent(ActivityLogin.this,ActivityRegister.class);
			startActivity(intent);
			break;
		
		case R.id.login_btn:
			if(validate()){
				new GetDataTask().execute();
			}
			break;
		
		}
	}
}
