package com.hf.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;
import com.hf.paid.PaidDAO;

@WebServlet("/DeletePmoimC")
public class DeletePmoimC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		AccountDAO.makeQnA(request);
		AccountDAO.makeArea(request);
		PaidDAO.deletePmoim(request);
		AccountDAO.getFreeMoim(request);
		AccountDAO.getPaidMoim(request);
		request.setAttribute("contentPage", "Mypage.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
