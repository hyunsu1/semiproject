package com.hf.hc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginC")
public class LoginC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.login(request);
		AccountDAO.loginCheck(request);
		
		request.setAttribute("contentPage", "home.jsp");	
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
