package com.hushi.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hushi.dao.UserDAO;
import com.hushi.dao.impl.UserDAOimpl;
import com.hushi.entity.UserVo;
import com.hushi.util.JsonUtils;


/**
 * 
 *
 * 响应 Android客户端发来的请求
 */
public class AppLoginServlet extends HttpServlet {// 用户登录类

	public void doGet(HttpServletRequest request, HttpServletResponse response)//类的一个函数，返回值为空，有两个变量：请求，响应
			throws ServletException, IOException {//throws 是什么语法？
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 获得客户端请求参数
		String phone  = request.getParameter("account");//定义了三个变量，电话，密码，注户到实例化
		String password = request.getParameter("password");
		UserDAO zhuhuDAO = new UserDAOimpl();
	    
		UserVo res = zhuhuDAO.login(phone, password);//login的登录是电话和密码
		JSONObject object = new JSONObject();
		if(null == res){
			object.accumulate("error", -1);
		}else {
			object.accumulate("error", 1);
			object.accumulate("user", JsonUtils.createJsonString(res));
		}
		out.print(object.toString());
		out.flush();
		out.close();
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)//类的另个一个函数，返回值为空，有两个变量：请求，响应
			throws ServletException, IOException {
		doGet(request,response);//调用第一个函数
	}
	public void init() throws ServletException {
	}
	
	public AppLoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
