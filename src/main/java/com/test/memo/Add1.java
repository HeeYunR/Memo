package com.test.memo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add1.do")
public class Add1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Add1.java

		//1. DB 작업 > select 카테고리
		
		//add.do
		
		MemoDAO1 dao = new MemoDAO1();
		
		java.util.List<CategoryDTO1> clist = dao.clist();
		
		req.setAttribute("clist", clist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/add1.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//하나의 survlet은 doget dopost 둘다 가질 수 있다. 
		//add.ok 역할
		//1. 데이터 가져오기
		//2. DB작업 > insert 
		//3. 피드백
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
		
		//1. 
		String memo = req.getParameter("memo");
		String category = req.getParameter("category");
		
		//2. insert를 하기 위해
		MemoDAO1 dao = new MemoDAO1();
		//넘길값이 두개니까?
		MemoDTO1 dto = new MemoDTO1();
		
		dto.setMemo(memo);
		dto.setCseq(category);
		
		int result = dao.add(dto);
		
		
		//jsp 말고 survlet
		
		if(result == 1) {
			//메모 추가하기 성공했을때
			resp.sendRedirect("/memo/list1.do");
			
		}else {
			
			//이거 survlet방식
			PrintWriter writer = resp.getWriter();
			writer.write("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}

}