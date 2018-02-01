package com.hushi.dao;

import com.hushi.dao.impl.SubjectDAOimpl;
import com.hushi.dao.impl.UserDAOimpl;

 

public class DAOFactory {

	public DAOFactory(){
		
	}
	
	
	
	public static UserDAO getUserDAO(){
		return new UserDAOimpl();
	}
	public static SubjectDAO getSubjectDAO(){
		return new SubjectDAOimpl();
	}
	
}
