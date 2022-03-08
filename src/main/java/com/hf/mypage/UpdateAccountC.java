package com.hf.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;

@WebServlet("/UpdateAccountC")
public class UpdateAccountC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDAO.loginCheck(request);
		
		if(request.getAttribute("loginCheck").equals("notLogin")) {
            request.getRequestDispatcher("jsp/check/notLogin.jsp").forward(request, response);
         } else {
        	 AccountDAO.makeBirth(request);
        	 AccountDAO.makeEmail(request);
        	 AccountDAO.makePhoneNum(request);
        	 AccountDAO.makeArea(request);
        	 AccountDAO.makeQnA(request);
        	 request.setAttribute("contentPage", "updateInfo.jsp");
        	 request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
         }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDAO.loginCheck(request);
		AccountDAO.updateAccount(request);
		AccountDAO.makeArea(request);
		AccountDAO.makeQnA(request);
		AccountDAO.getFreeMoim(request);
		AccountDAO.getPaidMoim(request);
		request.setAttribute("contentPage", "Mypage.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

	}

}
