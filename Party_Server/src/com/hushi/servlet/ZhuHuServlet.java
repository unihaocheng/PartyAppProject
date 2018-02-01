package com.hushi.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hushi.dao.DAOFactory;
import com.hushi.dao.UserDAO;
import com.hushi.entity.UserVo;
import com.hushi.pager.PageBean;
import com.hushi.util.JsonUtils;
import com.hushi.util.ResponseUtil;

public class ZhuHuServlet extends HttpServlet {
	DAOFactory daoFactory;
	
	public void  doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 
		response.setCharacterEncoding("utf-8");
		
		daoFactory = new DAOFactory();
		String op = request.getParameter("op");
		System.out.println(op);
		if(op.equals("add")){
			addPatient(request, response);
		}else if(op.equals("update")){
			updatePatient(request, response);
		}else if(op.equals("del")){
			delPatient(request, response);
		}else if(op.equals("list")){
			listPatient(request, response);
		}else if(op.equals("combo")){
			comboListPatient(request, response);
		}else if(op.equals("combo2")){
			combo2ListPatient(request, response);
		}
		
	}
	private void listPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String page	= request.getParameter("page");
		String rows = request.getParameter("rows");
		
		String phone   = request.getParameter("s_phone");
		String type		= request.getParameter("s_type");
		
		
		if(phone==null)
			phone="";
		if(type==null)
			type="";
		
		UserDAO userDAO = daoFactory.getUserDAO();;
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		String jsonStr = JsonUtils.createJsonString(userDAO.patientList(pageBean, phone, type, -1));
		
		PrintWriter out=response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
		
		
		
	}
	private void delPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String delIds=request.getParameter("delIds");
		UserDAO userDAO = daoFactory.getUserDAO();
		
		String str[]=delIds.split(",");
		for(int i=0; i<str.length; i++){
			userDAO.del(str[i]);
		}
		
		try {
			JSONObject result=new JSONObject();
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updatePatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String nickName	 	= request.getParameter("nickName");
		String phone	= request.getParameter("phone");
		int type	 		= Integer.parseInt(request.getParameter("type"));
		int id	 		= Integer.parseInt(request.getParameter("id"));
		String pwd	= request.getParameter("pwd");
		String home_location	= request.getParameter("home_location");
		String cur_location	= request.getParameter("cur_location");
		UserVo p = new UserVo();
	
		p.id = id ;
		p.nickName = nickName ;
		p.pwd = pwd ;
		p.phone = phone ;
		p.type = type ;
		
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.edit(p);
	
		try {
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		//String zh_id		= request.getParameter("zh_id");
		String nickName	 	= request.getParameter("nickName");
		String phone	= request.getParameter("phone");
		int type	 		= Integer.parseInt(request.getParameter("type"));
		String pwd	= request.getParameter("pwd");
		String home_location	= request.getParameter("home_location");
		String cur_location	= request.getParameter("cur_location");
		UserVo p = new UserVo();
	
		p.nickName = nickName ;
		p.pwd = pwd ;
		p.phone = phone ;
		p.type = type ;
		
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.add(p);
		
		System.out.println(p.toString());
		try {
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void comboListPatient(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		
		
		UserDAO userDAO = daoFactory.getUserDAO();
	
		
		String jsonStr = JsonUtils.createJsonString(userDAO.listAll());
		PrintWriter out=response.getWriter();
		System.out.println(jsonStr);
		out.println(jsonStr);
		out.flush();
		out.close();
	}
	
	private void combo2ListPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
			
			
		   UserDAO userDAO = daoFactory.getUserDAO();
			
			List<UserVo> patientList = userDAO.listAllInhospital();
			
			String jsonStr = JsonUtils.createJsonString(patientList);
			PrintWriter out=response.getWriter();
			System.out.println(jsonStr);
			out.println(jsonStr);
			out.flush();
			out.close();
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
