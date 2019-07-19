package kr.yi.board.handler;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Article;

public class JsonTestHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/jsonTest.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String sNo = req.getParameter("no");
			int no = Integer.parseInt(sNo);
			//db에서 no에 해당하는 게시글을 꺼냄
			Connection conn=null;
			try {
				conn=ConnectionProvider.getConnection();
				ArticleDao dao = ArticleDao.getInstance();
				Article article = dao.selectByNo(conn, no);
				
				ObjectMapper om = new ObjectMapper();
				//{article_no:111, writer_id:user00}
				String data = om.writeValueAsString(article);
				res.setContentType("application/json;charset=utf-8");
				PrintWriter out = res.getWriter();
				out.print(data);
				out.flush();
			  
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
