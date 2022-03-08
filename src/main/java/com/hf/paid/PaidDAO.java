package com.hf.paid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hf.free.Moim;
import com.hf.hc.Account;
import com.hf.mail.Application;
import com.hf.hc.DBManager;
import com.hf.hc.PMoim;
import com.hf.mail.Mail;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PaidDAO {

	public static void createMoim(HttpServletRequest request) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("file");
		System.out.println(path);
		MultipartRequest mr = new MultipartRequest(request, path,
			10*1024*1024, "utf-8", new DefaultFileRenamePolicy());

		String name = mr.getParameter("name");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		try {
			String sql = "insert into user_moim values (?, ?, 'admin')";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		String interest = mr.getParameter("interest");
		String sido = mr.getParameter("sido");
		String gugun = mr.getParameter("gugun");
		String dong = mr.getParameter("dong");
		String region = sido +" " +gugun +" " +dong;
		int members = Integer.parseInt(mr.getParameter("members"));
		String introduce = mr.getParameter("introduce");
		String image = mr.getFilesystemName("image");
		String fee = mr.getParameter("fee");
		
		try {
			String sql = "insert into paidmoim(p_name, p_interest, p_region, p_maxMembers, p_introduce, p_image, p_fee) "
						+"values(?,?,?,?,?,?,?)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, interest);
			pstmt.setString(3, region);
			pstmt.setInt(4, members);
			pstmt.setString(5, introduce);
			pstmt.setString(6, image);
			pstmt.setString(7, fee);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void moimList(HttpServletRequest request) {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		if(sido == null)
			sido = "";
		if(gugun == null)
			gugun = "";
		String region = sido +" " +gugun;
		String searchName = request.getParameter("searchName");
		
		int page;
		String page2 = request.getParameter("page");
		int currentBlock = (Integer)request.getAttribute("currentBlock");
		
		if(page2 == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int begin = (currentBlock * 10 * 10) + (10 * (page - 1)) + 1;
		int end = (currentBlock * 10 * 10) + (10 * page);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from ( "
						+"	select rownum as num, paidmoim.* "
						+"	from (select * from paidmoim where p_region like '%'||?||'%' and p_name like '%'||?||'%' and p_fee > 0 order by p_createDate desc) paidmoim"
						+") where num between ? and ?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, region);
			pstmt.setString(2, searchName);
			pstmt.setInt(3, begin);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();
			
			ArrayList<Moim> moims = new ArrayList<Moim>();
			Moim m = null;
			
			while(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("p_name"));
				m.setInterest(rs.getString("p_interest"));
				m.setRegion(rs.getString("p_region"));
				m.setMaxMembers(rs.getInt("p_maxMembers"));
				m.setIntroduce(rs.getString("p_introduce"));
				m.setImage(rs.getString("p_image"));
				m.setCurrentMembers(rs.getInt("p_currentMembers"));
				m.setCreateDate(rs.getDate("p_createDate"));
				m.setFee(rs.getString("p_fee"));
				moims.add(m);
			}
			
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			
			request.setAttribute("moims", moims);
			request.setAttribute("searchName", searchName);
			request.setAttribute("page", page);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	/*
	public static void selectRegion(HttpServletRequest request) {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String region = sido +" " +gugun;
		String name = request.getParameter("searchName");
				
		int page;
		String page2 = request.getParameter("page");
		
		if(page2 == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int begin = (10 * (page - 1)) + 1;
		int end =  (10 * page);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from ( "
						+"	select rownum as num, freemoim.* "
						+"	from (select * from freemoim where f_region like '%'||?||'%' and f_name like '%'||?||'%' order by f_createDate desc) freemoim"
						+") where num between ? and ?";
		
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, region);
			pstmt.setString(2, name);
			pstmt.setInt(3, begin);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();
			
			ArrayList<Moim> moims = new ArrayList<Moim>();
			Moim m = null;
			
			while(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("f_name"));
				m.setInterest(rs.getString("f_interest"));
				m.setRegion(rs.getString("f_region"));
				m.setMaxMembers(rs.getInt("f_maxMembers"));
				m.setIntroduce(rs.getString("f_introduce"));
				m.setImage(rs.getString("f_image"));
				m.setCurrentMembers(rs.getInt("f_currentMembers"));
				m.setCreateDate(rs.getDate("f_createDate"));
				
				moims.add(m);
			}
			
			request.setAttribute("moims", moims);
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	 */
	
	public static void selectMoim(HttpServletRequest request) {
		String name = "";
		if(request.getParameter("name") == null) {
			name = request.getParameter("moimName");
		} else {
			name = request.getParameter("name");
		}
		
		System.out.println(name);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from paidmoim where p_name=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			Moim m = null;
			
			if(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("p_name"));
				m.setInterest(rs.getString("p_interest"));
				m.setRegion(rs.getString("p_region"));
				m.setMaxMembers(rs.getInt("p_maxMembers"));
				m.setIntroduce(rs.getString("p_introduce"));
				m.setImage(rs.getString("p_image"));
				m.setCurrentMembers(rs.getInt("p_currentMembers"));
				m.setFee(rs.getString("p_fee"));
			}
		
			request.setAttribute("moims", m);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void moimCheck(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		String name = request.getParameter("name");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user_moim where u_moimName=? and u_userId=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				request.setAttribute("moimCheck", "joined");
			} else {
				request.setAttribute("moimCheck", "notjoined");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void currentMembers(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		System.out.println(moimName);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update paidmoim set p_currentMembers = (select count(*) from user_moim where u_moimname = ?) where p_name = ?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, moimName);
			
			if(pstmt.executeUpdate() >= 1) {
				System.out.println("현재인원 변경 성공");
			} else {
				System.out.println("현재인원 변경 실패");
			}
			
		} catch (Exception e) {
			System.out.println("현재인원 db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void paging(HttpServletRequest request) {
		int currentBlock;
		String currentBlock2 = request.getParameter("currentBlock");
		
		if(currentBlock2 == null) {
			currentBlock = 0;
		} else {
			currentBlock = Integer.parseInt(request.getParameter("currentBlock"));
		}
		
		int pageCount = 10;
		int blockStartNum = (pageCount * currentBlock) + 1;
		int blockLastNum = blockStartNum + (pageCount - 1);
		int maxPage = 0;
		int maxBlock = 0;
		
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String region = sido +" " +gugun;
		String searchName = request.getParameter("searchName");
		String interest = request.getParameter("interest");
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "";
			if((sido == null || sido.isEmpty()) && (gugun == null || gugun.isEmpty()) && (searchName == null || searchName.isEmpty())) {
				sql = "select count(*) from paidmoim where p_interest like '%'||?||'%' and p_fee > 0";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, interest);
				rs = pstmt.executeQuery();
			} else {
				sql = "select count(*) from paidmoim where p_region like '%'||?||'%' and p_name like '%'||?||'%' and p_interest like '%'||?||'%' and p_fee > 0";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, region);
				pstmt.setString(2, searchName);
				pstmt.setString(3, interest);
				rs = pstmt.executeQuery();
				
			}
			
			if(rs.next()) {
				maxPage = rs.getInt("count(*)") == 0 ? 1 : (int)Math.ceil(((double)rs.getInt("count(*)") / 10));
				maxBlock = (maxPage % 10) == 0 ? maxPage / 10 - 1 : maxPage / 10;
				
				if(maxPage<blockLastNum) {
					blockLastNum = maxPage;
				}
				
				request.setAttribute("currentBlock", currentBlock);
				request.setAttribute("blockStartNum", blockStartNum);
				request.setAttribute("blockLastNum", blockLastNum);
				request.setAttribute("maxBlock", maxBlock);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void testCreate(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			for (int i=1; i<=123; i++) {
				String name = "hs" +i;
				String sql = "insert into paid(p_name, p_interest, p_region, p_maxMembers, p_introduce, p_image, p_fee) "
							+"values (?, '여행', '서울 강남구 신사동', 10, 'hs', '다운로드.jpg', 10000)";
				
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
	
				if(pstmt.executeUpdate() >= 1) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void payment(HttpServletRequest request) {
		String moimName = request.getParameter("name");
		
		Moim m = (Moim) request.getAttribute("moims");
		String fee = m.getFee();
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		String receiveId = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user_moim where u_moimName=? and u_position='admin'";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				receiveId = rs.getString("u_userId");
			}
			
		} catch (Exception e) {
			System.out.println("db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		try {
			String sql = "insert into payment values (?,?,?,sysdate)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, fee);

			if(pstmt.executeUpdate() == 1) {
				System.out.println("payment insert 성공");
			} else {
				System.out.println("payment insert 실패");
			}
			
		} catch (Exception e) {
			System.out.println("payment insert db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void refund(HttpServletRequest request) {
		Moim m = (Moim) request.getAttribute("moims");
		String fee = m.getFee();
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String sendId = a.getM_id();
		String receiveId = request.getParameter("id");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "insert into payment values (?,?,?,sysdate)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, fee);

			if(pstmt.executeUpdate() == 1) {
				System.out.println("refund insert 성공");
			} else {
				System.out.println("refund insert 실패");
			}
			
		} catch (Exception e) {
			System.out.println("payment insert db문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void transfer(HttpServletRequest request) {
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from payment where p_receiveId=? order by p_paydate desc";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			ArrayList<Transfer> transfers = new ArrayList<Transfer>();
			Transfer t = null;
			
			if(rs.next()) {
				t = new Transfer();
				t.setSendId(rs.getString("p_sendId"));
				t.setReceiveId(rs.getString("p_receiveId"));
				t.setFee(rs.getString("p_fee"));
				t.setPayDate(rs.getDate("p_payDate"));
				transfers.add(t);
			}
			
			request.setAttribute("transfers", transfers);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void createMoimCount(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) from user_moim where u_userId=? and u_position='admin'";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			System.out.println(count);
			request.setAttribute("count", count);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void updatePmoim(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		try {
			request.setCharacterEncoding("utf-8");
			String path = request.getSession().getServletContext().getRealPath("file");
			MultipartRequest mr = new MultipartRequest(request, path,
				10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			con = DBManager.connect();
			String sql = "update paidmoim\r\n"
					+ "set p_interest=?, p_region=?, p_maxMembers=?, p_introduce=?, p_image=?, p_fee=? \r\n"
					+ "where p_name = ? and p_name in (\r\n"
					+ "	select u_moimName\r\n"
					+ "	from USER_MOIM	\r\n"
					+ "	where u_userId = ?)";
			pstmt = con.prepareStatement(sql);

			String interest = mr.getParameter("interest");
			String interest2 = "";
			if(interest.equals("A")) {
				interest2 = "여행";
			}else if(interest.equals("B")) {
				interest2 = "운동";
			}else if(interest.equals("C")) {
				interest2 = "게임";
			}else if(interest.equals("D")) {
				interest2 = "음악";
			}else if(interest.equals("E")) {
				interest2 = "스터디";
			}else if(interest.equals("F")) {
				interest2 = "사진";
			}else if(interest.equals("G")) {
				interest2 = "반려동물";
			}else if(interest.equals("H")) {
				interest2 = "자유주제";
			}
			if(interest != null && interest2 != null) {
				interest = interest2;
			}
			String region1 = mr.getParameter("sido");
			String region2 = mr.getParameter("gugun");
			String region3 = mr.getParameter("dong");
			String region = region1+" " + region2 +" "+ region3;
			int maxMembers = Integer.parseInt(mr.getParameter("maxMembers"));
			String introduce = mr.getParameter("introduce");
			String image = mr.getParameter("image");
			String image2 = mr.getFilesystemName("image2");
			String fee = mr.getParameter("fee");
			String fee2 = mr.getParameter("fee2");
			
			
			pstmt.setString(1, interest);
			pstmt.setString(2, region);
			pstmt.setInt(3, maxMembers);
			pstmt.setString(4, introduce);
			
			if(image2 != null) {
				pstmt.setString(5, image2);
			}else {
				pstmt.setString(5, image);
			}
			
			if(fee2 != null) {
				pstmt.setString(6, fee2);
			} else {
				pstmt.setString(6, fee);
			}
			
			pstmt.setString(7, request.getParameter("name"));
			pstmt.setString(8, id);

//			login(request);
			if (pstmt.executeUpdate() == 1) {
				System.out.println("유료모임 수정 성공");
			} else {
				System.out.println("유료모임 수정 실패");
			}
			
		} catch (Exception e) {
			System.out.println("유료모임 수정 DB 문제");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void makeRegion(HttpServletRequest request) {
		Moim m = (Moim) request.getAttribute("moims");
		
		if (m != null) {
			String ar = m.getRegion();
			String[] ar2 = ar.split(" ");
			
			for (String s : ar2) {
				System.out.println(s);
			}
			
			request.setAttribute("rg", ar2);
			
			}
	}

	public static void deletePmoim(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String pname = request.getParameter("pname");
		
		try {
			con = DBManager.connect();
			String sql1 = "delete paidmoim where p_name = ?";
			String sql2 = "delete board where b_moim = ?";
			String sql3 = "delete photoboard where p_moim = ?";
			String sql4 = "delete user_moim where u_moimName = ?";
			
			pstmt = con.prepareStatement(sql4);
			pstmt.setString(1, pname);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, pname);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, pname);
			pstmt.executeUpdate();

			pstmt.close();
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, pname);
			if(pstmt.executeUpdate()==1) {
				System.out.println("모임 삭제 성공");
			} else {
				System.out.println("모임 삭제 실패");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("모임 삭제 실패 DB 확인 요망");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	public static void admindelete(HttpServletRequest request) {
		ArrayList<PMoim> pnames = new ArrayList<PMoim>();
		pnames = (ArrayList<PMoim>) request.getAttribute("delpaid");
		
		if(pnames!=null) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();
			for(int i=0; i<pnames.size(); i++) {
				String sql = "delete from paidmoim where p_name = ?";			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pnames.get(i).getP_name());
				if(pstmt.executeUpdate() == 1) {
					System.out.println("유료 관리자 테이블 삭제 성공");
				} else {
					System.out.println("유료 관리자 테이블 삭제 실패");
				}
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유료 관리자 테이블 삭제 DB 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		}
		
	}

	public static void deleteAccountUpdate(HttpServletRequest request) {
		ArrayList<PMoim> moims = new ArrayList<PMoim>();
		moims = (ArrayList<PMoim>) request.getAttribute("moims");
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		
		if(moims!=null) {
			System.out.println(moims.size());
			System.out.println(moims.get(0));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			for(int i=0; i<moims.size(); i++) {
				String sql2 = "update paidmoim set p_currentMembers = (select count(*) from user_moim where u_moimname = ?) where p_name = ?";
			
				
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, moims.get(i).getP_name());
				pstmt.setString(2, moims.get(i).getP_name());
				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("현재인원 변경 성공");
				} else {
					System.out.println("현재인원 변경 실패");
				}
			}
				
		} catch (Exception e) {
			System.out.println("db 현재인원 변경 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		}
	}
	
	public static void GroupInfo(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 String sql = "select * from paidmoim where p_name=?";
				
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();

			Moim m = null;
			
			if(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("p_name"));
				m.setInterest(rs.getString("p_interest"));
				m.setRegion(rs.getString("p_region"));
				m.setMaxMembers(rs.getInt("p_maxMembers"));
				m.setIntroduce(rs.getString("p_introduce"));
				m.setImage(rs.getString("p_image"));
				m.setCurrentMembers(rs.getInt("p_currentMembers"));
				m.setCreateDate(rs.getDate("p_createDate"));
			}
			
			if(m!=null) {
				request.setAttribute("moim", m);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
}
