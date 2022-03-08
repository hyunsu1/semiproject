package com.hf.paid;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.mail.MailDAO;


@WebServlet("/PaymentC")
public class PaymentC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaidDAO.selectMoim(request);
		PaidDAO.payment(request);
		MailDAO.sendApplication2(request);
		
		if(request.getAttribute("applyMoim").equals("success")) {
			request.getRequestDispatcher("jsp/check/applySuccess.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/check/applyFail.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
