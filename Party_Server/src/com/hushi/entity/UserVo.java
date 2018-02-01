package com.hushi.entity;

import java.io.Serializable;

public class UserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int id;
	public String nickName;
	public String phone;
	public int type;
	public String birtyday;
	public String address;
	public String pwd;
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		 UserVo userVo = ((UserVo) obj);
		if(userVo.id == this.id) {
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		String str = id +"";
		return str.hashCode();
	}
	
}
