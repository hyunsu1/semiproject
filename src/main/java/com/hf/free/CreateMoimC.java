package com.hf.free;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;


@WebServlet("/CreateMoimC")
public class CreateMoimC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
	
		if(request.getAttribute("loginCheck").equals("notLogin")) {
			request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
		} else {
			FreeDAO.createMoimCount(request);
			
			if((int)request.getAttribute("count") >= 5) {
				request.getRequestDispatcher("jsp/check/createMoimLimited.jsp").forward(request, response);
			} else {
				request.setAttribute("contentPage", "freemoim/createMoim.jsp");
				request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		FreeDAO.createMoim(request);
		FreeDAO.paging(request);
		FreeDAO.moimList(request);
		request.setAttribute("moimListPage", "allMoim.jsp");
		request.setAttribute("contentPage", "freemoim/free.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
}
