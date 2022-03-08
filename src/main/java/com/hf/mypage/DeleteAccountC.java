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
		
		// Ż���Ϸ��� ����ڰ� ������ �������� ������ �̸� ��������
		AccountDAO.getFreeMoim(request);
	    AccountDAO.getPaidMoim(request);
	    
	    // ���� ����
		AccountDAO.deleteAccount(request);
		
		// ���� ����
		PaidDAO.admindelete(request);			// Ż���Ϸ��� ����ڰ� ������ �����ڸ� ���� ����
		PaidDAO.deleteAccountUpdate(request);	// Ż���Ϸ��� ����ڰ� ������ ����� ȸ�� �� 1 ����
		
		// ���� ����
		FreeDAO.admindelete(request);			// Ż���Ϸ��� ����ڰ� ������ �����ڸ� ���� ����
		FreeDAO.deleteAccountUpdate(request);	// Ż���Ϸ��� ����ڰ� ������ ����� ȸ�� �� 1 ����
		
		AccountDAO.loginCheck(request);
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
