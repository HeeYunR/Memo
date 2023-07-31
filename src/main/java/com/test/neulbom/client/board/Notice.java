package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.NoticeDAO;
import com.test.neulbom.client.repository.NoticeDTO;

@WebServlet("/client/board/notice.do")
public class Notice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//notice.java

//		 HttpSession session = req.getSession();
//		 req.getSession().setAttribute("id", "resi001");
		 
		 NoticeDAO dao = new NoticeDAO();
		 
		 List<NoticeDTO> list = dao.list();
		 
		 

		 
		 req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/notice.jsp");
		dispatcher.forward(req, resp);
		
		
	}

}
