package com.hf.Photo;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import com.hf.hc.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PhotoBoardDAO {

	ArrayList<PhotoBoardTO> photos;
	private static final PhotoBoardDAO PDAO = new PhotoBoardDAO();
	private PhotoBoardDAO() {
		
	}
	
	public static PhotoBoardDAO getPdao() {
		return PDAO;
	}
	
	// 모든 게시판 글 가져오기
	public void getallPhotoBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();
			String sql = "select * from photoboard where p_moim=? order by p_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("moimName"));
			rs = pstmt.executeQuery();

			photos = new ArrayList<PhotoBoardTO>();
			PhotoBoardTO pbt = null;
			while (rs.next()) {
				pbt = new PhotoBoardTO();
				pbt.setP_no(rs.getInt("p_no"));
				pbt.setP_title(rs.getString("p_title"));
				pbt.setP_text(rs.getString("p_text"));
				pbt.setP_thumb(rs.getString("p_thumb"));
				pbt.setP_file(rs.getString("p_file"));
				pbt.setP_date(rs.getDate("p_date"));
				photos.add(pbt);
			}

			request.setAttribute("photos", photos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	// 특정 게시판 글 가져오기
	public void getPhotoBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();
			String sql = "select * from photoboard where p_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();

			PhotoBoardTO pbt = null;
			if (rs.next()) {
				pbt = new PhotoBoardTO();
				pbt.setP_no(rs.getInt("p_no"));
				pbt.setP_title(rs.getString("p_title"));
				pbt.setP_text(rs.getString("p_text"));
				pbt.setP_thumb(rs.getString("p_thumb"));
				pbt.setP_file(rs.getString("p_file"));
				pbt.setP_date(rs.getDate("p_date"));
			}

			request.setAttribute("photo", pbt);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	// 게시판 글 작성하기
	public void insPhotoBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			request.setCharacterEncoding("utf-8");
			String path = request.getServletContext().getRealPath("file");
			MultipartRequest mr = new MultipartRequest(request, path, 31457280, "utf-8", new DefaultFileRenamePolicy());
	
			/*
			ArrayList<String> fNames = new ArrayList<String>();
			String p_file = "p_file";
			String inputName = "";
			for (int i = 0; i < 5; i++) {
				inputName = mr.getFilesystemName(p_file + (i+1));
				System.out.println(inputName);
				if(inputName == null) {
					continue;
				} else {
					fNames.add(inputName);
				}
			}
			*/

			ArrayList<String> fNames = new ArrayList<String>();
			String p_file = "p_file";
			String inputName = "";
			for (int i = 0; i < 4; i++) {
				inputName = mr.getFilesystemName(p_file + (i+1));
				if(inputName == null) {
					inputName = null;
					fNames.add(inputName);
				} else {
					fNames.add(inputName);
				}
			}
			
			String fileArray = "";
			for (String s : fNames) {
				System.out.println(s);
				fileArray += s + "!";
			}
			System.out.println(fileArray);
			
			con = DBManager.connect();
			String sql = "insert into photoboard values(photoboard_seq.nextval, ?, ?, ?, ?, sysdate, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mr.getParameter("p_title"));
			pstmt.setString(2, mr.getParameter("p_text"));
			pstmt.setString(3, mr.getFilesystemName("p_thumb"));
			pstmt.setString(4, fileArray);
			pstmt.setString(5, request.getParameter("moimName"));

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("mention", "작성 성공");
			} else {
				request.setAttribute("mention", "작성 실패. 다시 시도 요망");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mention", "DB Server Error");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	// 파일 이름 나눠주기
	public int getFiles(HttpServletRequest request) {
		PhotoBoardTO pbtt = (PhotoBoardTO) request.getAttribute("photo");
//		if(pbt != null) {
			String pbtFile = pbtt.getP_file();
			String[] pbtFiles = pbtFile.split("!");
			
			request.setAttribute("files", pbtFiles);
//		}
		return 1;
	}
	
	public void delPhotoBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			String sql = "delete from photoboard where p_no =? and p_moim=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.setString(2, request.getParameter("moimName"));
			
			if(pstmt.executeUpdate() == 1) {
				request.setAttribute("delmention", "삭제에 성공하였습니다.");
			} else {
				request.setAttribute("delmention", "삭제에 실패하였습니다. 다시 시도하여주세요.");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("delmention", "Error. DB Server Error");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void upPhotoBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String path = request.getServletContext().getRealPath("file");
			MultipartRequest mr = new MultipartRequest(request, path, 31457280, "utf-8", new DefaultFileRenamePolicy());
			
			String NewTitle = mr.getParameter("np_title");
			String Text = mr.getParameter("np_text");
			String NewThumb = mr.getFilesystemName("np_thumb");
			String BefThumb = mr.getParameter("hp_thumb");
			String HiddenPK = mr.getParameter("hiddenNo");
			
			String newfile = "newFile";
			String oldfile = "oldFile";
			ArrayList<String> FileNames = new ArrayList<String>();
			String iName = "";
			for (int i = 0; i < 4; i++) {
				if( mr.getFilesystemName(newfile+(i+2)) == null) {
					iName = mr.getParameter(oldfile+(i+2));
					FileNames.add(iName);
				} else {
					iName = mr.getFilesystemName(newfile+(i+2));
					FileNames.add(iName);
					/*
					String delFile = path + "/" + iName;
					File f = new File(delFile);
					f.delete();
					*/
				}
			}
			
			String NewArray = "";
			for (String s : FileNames) {
				NewArray += s + "!";
			}
			System.out.println(NewArray);
			
			
			con = DBManager.connect();
			String sql = "update photoboard set p_title=?, p_text=?, p_thumb=?, p_file=?, p_date=sysdate where p_no=?";
			pstmt = con.prepareStatement(sql);
			
			// 첫번째 ?에 title
			pstmt.setString(1, NewTitle);
				
			// 두번째 ?에 text
			pstmt.setString(2, Text);
			
			// 세번째 ?에 썸네일
			if(NewThumb == null) {
				pstmt.setString(3, BefThumb);				
			} else {
				pstmt.setString(3, NewThumb);				
				String delThumb = path + "/" + BefThumb;
				File t = new File(delThumb);
				t.delete();
			}
			
			// 네번째 ?에 사진들
			pstmt.setString(4, NewArray);
			
			pstmt.setString(5, HiddenPK);
			
			if(pstmt.executeUpdate()==1) {
				request.setAttribute("upmention", "수정되었습니다.");
			} else {
				request.setAttribute("upmention", "수정 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	// 페이징
	public void Ppaging(int page, HttpServletRequest request) {
		request.setAttribute("curPageNo", page);
		
		int cnt = 5;
		int total = photos.size();
		
		int pageCount = (int) Math.ceil(total/(double)cnt);
		request.setAttribute("pageCount", pageCount);
		
		int start = total - (cnt * (page-1));
		int end = (page == pageCount) ? -1 : start - (cnt + 1);
		
		ArrayList<PhotoBoardTO> items = new ArrayList<PhotoBoardTO>();
		if(start>=1) {
			for(int i=start-1; i>end; i--) {
				items.add(photos.get(i));
			}
		}
		
		request.setAttribute("photos", items);
	}
	
	
	
}




/*
String file1 = mr.getFilesystemName("p_file1");
String file2 = mr.getFilesystemName("p_file2");
String file3 = mr.getFilesystemName("p_file3");
String file4 = mr.getFilesystemName("p_file4");
String file5 = mr.getFilesystemName("p_file5");
if (file1 != null) {
	fNames.add(file1);
}
if (file2 != null) {
	fNames.add(file2);
}
if (file3 != null) {
	fNames.add(file3);
}
if (file4 != null) {
	fNames.add(file4);
}
if (file5 != null) {
	fNames.add(file5);
}
*/