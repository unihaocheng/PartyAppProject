package com.hushi.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hushi.dao.DAOFactory;
import com.hushi.dao.SubjectDAO;
import com.hushi.entity.SubjectVo;
import com.hushi.pager.PageBean;
import com.hushi.util.JsonUtils;
import com.hushi.util.ResponseUtil;

public class FangWuServlet extends HttpServlet {
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
		}
		
	}
	private void listPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String page	= request.getParameter("page");
		String rows = request.getParameter("rows");
		
		String groupid   = request.getParameter("s_groupid");
		
		
		if(groupid==null) 	groupid="";
		SubjectDAO dao = daoFactory.getSubjectDAO();
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		
		PrintWriter out=response.getWriter();
		out.flush();
		out.close();
		
		
		
	}
	private void delPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String delIds=request.getParameter("delIds");
		System.out.println(delIds+"::");
		SubjectDAO dao = daoFactory.getSubjectDAO();
		
		String str[]=delIds.split(",");
		for(int i=0; i<str.length; i++){
			dao.del(str[i]);
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
		
		int id	= Integer.parseInt(request.getParameter("id"));
		String  subject	= request.getParameter("subject");
		String  option_a	= request.getParameter("option_a");
		String  option_b	= request.getParameter("option_b");
		String  option_c	= request.getParameter("option_c");
		String  right	= request.getParameter("right");
		int groupid	= Integer.parseInt(request.getParameter("groupid"));
		
		SubjectVo p = new SubjectVo();
	
		p.id = id ;
		p.groupid = groupid ;
		SubjectDAO dao = daoFactory.getSubjectDAO();
	
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
		
		String  subject	= request.getParameter("subject");
		String  option_a	= request.getParameter("option_a");
		String  option_b	= request.getParameter("option_b");
		String  option_c	= request.getParameter("option_c");
		String  right	= request.getParameter("right");
		int groupid	= Integer.parseInt(request.getParameter("groupid"));
		
		SubjectVo p = new SubjectVo();
		p.groupid = groupid ;
		SubjectDAO dao = daoFactory.getSubjectDAO();
		dao.add(p);
		
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
		
		
		SubjectDAO dao = daoFactory.getSubjectDAO();
		String jsonStr = JsonUtils.createJsonString(dao.listAll());
		PrintWriter out=response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
