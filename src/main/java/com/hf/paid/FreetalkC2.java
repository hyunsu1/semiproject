package com.hf.paid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;


@WebServlet("/FreetalkC2")
public class FreetalkC2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		PaidDAO.paging(request);
		PaidDAO.moimList(request);
		request.setAttribute("contentPage", "paidmoim/paid.jsp");
		request.setAttribute("moimListPage", "Freetalk2.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
