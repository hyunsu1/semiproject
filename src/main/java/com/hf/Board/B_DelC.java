package com.hf.Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;

@WebServlet("/B_DelC")
public class B_DelC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
//		BoardDAO.delBoard(request);
//		BoardDAO.getAllBoard(request);
		BoardDAO.getBdao().delBoard(request);
		BoardDAO.getBdao().getAllBoard(request);
		BoardDAO.getBdao().paging(1, request);
		request.setAttribute("contentPage", "mg_board/Board.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
