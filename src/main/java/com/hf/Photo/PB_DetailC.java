package com.hf.Photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.hc.AccountDAO;

@WebServlet("/PB_DetailC")
public class PB_DetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		
		//		PhotoBoardDAO.getPhotoBoard(request);
//		PhotoBoardDAO.getFiles(request);
		PhotoBoardDAO.getPdao().getPhotoBoard(request);
		PhotoBoardDAO.getPdao().getFiles(request);
		request.setAttribute("contentPage", "mg_pboard/PhotoBoardDetail.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
