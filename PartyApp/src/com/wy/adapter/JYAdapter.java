package com.wy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wy.R;
import com.wy.model.SubjectVo;
import com.wy.util.StringUtils;

public class JYAdapter extends BaseAdapter {
	private ViewHolder holder; // 视图容器
	private LayoutInflater layoutInflater;
	private Context context;
	private List<SubjectVo> list; // 博客列表
	
	

	
	public JYAdapter(Context context, List<SubjectVo> list) {

		this.context = context;
		this.list = list;
		this.layoutInflater = LayoutInflater.from(context);
		
	}

	public void setList(List<SubjectVo> list) {
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
			convertView = layoutInflater.inflate(R.layout.item_jf,null);
					
			holder = new ViewHolder();
			
			holder.jf_staff      = (TextView) convertView.findViewById(R.id.bx_date);
			
			// 使用tag存储数据
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SubjectVo item = list.get(position); // 获取当前数据
		if (item != null) {
			holder.jf_staff .setText((StringUtils.isBlank(item.com)?"":item.com));    ;
		}

		return convertView;
	}

	public static class ViewHolder {
		
		TextView jf_staff;
		
		
	}

}
