package com.hushi.dao;

import java.util.List;

import com.hushi.entity.UserVo;
import com.hushi.pager.PageBean;



public interface UserDAO {


	
    public UserVo login(String phone, String password);
	
	public boolean add(UserVo patient);

	public boolean edit(UserVo patient);

	public boolean del(String id);
	
	public UserVo findById(String id);
	
	public UserVo findByPhone(String Phone);
	
	public List<UserVo> listAll();
	
	public List<UserVo> listAllInhospital();
	
	public List<UserVo> patientList(PageBean pageBean, String phone, String type, int status);
}
