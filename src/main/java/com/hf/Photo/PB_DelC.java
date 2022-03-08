package com.hf.Photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;

@WebServlet("/PB_DelC")
public class PB_DelC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PhotoBoardDAO.delPhotoBoard(request);
//		PhotoBoardDAO.getallPhotoBoard(request);
		AccountDAO.loginCheck(request);
		PhotoBoardDAO.getPdao().delPhotoBoard(request);
		PhotoBoardDAO.getPdao().getallPhotoBoard(request);
		PhotoBoardDAO.getPdao().Ppaging(1, request);
		request.setAttribute("contentPage", "mg_pboard/PhotoBoard.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
