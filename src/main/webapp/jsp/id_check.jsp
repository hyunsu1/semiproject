<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.hf.hc.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String id = request.getParameter("userid");
System.out.println(id);
String result = "";
try {
	con = DBManager.connect();
	String sql = "select * from m_member where m_id=?";
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, id);

	rs = pstmt.executeQuery();
	if (rs.next()) {
		result = "1";
	} else {
		result = "0";
	}
	
	response.setContentType("application/json");
	JSONObject jo = new JSONObject();
	jo.put("asd", result);
	out.print(jo.toJSONString());

	
	
} catch (Exception e) {
	request.setAttribute("r", "db 서버 문제..");
	e.printStackTrace();
} finally {
	DBManager.close(con, pstmt, rs);
}

%>
