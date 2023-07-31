package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.Notice_DetailDAO;
import com.test.neulbom.client.repository.Notice_DetailDTO;

@WebServlet("/client/board/notice_detail.do")
public class Notice_Detail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//notice_detail.java
		String notice_seq = req.getParameter("notice_seq");
		
		Notice_DetailDAO dao = new Notice_DetailDAO();
		Notice_DetailDTO dto = dao.get(notice_seq);

		
		
		/*
		 * String keyword = req.getParameter("keyword");
		 * 
		 * List<Notice_DetailDTO> searchResult = dao.searchByTitle(keyword);
		 * 
		 * 
		 */


		    
		
		    
		    
		    
		// 조회수 증가 메서드 호출
		String noticeSeq = req.getParameter("notice_seq");
	    dao.increaseReadCount(noticeSeq);
		
		
		//개행문자
		String content = dto.getContent();
		
		//HTML 태그 이스케이프
		//얘를 먼저해야 내용상의 br태그를 어쩌구 먼저 처리?
		//이거 안해놓으면 태그를 읽음?
		content = content.replace("<", "&lt")
						.replace(">","&gt");
		
		
		//글 내용 개행문자 처리
		content = content.replace("/r/n", "<br>");
		
		
		/*
		 * //내용으로 검색 시 검색어 강조 if (search.equals("y") && column.equals("content")) {
		 * 
		 * content = content.replace(word,
		 * "<span style=\"background-color:gold;color:tomato;\">" + word + "</span>");
		 * 
		 * 
		 * }
		 */
		
		
		
		dto.setContent(content);
		
		
		/* req.setAttribute("searchResult", searchResult); */
		
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/notice_detail.jsp");
		dispatcher.forward(req, resp);
	}


}





