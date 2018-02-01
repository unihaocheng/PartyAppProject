package com.hushi.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hushi.dao.GroupDAO;
import com.hushi.entity.GroupVo;
import com.hushi.pager.PageBean;
import com.hushi.util.JdbcTmpl;



public class GroupDAOimpl implements GroupDAO{
	
	JdbcTmpl jt = new JdbcTmpl();
	

	public boolean add(GroupVo p) {
		// TODO Auto-generated method stub
		String  sql =  "insert into db_group values(null,?,?,?,?,?,?,?)";
		Object[] o = new Object[]{p.name,p.introduct,p.create_id,p.address,p.start_time,p.end_time,p.join_ids};
		try {
			
			return jt.insert(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		
		return false;
	}

	public boolean edit(GroupVo p) {
		// TODO Auto-generated method stub
		String sql = " update db_group set name=?,introduct=?,create_id=?,address=?,start_time=?,end_time=?,join_ids=? where id=?";
		Object[] o = new Object[]{p.name,p.introduct,p.create_id,p.address,p.start_time,p.end_time,p.join_ids,p.id};
		try {
			return jt.update(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return false;
	}

	public boolean del(String id) {
		// TODO Auto-generated method stub
		String sql="delete from db_group where id=?";
		Object[] o = new Object[]{id};
		try {
			return jt.delete(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return false;
	}
	
	public GroupVo findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from db_group where id = ?";
		Object[] params = new Object[]{id};
		
		ResultSet rs = jt.find(sql, params);
		GroupVo p = null;
		
		try {
			if(rs.next()){
				p = new GroupVo();
				p.id = (rs.getInt("id"));
				p.name = (rs.getString("name"));
				p.introduct = (rs.getString("introduct"));
				p.create_id = (rs.getInt("create_id"));
				p.address = (rs.getString("address"));
				p.start_time = (rs.getString("start_time"));
				p.end_time = (rs.getString("end_time"));
				p.join_ids = (rs.getString("join_ids"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p;
	}

	
	
	public List<GroupVo> listAll() {
		// TODO Auto-generated method stub
		List<GroupVo> list = new ArrayList<GroupVo>();
		String sql = "select * from db_group";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				GroupVo p = new GroupVo();
				p.id = (rs.getInt("id"));
				p.name = (rs.getString("name"));
				p.introduct = (rs.getString("introduct"));
				p.create_id = (rs.getInt("create_id"));
				p.address = (rs.getString("address"));
				p.start_time = (rs.getString("start_time"));
				p.end_time = (rs.getString("end_time"));
				p.join_ids = (rs.getString("join_ids"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
		
	}

	public List<GroupVo> patientList(PageBean pageBean, String name,
			String create_id, int status) {
		// TODO Auto-generated method stub
		String sql;
		List<GroupVo> list = new ArrayList<GroupVo>();
		sql = "select * from db_group where name like '%"+name+"%' and create_id like '%"+create_id+"%'";
		
		sql = sql +"  limit  "+pageBean.getStart()+","+pageBean.getRows()+"";
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				GroupVo p = new GroupVo();
				p.id = (rs.getInt("id"));
				p.name = (rs.getString("name"));
				p.introduct = (rs.getString("introduct"));
				p.create_id = (rs.getInt("create_id"));
				p.address = (rs.getString("address"));
				p.start_time = (rs.getString("start_time"));
				p.end_time = (rs.getString("end_time"));
				p.join_ids = (rs.getString("join_ids"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}

	public List<GroupVo> listAllInhospital(String create_id) {
		// TODO Auto-generated method stub
		List<GroupVo> list = new ArrayList<GroupVo>();
		String sql = "select * from db_group where create_id = " +create_id;
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				GroupVo p = new GroupVo();
				p.id = (rs.getInt("id"));
				p.name = (rs.getString("name"));
				p.introduct = (rs.getString("introduct"));
				p.create_id = (rs.getInt("create_id"));
				p.address = (rs.getString("address"));
				p.start_time = (rs.getString("start_time"));
				p.end_time = (rs.getString("end_time"));
				p.join_ids = (rs.getString("join_ids"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}

	
}
