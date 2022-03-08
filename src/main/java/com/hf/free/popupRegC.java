package com.hf.free;

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


@WebServlet("/popupRegC")
public class popupRegC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		
		if(request.getAttribute("loginCheck").equals("notLogin")) {
			request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
		} else {
			FreeDAO.moimCheck(request);
			if(request.getAttribute("moimCheck").equals("notjoined")) {
				FreeDAO.selectMoim(request);
				request.getRequestDispatcher("jsp/freemoim/popupReg.jsp").forward(request, response);
			} else {
				FreeDAO.moimCheck(request);
				request.getRequestDispatcher("jsp/check/popupNotReg.jsp").forward(request, response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MailDAO.sendApplication(request);
		
		if(request.getAttribute("applyMoim").equals("success")) {
			request.getRequestDispatcher("jsp/check/applySuccess.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/check/applyFail.jsp").forward(request, response);
		}
	
	}
}
