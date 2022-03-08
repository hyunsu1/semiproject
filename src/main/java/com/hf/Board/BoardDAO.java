package com.hf.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hf.hc.DBManager;

public class BoardDAO {

	ArrayList<Board> boards;
	private static final BoardDAO BDAO = new BoardDAO();
	
	private BoardDAO() {
	}
	
	public static BoardDAO getBdao() {
		return BDAO;
	}

	//모든 게시판 글 가져오기
	public void getAllBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "select * from BOARD where b_moim=? order by b_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("moimName"));
			rs = pstmt.executeQuery();
			
			boards = new ArrayList<Board>();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				b.setB_no(rs.getInt("b_no"));
				b.setB_title(rs.getString("b_title"));
				b.setB_text(rs.getString("b_text"));
				b.setB_date(rs.getDate("b_date"));
				b.setB_cnt(rs.getInt("b_cnt"));
				boards.add(b);
			}
			
			request.setAttribute("boards", boards);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	// 특정 게시판 글 가져오기
	public void getBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "select * from Board where b_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			
			Board b = null;
			if(rs.next()) {
				b = new Board();
				b.setB_no(rs.getInt("b_no"));
				b.setB_title(rs.getString("b_title"));
				b.setB_text(rs.getString("b_text"));
				b.setB_date(rs.getDate("b_date"));
				b.setB_cnt(rs.getInt("b_cnt"));
			}
			request.setAttribute("board", b);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	// 게시글 지우기
	public void delBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			String sql="delete board where b_no=? and b_moim=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.setString(2, request.getParameter("moimName"));
			
			if(pstmt.executeUpdate()==1) {
				request.setAttribute("result", "삭제에 성공하였습니다.");
			} else {
				request.setAttribute("result", "삭제에 실패하였습니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "Error");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	// 게시글 수정
	public void updateBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "update board set b_title=?, b_text=?, b_date=sysdate where b_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("title"));
			pstmt.setString(2, request.getParameter("text"));
			pstmt.setString(3, request.getParameter("no"));
			
			if(pstmt.executeUpdate()==1) {
				request.setAttribute("result", "수정에 성공하였습니다.");
			} else {
				request.setAttribute("result", "수정에 실패하였습니다. 다시 시도해주세요.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "Error");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	// 게시글 등록
	public void insBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "insert into board values(board_seq.nextval,?,?,sysdate,0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("title"));
			pstmt.setString(2, request.getParameter("text"));
			pstmt.setString(3, request.getParameter("name"));
			
			
			if(pstmt.executeUpdate()==1) {
				request.setAttribute("result", "등록에 성공하였습니다.");
			} else {
				request.setAttribute("result", "등록에 실패하였습니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "Error");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
//	// 게시글 검색
	public void searchBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "select * from board where b_title like '%' || ? || '%' and b_moim=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("search"));
			pstmt.setString(2, request.getParameter("moimName"));
			rs = pstmt.executeQuery();
			
			boards = new ArrayList<Board>();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				b.setB_no(rs.getInt("b_no"));
				b.setB_title(rs.getString("b_title"));
				b.setB_text(rs.getString("b_text"));
				b.setB_date(rs.getDate("b_date"));
				boards.add(b);
			}
			request.setAttribute("boards", boards);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	// 조회수
	public void updateBoardCnt(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			String sql = "update board set b_cnt=b_cnt+1 where b_no=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, request.getParameter("no"));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	//페이징
	public void paging(int page, HttpServletRequest request) {
		request.setAttribute("curPageNo", page); // 현재 페이지넘버

		// 전체 페이지 수 계산
		int cnt = 10; // 한 페이지당 보여줄 개수
		int total = boards.size(); // 총 데이터 개수
		
		int pageCount = (int)Math.ceil(total/(double)cnt);
		request.setAttribute("pageCount", pageCount);
	
		int start = total - (cnt * (page-1));
		int end = (page == pageCount) ? -1 : start - (cnt+1);
		
		ArrayList<Board> items = new ArrayList<Board>();
		if(start>=1) {
	         for(int i=start-1; i>end; i--) {
	            items.add(boards.get(i));
	         }
	      }
		
//		ArrayList<Board> items = new ArrayList<Board>();
//		for(int i=start-1; i>end; i--) {
//			items.add(boards.get(i));
//		}
//		
		request.setAttribute("boards", items);
		
	}
	
	
	
}
