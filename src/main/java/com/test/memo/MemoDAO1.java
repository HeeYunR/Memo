package com.test.memo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;


public class MemoDAO1 {

	   private Connection conn;   //DB와 연결
	   private Statement stat;      //쿼리 담는다
	   private PreparedStatement pstat;   //쿼리 조작
	   private ResultSet rs;   //select에서 반환받은 결과 가져온다
	
	public MemoDAO1() {
		this.conn = DBUtil.open();
	}

	public List<CategoryDTO1> clist() {

		try {
		
			String sql = "select seq, name from tblCategory order by name asc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<CategoryDTO1> clist = new ArrayList<CategoryDTO1>();
			
			while (rs.next()) {
				CategoryDTO1 dto = new CategoryDTO1();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				clist.add(dto);
			}
			
			return clist;
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return null;
	}

	public int add(MemoDTO1 dto) {

		try {
			
			String sql = "insert into tblMemo values(seqMemo.nextVal, ?, default, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMemo());
			pstat.setString(2, dto.getCseq());
			
			//여기를 while이나 if로 돌릴때가 있다. 그 차이 알아 둘것
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return 0;
	}

	public List<MemoDTO1> list() {

		try {
			
			String sql = "select * from tblMemo order by seq desc";
			
			stat  = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<MemoDTO1> list = new ArrayList<MemoDTO1>();
			
			//다시 반환받아야할때 while이나 if를 사용하는건가?
			while(rs.next()) {
				//다시 패킹
				MemoDTO1 dto = new MemoDTO1();
				
				dto.setSeq(rs.getString("seq"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCseq(rs.getString("cseq"));
				
				dto.setIcon(rs.getString("icon"));
				dto.setColor(rs.getString("color"));;
				
				//arraylist에 담아서
				list.add(dto);
			}
			//그걸 return
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public MemoDTO1 get(String seq) {

		try {
			String sql="select * from tblMemo where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sql);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				MemoDTO1 dto = new MemoDTO1();
				
				dto.setSeq(rs.getString("seq"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCseq(rs.getString("cseq"));
				
				return dto;
				
			}
		} catch (Exception e) {

		}
		
		
		return null;
	}

}
