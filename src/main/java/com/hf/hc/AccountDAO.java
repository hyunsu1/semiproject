package com.hf.hc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountDAO {

	public static void loginCheck(HttpServletRequest request) {

		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");

		if (a == null) {
			// 로그인 실패
			request.setAttribute("loginPage", "login.jsp");
			request.setAttribute("loginCheck", "notLogin");
		} else {
			request.setAttribute("loginPage", "loginOK.jsp");
			request.setAttribute("loginCheck", "Login");
		}

	}

	public static void login(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		HttpSession hs = request.getSession();
		Account aa = (Account) hs.getAttribute("accountInfo");
		if(aa != null) {
			id = (String) request.getAttribute("idd");
			pw = (String) request.getAttribute("pww");
					
		}
		
		
		try {
			con = DBManager.connect();
			String sql = "select * from m_member where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String db_pw = rs.getString("m_pw");

				if (pw.equals(db_pw)) {
					Account a = new Account();
					a.setM_id(rs.getString("m_id"));
					a.setM_pw(rs.getString("m_pw"));
					a.setM_name(rs.getString("m_name"));
					a.setM_gender(rs.getString("m_gender"));
					a.setM_birth(rs.getString("m_birth"));
					a.setM_email(rs.getString("m_email"));
					a.setM_phone(rs.getString("m_phone"));
					a.setM_area(rs.getString("m_area"));
					a.setM_qa1(rs.getString("m_qa1"));
					a.setM_qa2(rs.getString("m_qa2"));

					
					hs.setAttribute("accountInfo", a);
					hs.setMaxInactiveInterval(300);

					request.setAttribute("r", "로그인 성공");
				} else {
					request.setAttribute("r", "비밀번호 오류");
				}
			} else {
				request.setAttribute("r", "존재하지 않는 회원");
			}

		} catch (Exception e) {
			request.setAttribute("r", "db 서버 문제..");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void regAccount(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "insert into m_member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String birth1 = request.getParameter("birth1");
			String birth2 = request.getParameter("birth2");
			String birth3 = request.getParameter("birth3");
			String birth = "";
			if (birth1 != null && birth2 != null && birth3 != null) {
				birth = birth1 + "/" + birth2 + "/" + birth3;
			}
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email = "";
			if (email1 != null && email2 != null) {
				email = email1 + "@" + email2;
			}
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String phone = "";
			if (phone1 != null && phone2 != null && phone3 != null) {
				phone = phone1 + "-" + phone2 + "-" + phone3;
			}
			String area1 = request.getParameter("sido");
			String area2 = request.getParameter("gugun");
			String area3 = request.getParameter("dong");
			String area = "";
			if (area1 != null && area2 != null && area3 != null) {
				area = area1 + "!" + area2 + "!" + area3;
			}
			String q1 = request.getParameter("q1");
			String q1_result = "";
			if(q1.equals("A")) {
				q1_result = "자신의 보물 제1호는?";
			}else if(q1.equals("B")) {
				q1_result = "가장 기억에 남는 선생님 성함은?";
			}else if(q1.equals("C")) {
				q1_result = "자신의 인생 좌우명은?";
			}
			String a1 = request.getParameter("a1");

			String q2 = request.getParameter("q2");
			String q2_result = "";
			if(q2.equals("A")) {
				q2_result = "인상깊게 읽은 책 이름은?";
			}else if(q2.equals("B")) {
				q2_result = "받았던 선물 중 기억에 남는 독특한 선물은?";
			}else if(q1.equals("C")) {
				q2_result = "다시 태어나면 되고 싶은 것은?";
			}
			String a2 = request.getParameter("a2");
			String qa1 = "";
			if(q1 != null && a1 != null) {
				qa1 = q1_result + "!" + a1;
			}
			String qa2 = "";
			if(q2 != null && a2 != null) {
				qa2 = q2_result + "!" + a2;
			}

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setString(5, birth);
			pstmt.setString(6, email);
			pstmt.setString(7, phone);
			pstmt.setString(8, area);
			pstmt.setString(9, qa1);
			pstmt.setString(10, qa2);
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			Account a = new Account(id, pw, name, gender, birth, email, phone, area, qa1, qa2);
			request.setAttribute("aa", a);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public static void updateAccount(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession hs = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "update m_member set m_pw=?, m_name=?, m_birth=?,  m_email=?, m_phone=?, m_area=?, m_qa1=?, m_qa2=? where m_id=?";
			pstmt = con.prepareStatement(sql);

			String pw = request.getParameter("pw");
			String pw2 = request.getParameter("pw3");
			String name = request.getParameter("name");
			String birth1 = request.getParameter("birth1");
			String birth2 = request.getParameter("birth2");
			String birth3 = request.getParameter("birth3");
			String birth = "";
			if (birth1 != null && birth2 != null && birth3 != null) {
				birth = birth1 + "/" + birth2 + "/" + birth3;
			}
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email = "";
			if (email1 != null && email2 != null) {
				email = email1 + "@" + email2;
			}
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String phone = "";
			if (phone1 != null && phone2 != null && phone3 != null) {
				phone = phone1 + "-" + phone2 + "-" + phone3;
			}
			String area1 = request.getParameter("sido");
			String area2 = request.getParameter("gugun");
			String area3 = request.getParameter("dong");
			String area = "";
			if (area1 != null && area2 != null && area3 != null) {
				area = area1 + "!" + area2 + "!" + area3;
			}

			String pw3 = "";
			if (pw2.length() == 0) {
				pw3 = pw;
			} else {
				pw3 = pw2;
			}
			String q1 = request.getParameter("q1");
			String q1_result = "";
			if(q1.equals("A")) {
				q1_result = "자신의 보물 제1호는?";
			}else if(q1.equals("B")) {
				q1_result = "가장 기억에 남는 선생님 성함은?";
			}else if(q1.equals("C")) {
				q1_result = "자신의 인생 좌우명은?";
			}
			String a1 = request.getParameter("a1");
			
			String q2 = request.getParameter("q2");
			String q2_result = "";
			if(q2.equals("A")) {
				q2_result = "인상깊게 읽은 책 이름은?";
			}else if(q2.equals("B")) {
				q2_result = "받았던 선물 중 기억에 남는 독특한 선물은?";
			}else if(q1.equals("C")) {
				q2_result = "다시 태어나면 되고 싶은 것은?";
			}
			String a2 = request.getParameter("a2");
			String qa1 = "";
			if(q1 != null && a1 != null) {
				qa1 = q1_result + "!" + a1;
			}
			String qa2 = "";
			if(q2 != null && a2 != null) {
				qa2 = q2_result + "!" + a2;
			}
			
			pstmt.setString(1, pw3);

			pstmt.setString(2, name);
			
			pstmt.setString(3, birth);

			pstmt.setString(4, email);

			pstmt.setString(5, phone);

			pstmt.setString(6, area);
			
			pstmt.setString(7, qa1);
			
			pstmt.setString(8, qa2);
			
			pstmt.setString(9, request.getParameter("id"));
			request.setAttribute("q1", q1);
			request.setAttribute("q2", q2);

			request.setAttribute("idd", request.getParameter("id"));
			request.setAttribute("pww", pw3);
//			login(request);
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "수정 성공!");
			} else {
				request.setAttribute("r", "수정 실패!");
			}
			
		pstmt.close();
		sql = "select * from m_member where m_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("id"));
		rs = pstmt.executeQuery();
		
		if(rs.next()){
		
			Account a = new Account();
			a.setM_id(rs.getString("m_id"));
			a.setM_pw(rs.getString("m_pw"));
			a.setM_name(rs.getString("m_name"));
			a.setM_gender(rs.getString("m_gender"));
			a.setM_birth(rs.getString("m_birth"));
			a.setM_email(rs.getString("m_email"));
			a.setM_phone(rs.getString("m_phone"));
			a.setM_area(rs.getString("m_area"));
			a.setM_qa1(rs.getString("m_qa1"));
			a.setM_qa2(rs.getString("m_qa2"));
			
			hs.setAttribute("accountInfo", a);
			hs.setMaxInactiveInterval(300);

		}
			
		} catch (Exception e) {
			request.setAttribute("r", "DB 서버 문제..");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static int makeBirth(HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("accountInfo");

		if (a != null) {
			String ar = a.getM_birth();
			String[] ar2 = ar.split("/");
			request.setAttribute("br", ar2);

			return 1;
		}
		return 0;
	}

	public static int makeEmail(HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("accountInfo");

		if (a != null) {
			String ar = a.getM_email();
			String[] ar2 = ar.split("@");
			request.setAttribute("em", ar2);

			return 1;
		}
		return 0;
	}
	
	public static int makePhoneNum(HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("accountInfo");
		
		if (a != null) {
			String ar = a.getM_phone();
			String[] ar2 = ar.split("-");
			request.setAttribute("ph", ar2);
			
			return 1;
		}
		return 0;
	}
	
	public static int makeArea(HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("accountInfo");
		
		if (a != null) {
			String ar = a.getM_area();
			String[] ar2 = ar.split("!");
			request.setAttribute("ar", ar2);
			
			return 1;
		}
		return 0;
	}
	
	public static void DetailArea(HttpServletRequest request) {
		Account a = (Account) request.getAttribute("mApp");
		if(a != null) {
			String dar = a.getM_area();
			String[] dar2 = dar.split("!");
			request.setAttribute("dar", dar2);
		}
	}
	
	public static void makeQnA(HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("accountInfo");
		
		if (a != null) {
			String q1 = a.getM_qa1();
			String[] q11 = q1.split("!");
			String q2 = a.getM_qa2();
			String[] q22 = q2.split("!");
			request.setAttribute("qa1", q11);
			request.setAttribute("qa2", q22);
			
		}
	}

	public static int getAccount(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = request.getParameter("id");
		try {
			con = DBManager.connect();
			String sql = "select * from m_member where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
					request.setAttribute("r", "이미 존재하는 회원 입니다");
					request.setAttribute("rr", 2);
					return 2;
			} else {
				request.setAttribute("r", "가입 가능 합니다!");
				return 1;
			}

		} catch (Exception e) {
			request.setAttribute("r", "db 서버 문제..");
			e.printStackTrace();
			return 0;
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void findId(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();
			
			String sql = "select * from m_member where m_phone = ? and m_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				request.setAttribute("m_id", rs.getString("m_id")); 
				request.setAttribute("ment", "님, 당신의 ID는"); 
				request.setAttribute("m_name", rs.getString("m_name")); 
			} else {
				request.setAttribute("r", "사용자 정보를 찾을 수 없습니다."); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void findPw(HttpServletRequest request) {

		String id = request.getParameter("id");
		String q1 = request.getParameter("q1");
		String a1 = request.getParameter("a1");
		String q2 = request.getParameter("q2");
		String a2 = request.getParameter("a2");

		String q1_result = "";
		if(q1.equals("A")) {
			q1_result = "자신의 보물 제1호는?";
		}else if(q1.equals("B")) {
			q1_result = "가장 기억에 남는 선생님 성함은?";
		}else if(q1.equals("C")) {
			q1_result = "자신의 인생 좌우명은?";
		}

		String q2_result = "";
		if(q2.equals("A")) {
			q2_result = "인상깊게 읽은 책 이름은?";
		}else if(q2.equals("B")) {
			q2_result = "받았던 선물 중 기억에 남는 독특한 선물은?";
		}else if(q1.equals("C")) {
			q2_result = "다시 태어나면 되고 싶은 것은?";
		}
		String qa1 = "";
		if(q1 != null && a1 != null) {
			qa1 = q1_result + "!" + a1;
		}
		String qa2 = "";
		if(q2 != null && a2 != null) {
			qa2 = q2_result + "!" + a2;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();
			
			String sql = "select * from m_member where m_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				if(rs.getString("m_qa1").equals(qa1) && rs.getString("m_qa2").equals(qa2)) {
					request.setAttribute("m_pw", rs.getString("m_pw")); 
					request.setAttribute("ment", "님, 당신의 PW는"); 
					request.setAttribute("m_name", rs.getString("m_name")); 	
					
					System.out.println(11);
				} else {
					request.setAttribute("r", "사용자 정보를 찾을 수 없습니다."); 
				}
			} else {
				request.setAttribute("r", "사용자 정보를 찾을 수 없습니다."); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void deleteAccount(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			String sql = "delete m_member where m_id = ?";
			pstmt = con.prepareStatement(sql);
			String id = request.getParameter("id");
			System.out.println(id);
			pstmt.setString(1, id);
			if(pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "삭제 성공");
				Account a = (Account) request.getSession().getAttribute("accountInfo");
				a = null;
				request.getSession().setAttribute("accountInfo", a);
			}else {
				request.setAttribute("r", "삭제 실패");
				
			}
			
		} catch (Exception e) {
			request.setAttribute("r", "db서버 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getPaidMoim(HttpServletRequest request) {

		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();
			// select * from freemoim, USER_MOIM where f_name = u_moimName and u_userId = 'syy';

			String sql = "select * from paidmoim, user_moim where p_name = u_moimName and u_userId = ? and u_position='admin'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			int temp = 0;
			
			
			ArrayList<PMoim> moims = new ArrayList<PMoim>();
			PMoim pm = new PMoim();
			
			while (rs.next()) {
				temp = 1;
					pm = new PMoim();
					
					pm.setP_name(rs.getString("p_name"));
					pm.setP_interest(rs.getString("p_interest"));
					pm.setP_region(rs.getString("p_region"));
					pm.setP_maxMembers(rs.getInt("p_maxMembers"));
					pm.setP_introduce(rs.getString("p_introduce"));
					pm.setP_image(rs.getString("p_image"));
					pm.setP_currentMembers(rs.getInt("p_currentMembers"));
					pm.setP_createDate(rs.getDate("p_createDate"));
					pm.setP_fee(rs.getString("p_fee"));
					
					moims.add(pm);
				}
				
			System.out.println(moims +"-----paid");
			request.setAttribute("moims_paid", moims);
			request.setAttribute("temp2", temp);
			
			rs.close();
			rs = pstmt.executeQuery();
			ArrayList<PMoim> pnames = new ArrayList<PMoim>();
			PMoim pn = null;
			while(rs.next()) {
				pn = new PMoim();
				pn.setP_name(rs.getString("p_name"));
				pnames.add(pn);
			}
			request.setAttribute("delpaid", pnames);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	public static void getFreeMoim(HttpServletRequest request) {
		
		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		String id = a.getM_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.connect();
			// select * from freemoim, USER_MOIM where f_name = u_moimName and u_userId = 'syy';
			
			String sql = "select * from freemoim, user_moim where f_name = u_moimName and u_userId = ? and u_position='admin'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			
			ArrayList<FMoim> moims = new ArrayList<FMoim>();
			FMoim fm = new FMoim();
			int temp = 0;
			
			while (rs.next()) {
				temp = 1;
					fm = new FMoim();
					fm.setF_name(rs.getString("f_name"));
					fm.setF_interest(rs.getString("f_interest"));
					fm.setF_region(rs.getString("f_region"));
					fm.setF_maxMembers(rs.getInt("f_maxMembers"));
					fm.setF_introduce(rs.getString("f_introduce"));
					fm.setF_image(rs.getString("f_image"));
					fm.setF_currentMembers(rs.getInt("f_currentMembers"));
					fm.setF_createDate(rs.getDate("f_createDate"));
					fm.setF_fee(rs.getString("f_fee"));
					
					moims.add(fm);
				
			}
			System.out.println(moims + "------free");
			request.setAttribute("moims_free", moims);
			request.setAttribute("temp1", temp);
			
			
			rs.close();
			rs = pstmt.executeQuery();
			ArrayList<FMoim> fnames = new ArrayList<FMoim>();
			FMoim fn = null;
			while(rs.next()) {
				fn = new FMoim();
				fn.setF_name(rs.getString("f_name"));
				fnames.add(fn);
			}
			request.setAttribute("delfree", fnames);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void member(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = (String)request.getAttribute("sendId");
		
		try {
			con = DBManager.connect();
			String sql = "select * from m_member where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			Account a = null;
			if (rs.next()) {
				a = new Account();
				a.setM_id(id);
				a.setM_name(rs.getString("m_name"));
				a.setM_birth(rs.getString("m_birth"));
				a.setM_gender(rs.getString("m_gender"));
				a.setM_area(rs.getString("m_area"));
			} 
			request.setAttribute("mApp", a);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

}