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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.wy.R;
import com.wy.adapter.GroupAdapter;
import com.wy.app.MyApplication;
import com.wy.model.GroupVo;
import com.wy.util.HttpUtil;
import com.wy.util.JsonUtils;

/**
 * Created by Cheng Hao on 2017-1-20 下午10:42:45
 * Describe :Homepage
 */
public class MainActivity extends Activity  {
      
	private LinearLayout mainClayot,editClayot;
	private ListView groupLv;
	private List<GroupVo> list = new ArrayList<GroupVo>();
	private GroupAdapter groupAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainClayot = (LinearLayout) findViewById(R.id.main_create_layout);
		editClayot = (LinearLayout) findViewById(R.id.main_edit_layout);
		groupLv = (ListView) findViewById(R.id.group_lv);
		groupAdapter = new GroupAdapter(MainActivity.this, list);
		groupLv.setAdapter(groupAdapter);
		
		
		editClayot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,ActivityEdit.class);
				startActivity(intent);
			}
		});
		
		mainClayot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,ActivityBX.class);
				startActivity(intent);
				
			}
		});
		
		groupLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,ActivityCL.class);
				intent.putExtra("group", list.get(position));
				startActivity(intent);
				
				
			}
		});
		
		
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
				Type listType = new TypeToken<List<GroupVo>>() {}.getType();	
				list.clear();
				list = (List<GroupVo>) JsonUtils.StringFromJson(result,listType);	
				groupAdapter.setList(list);
				groupAdapter.notifyDataSetChanged();
			} 

		}
	}
	
	private String query() {
		String queryString =  "op=" + 1 +"&id=" + MyApplication.getInstance().getUser().id;
		// url
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForPost(url);
	}
	
}
