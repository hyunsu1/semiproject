package com.hf.hc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberService {

	public Account memberLogin(String memberId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();
			String sql = "select * from m_member where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
			String email = rs.getString("m_email");
			Account m = new Account();
			m.setM_email(email);
			return m;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
