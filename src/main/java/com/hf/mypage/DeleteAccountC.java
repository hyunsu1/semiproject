package com.hf.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.free.FreeDAO;
import com.hf.hc.AccountDAO;
import com.hf.paid.PaidDAO;

@WebServlet("/DeleteAccountC")
public class DeleteAccountC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		// Moim select
		FreeDAO.selectJoinedMoim(request);
		
		// 탈퇴하려는 사용자가 모임의 개설자인 모임의 이름 가져오기
		AccountDAO.getFreeMoim(request);
	    AccountDAO.getPaidMoim(request);
	    
	    // 계정 삭제
		AccountDAO.deleteAccount(request);
		
		// 유료 모임
		PaidDAO.admindelete(request);			// 탈퇴하려는 사용자가 모임의 개설자면 모임 삭제
		PaidDAO.deleteAccountUpdate(request);	// 탈퇴하려는 사용자가 모임의 멤버면 회원 수 1 감소
		
		// 무료 모임
		FreeDAO.admindelete(request);			// 탈퇴하려는 사용자가 모임의 개설자면 모임 삭제
		FreeDAO.deleteAccountUpdate(request);	// 탈퇴하려는 사용자가 모임의 멤버면 회원 수 1 감소
		
		AccountDAO.loginCheck(request);
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
