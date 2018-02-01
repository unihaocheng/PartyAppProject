package com.hushi.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hushi.dao.MsgDAO;
import com.hushi.entity.MsgVo;
import com.hushi.pager.PageBean;
import com.hushi.util.JdbcTmpl;



public class MsgDAOimpl implements MsgDAO{
	
	JdbcTmpl jt = new JdbcTmpl();
	

	public boolean add(MsgVo p) {
		// TODO Auto-generated method stub
		String  sql =  "insert into db_message values(null,?,?,?)";
		Object[] o = new Object[]{p.userid,p.groupid,p.message};
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

	public boolean edit(MsgVo p) {
		// TODO Auto-generated method stub
		String sql = " update db_message set userid=?,groupid=?,message=? where id=?";
		Object[] o = new Object[]{p.userid,p.groupid,p.message,p.id};
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
		String sql="delete from db_message where id=?";
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
	
	public MsgVo findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from db_message where id = ?";
		Object[] params = new Object[]{id};
		
		ResultSet rs = jt.find(sql, params);
		MsgVo p = null;
		
		try {
			if(rs.next()){
				p = new MsgVo();
				p.id = (rs.getInt("id"));
				p.message = (rs.getString("message"));
				p.userid = (rs.getInt("userid"));
				p.groupid = (rs.getInt("groupid"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p;
	}

	
	
	public List<MsgVo> listAll() {
		// TODO Auto-generated method stub
		List<MsgVo> list = new ArrayList<MsgVo>();
		String sql = "select * from db_message";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				MsgVo p = new MsgVo();
				p.id = (rs.getInt("id"));
				p.message = (rs.getString("message"));
				p.userid = (rs.getInt("userid"));
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

	public List<MsgVo> patientList(PageBean pageBean, String userid,
			String groupid, int status) {
		// TODO Auto-generated method stub
		String sql;
		List<MsgVo> list = new ArrayList<MsgVo>();
		sql = "select * from db_message where userid like '%"+userid+"%' and groupid like '%"+groupid+"%'";
		
		sql = sql +"  limit  "+pageBean.getStart()+","+pageBean.getRows()+"";
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				MsgVo p = new MsgVo();
				p.id = (rs.getInt("id"));
				p.message = (rs.getString("message"));
				p.userid = (rs.getInt("userid"));
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

	public List<MsgVo> listAllInhospital(String groupid) {
		// TODO Auto-generated method stub
		List<MsgVo> list = new ArrayList<MsgVo>();
		String sql = "select * from db_message where groupid = "+groupid;
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				MsgVo p = new MsgVo();
				p.id = (rs.getInt("id"));
				p.message = (rs.getString("message"));
				p.userid = (rs.getInt("userid"));
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
