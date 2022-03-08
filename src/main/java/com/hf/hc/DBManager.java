package com.hf.hc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DB관련 작업을 할때는 매번 연결코드를 쓴 이후 작업 한다
//닫는 작업도 마찬가지

//AOP
public class DBManager {
	
	//db연결
	public static Connection connect() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		return DriverManager.getConnection(url, "c##hs", "hs");
	}
	
	//닫기
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			
		}
		
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			
		}
		
		try {
			if(con != null) {
				con.close();
			}
		} catch (Exception e) {
			
		}
	}
	
}
