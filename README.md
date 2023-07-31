# Memo
Create Notepad

## list 목록
```
@WebServlet("/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//List.java
		
		//1. DB 작업 > select
		//2. 결과 반환 > jsp 호출하기
		
		MemoDAO dao = new MemoDAO();
		
		java.util.List<MemoDTO> list = dao.list();
		
		req.setAttribute("list", list);
  //반환값이 있는얘랑 없는얘랑 뭔가 패턴이 있다.
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
		dispatcher.forward(req, resp);
	}

}

```


<br>


## add 글쓰기
```
@WebServlet("/add.do")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Add.java		
		//1. DB작업 > select 카테고리 
		
		MemoDAO dao = new MemoDAO();
		
		java.util.List<CategoryDTO> clist = dao.clist();
		
		req.setAttribute("clist", clist);
		
		System.out.println(clist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/add.jsp");
		dispatcher.forward(req, resp);
	}

	//하나의 servlet은 두개의 dopost, doget을 가질 수 있따.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Addok.java 역할
		//1. 데이터 가져오기
		//2. DB작업 > insert
		//3. 피드백
		
		//1. 
		req.setCharacterEncoding("UTF-8");
		String memo = req.getParameter("memo");
		String category = req.getParameter("category");
		
		//2. 
		MemoDAO dao = new MemoDAO();
		
		MemoDTO dto = new MemoDTO();
		dto.setMemo(memo);
		dto.setCseq(category);
		
		int result = dao.add(dto);
		
		if(result == 1) {
			resp.sendRedirect("/memo/list.do");
		}else {
			PrintWriter writer = resp.getWriter();
			writer.write("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
//		super.doPost(req, resp);
	}
}
```

<br>


## edit 수정하기
```
@WebServlet("/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Edit.java

		//0. 번호가져오기
		//1. DB 작업 > select
		//2. 결과 반환 > jsp 호출하기
		
		String seq = req.getParameter("seq");	
		MemoDAO dao = new MemoDAO();	
		MemoDTO dto = dao.get(seq);
			
		java.util.List<CategoryDTO> clist = dao.clist();
		req.setAttribute("clist", clist);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
		dispatcher.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//editok.java
		//Addok.java 역할
				//1. 데이터 가져오기
				//2. DB작업 > update
				//3. 피드백
				
				//1. 
				req.setCharacterEncoding("UTF-8");
				String memo = req.getParameter("memo");
				String category = req.getParameter("category");
				String seq = req.getParameter("seq");
				
				//2. 
				MemoDAO dao = new MemoDAO();
				
				MemoDTO dto = new MemoDTO();
				dto.setMemo(memo);
				dto.setCseq(category);
				dto.setSeq(seq);//수정할 메모번호
				
				int result = dao.edit(dto);
				
				if(result == 1) {
					resp.sendRedirect("/memo/list.do");
				}else {
					PrintWriter writer = resp.getWriter();
					writer.write("<script>alert('failed');history.back();</script>");
					writer.close();
				}			
			}
	}
```

<br>


## del 삭제하기
```
@WebServlet("/del.do")
public class Del extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Del.java

		//1. 
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		
		//2. 
		MemoDAO dao = new MemoDAO();	
		int result = dao.del(seq);
		
		if(result == 1) {
			resp.sendRedirect("/memo/list.do");
		}else {
			PrintWriter writer = resp.getWriter();
			writer.write("<script>alert('failed');history.back();</script>");
			writer.close();
		}		
	}
}
```

<br>

## DTO
```
//어노테이션 
//코드 자동생성 + 코드 축소
//@Setter
//@Getter
//@ToString
@Data //@ToString + @Getter + @Setter + @equalsAndHashCode + @RequierdArgsConstructor
public class MemoDTO {
	
	private String seq;
	private String memo;
	private String regdate;
	private String cseq;

	private String icon;
	private String color;
	
}
```

<br>

## DAO
```
public class MemoDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemoDAO() {
		this.conn = DBUtil.open();
	}

	public List<CategoryDTO> clist() {
		try {
			
			String sql = "select seq, name from tblCategory order by name asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<CategoryDTO> clist = new ArrayList<CategoryDTO>();
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				clist.add(dto);
				
			}
			
			return clist;
			//return 잊지 말기
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int add(MemoDTO dto) {
		
		try {
			
			String sql = "insert into tblmemo values(seqMemo.nextval, ?, default , ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMemo());
			pstat.setString(2, dto.getCseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public List<MemoDTO> list() {
		try {
			
			String sql = "select \r\n"
					+ "    tblMemo.*, \r\n"
					+ "    (select icon from tblCategory where seq = tblMemo.cseq) as icon, \r\n"
					+ "    (select color from tblCategory where seq = tblMemo.cseq) as color \r\n"
					+ "from tblMemo order by seq desc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<MemoDTO> list = new ArrayList<MemoDTO>();
			
			while(rs.next()) {
				
				MemoDTO dto = new MemoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCseq(rs.getString("cseq"));
				
				dto.setIcon(rs.getString("icon"));
				dto.setColor(rs.getString("color"));
				
				list.add(dto);
								
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemoDTO get(String seq) {

			try {
				
				String sql = "select * from tblMemo where seq = ?";
	
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, seq);
					
					rs = pstat.executeQuery();
					
					if(rs.next()) {
						MemoDTO dto = new MemoDTO();
						
						dto.setSeq(rs.getString("seq"));
						dto.setMemo(rs.getString("memo"));
						dto.setRegdate(rs.getString("regdate"));
						dto.setCseq(rs.getString("cseq"));
						
						return dto;
						
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public int edit(MemoDTO dto) {
		
		try {
			
			String sql = "update into tblmemo set memo = ?, cseq= ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMemo());
			pstat.setString(2, dto.getCseq());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		return 0;
	}

	public int del(String seq) {
		try {
			
			String sql = "delete from tblMemo where seq = ?";
			
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
```
