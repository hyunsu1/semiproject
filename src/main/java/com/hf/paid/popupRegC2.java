package com.hf.paid;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hf.hc.Account;
import com.hf.hc.AccountDAO;
import com.hf.mail.MailDAO;


@WebServlet("/popupRegC2")
public class popupRegC2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		
		if(request.getAttribute("loginCheck").equals("notLogin")) {
			request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
		} else {
			PaidDAO.moimCheck(request);
			if(request.getAttribute("moimCheck").equals("notjoined")) {
				PaidDAO.selectMoim(request);
				request.getRequestDispatcher("jsp/paidmoim/popupReg2.jsp").forward(request, response);
			} else {
				PaidDAO.moimCheck(request);
				request.getRequestDispatcher("jsp/check/popupNotReg.jsp").forward(request, response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaidDAO.selectMoim(request);
		
		request.getRequestDispatcher("jsp/paidmoim/payment.jsp").forward(request, response);
	
	}
}
