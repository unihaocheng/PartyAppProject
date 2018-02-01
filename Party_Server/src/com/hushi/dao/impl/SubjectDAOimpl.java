package com.hushi.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hushi.dao.SubjectDAO;
import com.hushi.entity.SubjectVo;
import com.hushi.util.JdbcTmpl;



public class SubjectDAOimpl implements SubjectDAO{
	
	JdbcTmpl jt = new JdbcTmpl();
	

	public boolean add(SubjectVo p) {
		// TODO Auto-generated method stub
		String  sql =  "insert into db_com values(null,?,?,?)";
		Object[] o = new Object[]{p.com,p.user_id,p.groupid};
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

	

	public boolean del(String id) {
		// TODO Auto-generated method stub
		String sql="delete from db_com where id=?";
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
	
	
	
	
	public List<SubjectVo> listAll() {
		// TODO Auto-generated method stub
		List<SubjectVo> list = new ArrayList<SubjectVo>();
		String sql = "select * from db_com";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				SubjectVo p = new SubjectVo();
				p.id = (rs.getInt("id"));
				p.com = (rs.getString("com"));
				p.user_id = (rs.getInt("user_id"));
				p.groupid = (rs.getInt("groupid"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
		
	}

	
	public List<SubjectVo> listAllInhospital(String groupid) {
		// TODO Auto-generated method stub
		List<SubjectVo> list = new ArrayList<SubjectVo>();
		String sql = "select * from db_com where groupid =" +groupid ;
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				SubjectVo p = new SubjectVo();
				p.id = (rs.getInt("id"));
				p.com = (rs.getString("com"));
				p.user_id = (rs.getInt("user_id"));
				p.groupid = (rs.getInt("groupid"));
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
