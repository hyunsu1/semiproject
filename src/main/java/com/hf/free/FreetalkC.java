package com.hf.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;


@WebServlet("/FreetalkC")
public class FreetalkC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		FreeDAO.paging(request);
		FreeDAO.moimList(request);
		request.setAttribute("contentPage", "freemoim/free.jsp");
		request.setAttribute("moimListPage", "Freetalk.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
