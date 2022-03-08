package com.hf.paid;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;
import com.hf.hc.AccountDAO;


@WebServlet("/CreateMoimC2")
public class CreateMoimC2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		
		if(request.getAttribute("loginCheck").equals("notLogin")) {
			request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
		} else {
			PaidDAO.createMoimCount(request);
			if((int)request.getAttribute("count") >= 5) {
				request.getRequestDispatcher("jsp/check/createMoimLimited.jsp").forward(request, response);
			} else {
				request.setAttribute("contentPage", "paidmoim/createMoim2.jsp");
				request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		PaidDAO.createMoim(request);
		PaidDAO.paging(request);
		PaidDAO.moimList(request);
		request.setAttribute("moimListPage", "allMoim2.jsp");
		request.setAttribute("contentPage", "paidmoim/paid.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
}
