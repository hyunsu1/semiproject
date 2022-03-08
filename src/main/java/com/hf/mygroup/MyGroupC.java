package com.hf.mygroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;
import com.hf.hc.AccountDAO;


@WebServlet("/MyGroupC")
public class MyGroupC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		
		if(request.getAttribute("loginCheck").equals("notLogin")) {
			request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
		} else {
			FreeDAO.GroupList(request);
			request.setAttribute("contentPage", "mg_GroupInfo/GroupList.jsp");
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
