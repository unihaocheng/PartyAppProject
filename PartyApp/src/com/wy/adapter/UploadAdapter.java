package com.wy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wy.R;
import com.wy.model.UserVo;

public class UploadAdapter extends BaseAdapter {
	private ViewHolder holder; // 视图容器
	private LayoutInflater layoutInflater;
	private Context context;
	private List<UserVo> list; // 博客列表
	
	public UploadAdapter(Context context, List<UserVo> list) {

		this.context = context;
		this.list = list;
		this.layoutInflater = LayoutInflater.from(context);
		
	}

	public void setList(List<UserVo> list) {
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
			convertView = layoutInflater.inflate(R.layout.item_upload,null);
					
			holder = new ViewHolder();
			
			holder.filenameTv      = (TextView) convertView.findViewById(R.id.filename_tv);
			holder.filepathTv      = (TextView) convertView.findViewById(R.id.filepath_tv);
			
			// 使用tag存储数据
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UserVo item = list.get(position); // 获取当前数据
		if (item != null) {
			holder.filenameTv.setText(item.nickName);
			holder.filepathTv.setText(item.phone);
		}

		return convertView;
	}

	public static class ViewHolder {
		
		TextView filenameTv;
		TextView filepathTv;
		
		
	}

}
