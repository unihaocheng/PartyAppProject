package com.wy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wy.R;
import com.wy.app.MyApplication;
import com.wy.model.MsgVo;

public class CLAdapter extends BaseAdapter {
	private ViewHolder holder; // 视图容器
	private LayoutInflater layoutInflater;
	private Context context;
	private List<MsgVo> list; // 博客列表
	
	

	
	public CLAdapter(Context context, List<MsgVo> list) {

		this.context = context;
		this.list = list;
		this.layoutInflater = LayoutInflater.from(context);
		
	}

	public void setList(List<MsgVo> list) {
		this.list = list;
	}


	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 组装数据
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_cl,null);
					
			holder = new ViewHolder();
			
			holder.msg_right_tv      = (TextView) convertView.findViewById(R.id.msg_right_tv);
			holder.msg_left_tv      = (TextView) convertView.findViewById(R.id.msg_left_tv);
			
			// 使用tag存储数据
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MsgVo item = list.get(position); // 获取当前数据
		if (item != null) {
			if(item.userid == MyApplication.getInstance().getUser().id){
				holder.msg_right_tv.setVisibility(View.VISIBLE);
				holder.msg_left_tv.setVisibility(View.GONE);
			}else {
				holder.msg_right_tv.setVisibility(View.GONE);
				holder.msg_left_tv.setVisibility(View.VISIBLE);
			}
			
			holder.msg_right_tv .setText(item.message);    
			holder.msg_left_tv .setText(item.message);    
		}

		return convertView;
	}

	public static class ViewHolder {
		
		TextView msg_left_tv;
		TextView msg_right_tv;
		
		
	}

}
