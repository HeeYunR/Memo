package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class NoticeDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NoticeDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public List<NoticeDTO> list() {
		
		
		
		try {

			String sql = "SELECT * FROM tblnotice ORDER BY notice_date DESC";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<NoticeDTO> list = new ArrayList<NoticeDTO>();

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();
				
				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setRead(rs.getString("read"));
				
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Notice_DetailDTO> searchByTitle(String keyword) {
	    List<Notice_DetailDTO> result = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM tblnotice WHERE title LIKE ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "%" + keyword + "%");
	        rs = pstat.executeQuery();
	        
	        while (rs.next()) {
	            Notice_DetailDTO dto = new Notice_DetailDTO();
	            dto.setNotice_seq(rs.getString("notice_seq"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setNotice_date(rs.getString("notice_date"));
	            dto.setRead(rs.getString("read"));
	            
	            result.add(dto);
	        }
	        
	        rs.close();
	        pstat.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return result;
	}

	
}

