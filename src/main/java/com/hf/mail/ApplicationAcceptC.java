package com.hf.mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;


@WebServlet("/ApplicationAcceptC")
public class ApplicationAcceptC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MailDAO.applicationAccept(request);
		FreeDAO.currentMembers(request);
		MailDAO.applicationDelete(request);
		MailDAO.applicationResultMail(request);
		request.getRequestDispatcher("jsp/application/applicationAccept.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
