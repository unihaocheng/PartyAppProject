package com.hushi.dao;

import java.util.List;

import com.hushi.entity.GroupVo;
import com.hushi.pager.PageBean;



public interface GroupDAO {


	
	
	public boolean add(GroupVo patient);

	public boolean edit(GroupVo patient);

	public boolean del(String id);
	
	public GroupVo findById(String id);
	
	public List<GroupVo> listAll();
	
	public List<GroupVo> listAllInhospital(String create_id);
	
	public List<GroupVo> patientList(PageBean pageBean, String phone, String type, int status);
}
