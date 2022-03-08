package com.hf.mail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hf.free.Moim;
import com.hf.hc.Account;
import com.hf.hc.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class MailDAO {

	public static void mailList(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from mail where m_receiveId=? order by m_num desc";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			ArrayList<Mail> mails = new ArrayList<Mail>();
			Mail m = null;
			
			while(rs.next()) {
				m = new Mail();
				m.setNum(rs.getInt("m_num"));
				m.setSendId(rs.getString("m_sendId"));
				m.setReceiveId(rs.getString("m_receiveId"));
				m.setTitle(rs.getString("m_title"));
				m.setContent(rs.getString("m_content"));
				mails.add(m);
			}
			
			request.setAttribute("mails", mails);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void sendMail(HttpServletRequest request) throws IOException {
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "insert into mail values(mail_seq.nextval, ?, ?, ?, ?)";
			
			String receiveId = request.getParameter("receiveId");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(sendId);
			System.out.println(receiveId);
			System.out.println(title);
			System.out.println(content);
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			
			if(pstmt.executeUpdate() == 1) {
				request.setAttribute("sendMail", "success");
			} else {
				request.setAttribute("sendMail", "fail");
			}
			
		} catch (Exception e) {
			request.setAttribute("sendMail", "fail");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	
	public static void mailDetail(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from mail where m_num=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			Mail m = null;
			
			while(rs.next()) {
				m = new Mail();
				m.setNum(rs.getInt("m_num"));
				m.setSendId(rs.getString("m_sendId"));
				m.setReceiveId(rs.getString("m_receiveId"));
				m.setTitle(rs.getString("m_title"));
				m.setContent(rs.getString("m_content"));
			}
			
			request.setAttribute("mailDetail", m);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public static void mailDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from mail where m_num=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("메일 삭제 완료");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	
	public static void sendApplication(HttpServletRequest request) throws IOException {
		String moimName = request.getParameter("name");
		String userId = "";
		System.out.println(moimName);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from user_moim where u_moimName=? and u_position='admin'";
			System.out.println(moimName);
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userId = rs.getString("u_userId");
				System.out.println(userId);
			}
			
		} catch (Exception e) {
			System.out.println("db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		String path = request.getSession().getServletContext().getRealPath("file");
		MultipartRequest mr = new MultipartRequest(request, path,
			10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		String receiveId = userId;
		String introduce = mr.getParameter("introduce");
		
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "insert into application values(application_seq.nextval, ?, ?, ?, ?)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, sendId);
			pstmt.setString(3, receiveId);
			pstmt.setString(4, introduce);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("신청성공");
				request.setAttribute("applyMoim", "success");
			} else {
				System.out.println("신청실패");
				request.setAttribute("applyMoim", "fail");
			}
			
		} catch (Exception e) {
			System.out.println("db문제 신청실패");
			request.setAttribute("sendMail", "fail");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void sendApplication2(HttpServletRequest request) {
		String moimName = request.getParameter("name");
		String userId = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from user_moim where u_moimName=? and u_position='admin'";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userId = rs.getString("u_userId");
			}
			
		} catch (Exception e) {
			System.out.println("db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		String receiveId = userId;
		String introduce = request.getParameter("introduce");
		
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "insert into application values(application_seq.nextval, ?, ?, ?, ?)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, sendId);
			pstmt.setString(3, receiveId);
			pstmt.setString(4, introduce);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("신청성공");
				request.setAttribute("applyMoim", "success");
			} else {
				System.out.println("신청실패");
				request.setAttribute("applyMoim", "fail");
			}
			
		} catch (Exception e) {
			System.out.println("db문제 신청실패");
			request.setAttribute("sendMail", "fail");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	public static void applicationList(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from application where a_receiveId=? order by a_num desc";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			ArrayList<Application> applications = new ArrayList<Application>();
			Application application = null;
			
			while(rs.next()) {
				application = new Application();
				application.setNum(rs.getInt("a_num"));
				application.setMoimName(rs.getString("a_moimName"));
				application.setSendId(rs.getString("a_sendId"));
				application.setReceiveId(rs.getString("a_receiveId"));
				application.setIntroduce(rs.getString("a_introduce"));
				applications.add(application);
			}
			
			request.setAttribute("applications", applications);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void applicationDetail(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "select * from application where a_num=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			Application application = null;
			
			if(rs.next()) {
				application = new Application();
				application.setNum(rs.getInt("a_num"));
				application.setMoimName(rs.getString("a_moimName"));
				application.setSendId(rs.getString("a_sendId"));
				application.setReceiveId(rs.getString("a_receiveId"));
				application.setIntroduce(rs.getString("a_introduce"));
			}
			String sendId = rs.getString("a_sendId");
			request.setAttribute("sendId", sendId);
			
			request.setAttribute("applications", application);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void applicationAccept(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		String id = request.getParameter("id");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "insert into user_moim values (?, ?, 'member')";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, id);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("가입성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void applicationDelete(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		String id = request.getParameter("id");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from application where a_moimname=? and a_sendid=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, id);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("지원서지우기성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void applicationResultMail(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		
		String moimName = request.getParameter("moimName");
		String receiveId = request.getParameter("id");
		
		String result = request.getParameter("result");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String sql = "";
			if(result.equals("accept")) {
				sql = "insert into mail values (mail_seq.nextval, ?, ?, '['||?||'] 모임 가입이 승인되었습니다', '['||?||'] 모임 가입이 승인되었습니다')";
			} else {
				sql = "insert into mail values (mail_seq.nextval, ?, ?, '['||?||'] 모임 가입이 거절되었습니다', '['||?||'] 모임 가입이 거절되었습니다')";
			} 
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, moimName);
			pstmt.setString(4, moimName);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("승인/거절메일 보내기 성공");
			} else {
				System.out.println("승인/거절메일 보내기 실패");
			}
			
		} catch (Exception e) {
			System.out.println("승인/거절메일 보내기 db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	

}
