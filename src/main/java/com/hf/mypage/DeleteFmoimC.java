package com.hf.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;
import com.hf.hc.AccountDAO;

@WebServlet("/DeleteFmoimC")
public class DeleteFmoimC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		AccountDAO.makeQnA(request);
		AccountDAO.makeArea(request);
		FreeDAO.deleteFmoim(request);
		AccountDAO.getFreeMoim(request);
		AccountDAO.getPaidMoim(request);
		request.setAttribute("contentPage", "Mypage.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
