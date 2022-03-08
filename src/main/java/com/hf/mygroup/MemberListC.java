package com.hf.mygroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;
import com.hf.hc.AccountDAO;


@WebServlet("/MemberListC")
public class MemberListC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		FreeDAO.memberList(request);
		
		request.setAttribute("contentPage", "mg_GroupInfo/MemberList.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
