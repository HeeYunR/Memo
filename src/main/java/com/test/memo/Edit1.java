package com.test.memo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit.do")
public class Edit1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Edit1.java
		
		//1. DB 작넙 > select
		//2. 결과 반환 > jsp
		
		String seq = req.getParameter("seq");
		
		MemoDAO1 dao = new MemoDAO1();
		
		MemoDTO1 dto = dao.get(seq);
		
		req.setAttribute("dto", dto);
		
		//카테고리 가져오기
		java.util.List<CategoryDTO1> clist = dao.clist();
		
		req.setAttribute("clist", clist);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	
	}
}