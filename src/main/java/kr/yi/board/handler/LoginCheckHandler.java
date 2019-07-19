package kr.yi.board.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Article;
import kr.yi.board.model.ArticlePage;

public class LoginCheckHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id=req.getParameter("id");
		
		Connection conn = null;
		
		try {
			conn=ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			
			Article article=dao.selectById(conn, id);
			boolean idcheck=true;
			if(article!=null) {
				idcheck=false;
			}
			
			res.setContentType("application/json;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.print(idcheck);
			out.flush();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}
	

}
