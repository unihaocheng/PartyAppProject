package com.wy.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.wy.R;
import com.wy.adapter.JYAdapter;
import com.wy.adapter.UploadAdapter;
import com.wy.model.GroupVo;
import com.wy.model.SubjectVo;
import com.wy.model.UserVo;
import com.wy.util.HttpUtil;
import com.wy.util.JsonUtils;

/**
 * Created by Cheng Hao on 2017-1-20 下午11:08:48
 * Describe : check comments
 */
public class ActivityNF extends Activity {
	private ListView bxlv;
	private List<SubjectVo> list = new ArrayList<SubjectVo>();
	private JYAdapter bxAdapter;
	private TextView titleTv;
	int type = 0;
	GroupVo groupVo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nf);
		
		titleTv = (TextView)findViewById(R.id.title_tv);
		bxlv = (ListView)findViewById(R.id.bx_lv);
	    bxAdapter = new JYAdapter(this, list);
	    
	    groupVo = (GroupVo) getIntent().getSerializableExtra("group");
	    type = getIntent().getIntExtra("type",0);
	    if(type == 1){
	    	titleTv.setText("Party Joiner");
	    }
	    
	    new GetDataTask().execute();
	    
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	private class GetDataTask extends AsyncTask<Void, Void, String> {
		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.

			String result = query();
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result != null) {
				
				list.clear();
				System.out.println(result);
				if(type == 0){
					Type listType = new TypeToken<List<SubjectVo>>() {}.getType();	
					list = (List<SubjectVo>) JsonUtils.StringFromJson(result,listType);	
					
				bxAdapter.setList(list);
				bxlv.setAdapter(bxAdapter);
				bxAdapter.notifyDataSetChanged();
				
				}else if(type == 1){
					Type listType = new TypeToken<List<UserVo>>() {}.getType();	
					List<UserVo> userlist = (List<UserVo>) JsonUtils.StringFromJson(result,listType);	
					UploadAdapter adapter = new UploadAdapter(ActivityNF.this, userlist);
					bxlv.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
			} 

		}
	}
	private String query() {
		String queryString =  "op=6&groupid=" + groupVo.id+"&type="+type;
		String url = HttpUtil.BASE_URL + "servlet/AppRegisterServlet?"
				+ queryString;
		return HttpUtil.queryStringForPost(url);
	}
}
