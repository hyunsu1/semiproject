package com.hf.Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;


@WebServlet("/B_UpdateC")
public class B_UpdateC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
//		BoardDAO.getBoard(request);
		BoardDAO.getBdao().getBoard(request);
		request.setAttribute("contentPage", "mg_board/BoardUpdate.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
//		BoardDAO.updateBoard(request);
//		BoardDAO.getBoard(request);
		BoardDAO.getBdao().updateBoard(request);
		BoardDAO.getBdao().getBoard(request);
		request.setAttribute("contentPage", "mg_board/BoardDetail.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
