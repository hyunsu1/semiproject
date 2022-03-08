package com.hf.mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.paid.PaidDAO;


@WebServlet("/ApplicationRejectC")
public class ApplicationRejectC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MailDAO.applicationDelete(request);
		MailDAO.applicationResultMail(request);
		PaidDAO.selectMoim(request);
		PaidDAO.refund(request);
		request.getRequestDispatcher("jsp/application/applicationReject.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
