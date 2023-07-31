package com.test.memo;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list1.do")
public class List1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//List1.java
		
		//1. DB 작업 > select
		//2. 결과 반환 > JSP 호출하기
		
		MemoDAO1 dao = new MemoDAO1();
		
		List<MemoDTO1> list = dao.list();
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list1.jsp");
		dispatcher.forward(req, resp);
	}

}