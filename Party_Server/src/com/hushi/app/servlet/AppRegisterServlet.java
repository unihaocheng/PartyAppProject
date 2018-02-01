package com.hushi.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hushi.dao.GroupDAO;
import com.hushi.dao.MsgDAO;
import com.hushi.dao.SubjectDAO;
import com.hushi.dao.UserDAO;
import com.hushi.dao.impl.GroupDAOimpl;
import com.hushi.dao.impl.MsgDAOimpl;
import com.hushi.dao.impl.SubjectDAOimpl;
import com.hushi.dao.impl.UserDAOimpl;
import com.hushi.entity.GroupVo;
import com.hushi.entity.MsgVo;
import com.hushi.entity.SubjectVo;
import com.hushi.entity.UserVo;
import com.hushi.util.JsonUtils;


/**
 * 
 *
 * 响应 Android客户端发来的请求
 */
public class AppRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String op  = request.getParameter("op");
		UserDAO userDAO = new UserDAOimpl();
		GroupDAO groupDAO = new GroupDAOimpl();
		MsgDAO msgDAO = new MsgDAOimpl();
		SubjectDAO subjectDAO = new SubjectDAOimpl();
		
		System.out.println(op);
		if("1".equals(op)){//群组
			String  id  = request.getParameter("id");
			UserVo userVo = userDAO.findById(id);
			
			if(userVo.type == 0){//查询所有
				out.print(JsonUtils.createJsonString(groupDAO.listAll()));
			}else {
				out.print(JsonUtils.createJsonString(groupDAO.listAllInhospital(id)));
			}
			
			out.flush();
			out.close();
			
		}else if("2".equals(op)){
			String id  = request.getParameter("id");
			String title  = request.getParameter("title");
			title = new String(title.getBytes("ISO8859-1"), "UTF-8");
			String ins  = request.getParameter("ins");
			ins = new String(ins.getBytes("ISO8859-1"), "UTF-8");
			String address  = request.getParameter("address");
			address = new String(address.getBytes("ISO8859-1"), "UTF-8");
			String start  = request.getParameter("start");
			start = new String(start.getBytes("ISO8859-1"), "UTF-8");
			String end  = request.getParameter("end");
			end = new String(end.getBytes("ISO8859-1"), "UTF-8");
			
			GroupVo groupVo = new GroupVo();
			groupVo.create_id = Integer.parseInt(id);
			groupVo.name = title;
			groupVo.introduct = ins;
			groupVo.address = address;
			groupVo.start_time = start;
			groupVo.end_time = end;
			
			boolean res = groupDAO.add(groupVo);
			if(res){
				 out.print(1);	
			 }else {
				 out.print(-1);	
			 }
			out.flush();
			out.close();	
			
		}else if("3".equals(op)){//查询信息
			String groupid  = request.getParameter("groupid");
			out.print(JsonUtils.createJsonString(msgDAO.listAllInhospital(groupid)));	
			out.flush();
			out.close();
			
		}else if("4".equals(op)){
			String groupid  = request.getParameter("groupid");
			String userid  = request.getParameter("userid");
			String msg  = request.getParameter("msg");
			msg = new String(msg.getBytes("ISO8859-1"), "UTF-8");
			MsgVo msgVo = new MsgVo();
			msgVo.groupid = Integer.parseInt(groupid);
			msgVo.userid = Integer.parseInt(userid);
			msgVo.message = msg;
			boolean res = msgDAO.add(msgVo);
			if(res){
				out.print(1);		
			}else {
				out.print(-1);	
			}
			out.flush();
			out.close();
			
		
			
		}else if("6".equals(op)){//查询信息
			String groupid  = request.getParameter("groupid");
			String type  = request.getParameter("type");
	        if(Integer.parseInt(type) == 0){
	        	out.print(JsonUtils.createJsonString(subjectDAO.listAllInhospital(groupid)));	
	        }else {//查询参与者用户信息
	        	
	        	List<MsgVo> list = msgDAO.listAllInhospital(groupid);
	        	List<UserVo> userVos = new ArrayList<UserVo>();
	        	for (int i = 0; i < list.size(); i++) {
	        		userVos.add(userDAO.findById("" + list.get(i).userid));
				}
	        	List<SubjectVo> subjectVos = subjectDAO.listAllInhospital(groupid);
	        	for (int i = 0; i < subjectVos.size(); i++) {
	        		userVos.add(userDAO.findById("" + subjectVos.get(i).user_id));
				}
	        	HashSet<UserVo> h = new HashSet(userVos);   
	        	userVos.clear();   
	        	userVos.addAll(h);   

	        	out.print(JsonUtils.createJsonString(userVos));	
	        }
			
			
			out.flush();
			out.close();
			
		}else if("7".equals(op)){//评论
			String groupid  = request.getParameter("groupid");
			String userid  = request.getParameter("userid");
			String com =  request.getParameter("com");
			
			com = new String(com.getBytes("ISO8859-1"), "UTF-8");
			SubjectVo  vo = new  SubjectVo();
			vo.com = com;
			vo.groupid = Integer.parseInt(groupid);
			vo.user_id = Integer.parseInt(userid);
			
			
			boolean res = subjectDAO.add(vo);
			if(res){
				out.print(1);		
			}else {
				out.print(-1);	
			}
			out.flush();
			out.close();
			
		}else if("8".equals(op)){
			// 获得客户端请求参数
			String username  = request.getParameter("username");
			String party  = request.getParameter("party");
			String location  = request.getParameter("location");
			String id  = request.getParameter("id");
			
			UserVo zhuhuVo = userDAO.findById(id);
			zhuhuVo.nickName =  new String(username.getBytes("ISO8859-1"), "UTF-8");;
			zhuhuVo.birtyday =new String(party.getBytes("ISO8859-1"), "UTF-8"); 
			zhuhuVo.address = new String(location.getBytes("ISO8859-1"), "UTF-8"); 
			
				boolean resu = userDAO.edit(zhuhuVo);
				if(resu){
					out.print(1);	
				}else {
					out.print(0);	
				}
				out.flush();
				out.close();
				
		}else {
			// 获得客户端请求参数
			String username  = request.getParameter("username");
			String pwd  = request.getParameter("pwd");
			String phone  = request.getParameter("phone");
			String sex  = request.getParameter("sex");
			
			UserVo zhuhuVo = new UserVo();
			zhuhuVo.nickName =  new String(username.getBytes("ISO8859-1"), "UTF-8");;
			zhuhuVo.pwd = pwd;
			zhuhuVo.phone = phone;
			zhuhuVo.type = Integer.parseInt(sex);
			
			UserVo vo = userDAO.findByPhone(phone);
			if(null == vo){
				boolean resu = userDAO.add(zhuhuVo);
				if(resu){
					out.print(1);	
				}else {
					out.print(0);	
				}
			}else {
				out.print(2);	
			}
			
				
				out.flush();
				out.close();
				
		}
		
		
		
		
			
			
		
		
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}
	public void init() throws ServletException {
	}
	
	public AppRegisterServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
