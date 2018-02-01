package com.hushi.dao;

import java.util.List;

import com.hushi.entity.MsgVo;
import com.hushi.pager.PageBean;



public interface MsgDAO {


	
	
	public boolean add(MsgVo patient);

	public boolean edit(MsgVo patient);

	public boolean del(String id);
	
	public MsgVo findById(String id);
	
	public List<MsgVo> listAll();
	
	public List<MsgVo> listAllInhospital(String groupid);
	
	public List<MsgVo> patientList(PageBean pageBean, String phone, String type, int status);
}
