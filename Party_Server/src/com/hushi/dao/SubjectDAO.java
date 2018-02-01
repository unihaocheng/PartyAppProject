package com.hushi.dao;

import java.util.List;

import com.hushi.entity.SubjectVo;
import com.hushi.pager.PageBean;



public interface SubjectDAO {


	
	
	public boolean add(SubjectVo patient);


	public boolean del(String id);
	
	
	public List<SubjectVo> listAll();
	
	public List<SubjectVo> listAllInhospital(String groupid);
	
}
