package com.wy.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
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
 * Describe : Party page
 */
public class ActivityCL extends Activity implements OnClickListener {
	private ListView msgLv;
	private List<MsgVo> list = new ArrayList<MsgVo>();
	private CLAdapter bxAdapter;
	private TextView seeComTv,seeJoinTv,comTv,partyTv;
	private TextView insTv,nameTv;
	private GroupVo groupVo;
	private Button sendBt;
	private EditText msgEt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cl);
		
		groupVo = (GroupVo) getIntent().getSerializableExtra("group");
		
		insTv = (TextView)findViewById(R.id.js_ins_tv);
		nameTv = (TextView)findViewById(R.id.group_name_tv);
		insTv.setText(groupVo.introduct);
		nameTv.setText(groupVo.name);
		
		
		
		msgEt = (EditText)findViewById(R.id.add_msg_et);
		sendBt = (Button)findViewById(R.id.send_bt);
		
		seeComTv = (TextView)findViewById(R.id.see_com_tv);
		seeJoinTv = (TextView)findViewById(R.id.see_join_tv);
		comTv = (TextView)findViewById(R.id.com_tv);
		partyTv = (TextView)findViewById(R.id.see_party_tv);
		
		
		
		
		sendBt.setOnClickListener(this);
		
		
		seeComTv.setOnClickListener(this);
		seeJoinTv.setOnClickListener(this);
		comTv.setOnClickListener(this);
		partyTv.setOnClickListener(this);
		
		msgLv = (ListView)findViewById(R.id.msg_lv);
	    bxAdapter = new CLAdapter(this, list);
	    msgLv.setAdapter(bxAdapter);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 Intent intent ;
		 if(v == sendBt){//发送消息
		 String msg = msgEt.getText().toString();
		 if(StringUtils.isNotBlank(msg)){
			 new AddDataTask().execute(msg);
		 }
		}else if(v == seeComTv){
			intent = new Intent(ActivityCL.this,ActivityNF.class);
			intent.putExtra("group", groupVo);
			startActivity(intent);
			
		}else if(v == seeJoinTv){
			
			intent = new Intent(ActivityCL.this,ActivityNF.class);
			intent.putExtra("type", 1);
			intent.putExtra("group", groupVo);
			startActivity(intent);
			
		}else if(v == comTv){
			intent = new Intent(ActivityCL.this,ActivityJY.class);
			intent.putExtra("group", groupVo);
			startActivity(intent);
	    }else if(v == partyTv){
			intent = new Intent(ActivityCL.this,ActivityDeatil.class);
			intent.putExtra("group", groupVo);
			startActivity(intent);
	    }else if(v == partyTv){
		intent = new Intent(ActivityCL.this,ActivityJY.class);
		intent.putExtra("group", groupVo);
		startActivity(intent);
       	}
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new GetDataTask().execute();
	}
	private class GetDataTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.

			String result = query();
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null) {
				Type listType = new TypeToken<List<MsgVo>>() {}.getType();	
				list.clear();
				list = (List<MsgVo>) JsonUtils.StringFromJson(result,listType);	
				bxAdapter.setList(list);
				bxAdapter.notifyDataSetChanged();
			} 

		}
	}
	
	private String query() {
		String queryString =  "op=3" +"&groupid=" +groupVo.id;
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForPost(url);
	}

	private class AddDataTask extends AsyncTask<String , Void, String> {
		@Override
		protected String doInBackground(String... params) {
			// Simulates a background job.
			String msg = params[0];
			String result = add(msg);
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null && "1".equals(result)) {
				msgEt.setText("");
				new GetDataTask().execute();
			} 

		}
	}
	
//	private String add(String msg) {
//		String queryString =  "op=4" +"&groupid=" +groupVo.id +"&userid="+MyApplication.getInstance().getUser().id
//				+"&msg=" +msg ;
//		// url
//		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
//				+ queryString;
//		return HttpUtil.queryStringForPost(url);
//	}
//}
//    

	private String add(String msg) {
		String queryString = null;
		try {
			queryString = "op=4" +"&groupid=" +groupVo.id 
                    +"&userid="+MyApplication.getInstance().getUser().id
					+"&msg=" +URLEncoder.encode(msg,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"+ queryString;
		
		return HttpUtil.queryStringForPost(url);
		}
}

