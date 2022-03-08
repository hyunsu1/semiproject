package com.hf.free;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hf.hc.Account;
import com.hf.hc.DBManager;
import com.hf.hc.FMoim;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeDAO {

	public static void createMoim(HttpServletRequest request) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("file");
		MultipartRequest mr = new MultipartRequest(request, path,
			10*1024*1024, "utf-8", new DefaultFileRenamePolicy());

		String name = mr.getParameter("name");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		String interest = mr.getParameter("interest");
		String sido = mr.getParameter("sido");
		String gugun = mr.getParameter("gugun");
		String dong = mr.getParameter("dong");
		String region = sido +" " +gugun +" " +dong;
		int members = Integer.parseInt(mr.getParameter("members"));
		String introduce = mr.getParameter("introduce");
		String image = mr.getFilesystemName("image");
		
		try {
			String sql = "insert into freemoim(f_name, f_interest, f_region, f_maxMembers, f_introduce, f_image) "
						+"values(?,?,?,?,?,?)";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, interest);
			pstmt.setString(3, region);
			pstmt.setInt(4, members);
			pstmt.setString(5, introduce);
			pstmt.setString(6, image);
			
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
		
		if(page2 == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int begin = 10 * (page - 1) + 1;
		int end = 10 * page;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from ( "
						+"	select rownum as num, freemoim.* "
						+"	from (select * from freemoim where f_region like '%'||?||'%' and f_name like '%'||?||'%' and f_fee = 0 order by f_createDate desc) freemoim"
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
		String name = request.getParameter("name");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from freemoim where f_name=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			Moim m = null;
			
			if(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("f_name"));
				m.setInterest(rs.getString("f_interest"));
				m.setRegion(rs.getString("f_region"));
				m.setMaxMembers(rs.getInt("f_maxMembers"));
				m.setIntroduce(rs.getString("f_introduce"));
				m.setImage(rs.getString("f_image"));
				m.setCurrentMembers(rs.getInt("f_currentMembers"));
				
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
			String sql = "update freemoim set f_currentMembers = (select count(*) from user_moim where u_moimname = ?) where f_name = ?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			pstmt.setString(2, moimName);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("현재인원 변경 성공");
			} else {
				System.out.println("현재인원 변경 실패");
			}
			
		} catch (Exception e) {
			System.out.println("현재인원 변경 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	public static void selectJoinedMoim(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user_moim where u_userId=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			ArrayList<String> moims = new ArrayList<String>();
			
			while(rs.next()) {
				moims.add(rs.getString("u_moimName"));
			}
			
			//
			if(moims!=null) {
				System.out.println(moims.size());
				for(int i=0; i<moims.size(); i++) {
					System.out.println(moims.get(i));					
				}
				request.setAttribute("moims", moims);				
			}
			
				
		} catch (Exception e) {
			System.out.println("db 가입한모임선택실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public static void admindelete(HttpServletRequest request) {
		ArrayList<FMoim> fnames = new ArrayList<FMoim>();
		fnames = (ArrayList<FMoim>) request.getAttribute("delfree");
		
		for (FMoim fMoim : fnames) {
			System.out.println(fMoim + "????");
		}
		
		if(fnames!=null) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();
			for(int i=0; i<fnames.size(); i++) {
				String sql = "delete from freemoim where f_name = ?";			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, fnames.get(i).getF_name());
				if(pstmt.executeUpdate() == 1) {
					System.out.println("무료 관리자 테이블 삭제 성공");
				} else {
					System.out.println("무료 관리자 테이블 삭제 실패");
				}
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("무료 관리자 테이블 삭제 DB 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		}
		
	}
	
	public static void deleteAccountUpdate(HttpServletRequest request) {
		ArrayList<FMoim> moims = new ArrayList<FMoim>();
		moims = (ArrayList<FMoim>) request.getAttribute("moims");
		
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
				String sql2 = "update freemoim set f_currentMembers = (select count(*) from user_moim where u_moimname = ?) where f_name = ?";
			
				
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, moims.get(i).getF_name());
				pstmt.setString(2, moims.get(i).getF_name());
				
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


	public static void paging(HttpServletRequest request) {
		int currentBlock;
		String currentBlock2 = request.getParameter("currentBlock");
		
		if(currentBlock2 == null) {
			currentBlock = 0;
		} else {
			currentBlock = Integer.parseInt(request.getParameter("currentBlock"));
		}
		request.setAttribute("currentBlock", currentBlock);
		
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
				sql = "select count(*) from freemoim where f_interest like '%'||?||'%' and f_fee = 0";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, interest);
				rs = pstmt.executeQuery();
			} else {
				sql = "select count(*) from freemoim where f_region like '%'||?||'%' and f_name like '%'||?||'%' and f_interest like '%'||?||'%' and f_fee = 0";
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
			for (int i=1001; i<=1037; i++) {
				String name = "hs" +i;
				String sql = "insert into freemoim(f_name, f_interest, f_region, f_maxMembers, f_introduce, f_image, f_fee) "
							+"values (?, '여행', '서울 강남구 신사동', 10, 'hs', '다운로드.jpg', '30000')";
				
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
	
	public static void GroupList(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 String sql = "select * from user_moim where u_userId = ?";
				
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			ArrayList<Moim> moims = new ArrayList<Moim>();
			Moim m = null;
			
			while(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("u_moimName"));
				moims.add(m);
			}
			
			request.setAttribute("moims", moims);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void GroupInfo(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 String sql = "select * from freemoim where f_name=?";
				
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();

			Moim m = null;
			
			if(rs.next()) {
				m = new Moim();
				m.setName(rs.getString("f_name"));
				m.setInterest(rs.getString("f_interest"));
				m.setRegion(rs.getString("f_region"));
				m.setMaxMembers(rs.getInt("f_maxMembers"));
				m.setIntroduce(rs.getString("f_introduce"));
				m.setImage(rs.getString("f_image"));
				m.setCurrentMembers(rs.getInt("f_currentMembers"));
				m.setCreateDate(rs.getDate("f_createDate"));
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

	public static void memberList(HttpServletRequest request) {
		String moimName = request.getParameter("moimName");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user_moim where u_moimName=?";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moimName);
			rs = pstmt.executeQuery();

			ArrayList<Usermoim> members = new ArrayList<Usermoim>();
			Usermoim u = null;
			
			while(rs.next()) {
				u = new Usermoim();
				u.setMoimName(rs.getString("u_moimName"));
				u.setUserId(rs.getString("u_userId"));
				u.setPosition(rs.getString("u_position"));
				members.add(u);
			}
			
			request.setAttribute("members", members);
			
			Moim m = new Moim();
			m.setName(moimName);
			
			request.setAttribute("moims", m);
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

	public static void updateFmoim(HttpServletRequest request) {
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
			String sql = "update freemoim\r\n"
					+ "set f_interest=?, f_region=?, f_maxMembers=?, f_introduce=?, f_image=? \r\n"
					+ "where f_name = ? and f_name in (\r\n"
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
			System.out.println(image+"------------------------------");
			System.out.println(image2+"---------------------");
			
			pstmt.setString(1, interest);
			System.out.println(interest);
			pstmt.setString(2, region);
			System.out.println(region);
			pstmt.setInt(3, maxMembers);
			System.out.println(maxMembers);
			pstmt.setString(4, introduce);
			System.out.println(introduce);
			if(image2 != null) {
				pstmt.setString(5, image2);
			}else {
				pstmt.setString(5, image);
			}
			pstmt.setString(6, request.getParameter("name"));
			pstmt.setString(7, id);

//			login(request);
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "수정 성공!");
			} else {
				request.setAttribute("r", "수정 실패!");
			}
			
		} catch (Exception e) {
			request.setAttribute("r", "DB 서버 문제..");
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

	public static void deleteFmoim(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String fname = request.getParameter("fname");
		
		try {
			con = DBManager.connect();
			String sql = "delete freemoim where f_name = ?";
			String sql2 = "delete board where b_moim = ?";
			String sql3 = "delete photoboard where p_moim = ?";
			String sql4 = "delete user_moim where u_moimName = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, fname);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, fname);
			pstmt.executeUpdate();

			pstmt.close();
			pstmt = con.prepareStatement(sql4);
			pstmt.setString(1, fname);
			if(pstmt.executeUpdate()==1) {
				System.out.println("모임 삭제 성공");
			} else {
				System.out.println("모임 삭제 실패");
			}
			
		} catch (Exception e) {
			request.setAttribute("r", "db서버 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

}
