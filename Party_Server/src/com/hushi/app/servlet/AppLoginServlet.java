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
 * ��Ӧ Android�ͻ��˷���������
 */
public class AppLoginServlet extends HttpServlet {// �û���¼��

	public void doGet(HttpServletRequest request, HttpServletResponse response)//���һ������������ֵΪ�գ�������������������Ӧ
			throws ServletException, IOException {//throws ��ʲô�﷨��
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// ��ÿͻ����������
		String phone  = request.getParameter("account");//�����������������绰�����룬ע����ʵ����
		String password = request.getParameter("password");
		UserDAO zhuhuDAO = new UserDAOimpl();
	    
		UserVo res = zhuhuDAO.login(phone, password);//login�ĵ�¼�ǵ绰������
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
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)//������һ������������ֵΪ�գ�������������������Ӧ
			throws ServletException, IOException {
		doGet(request,response);//���õ�һ������
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
