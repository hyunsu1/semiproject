package com.hf.hc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DB���� �۾��� �Ҷ��� �Ź� �����ڵ带 �� ���� �۾� �Ѵ�
//�ݴ� �۾��� ��������

//AOP
public class DBManager {
	
	//db����
	public static Connection connect() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		return DriverManager.getConnection(url, "c##hs", "hs");
	}
	
	//�ݱ�
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
