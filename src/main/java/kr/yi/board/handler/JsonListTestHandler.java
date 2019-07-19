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

public class JsonListTestHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("get")) {
			String sPage=req.getParameter("page");
			int page = 1;
			if(sPage!=null) {
				page=Integer.parseInt(sPage);
			}
			
			Connection conn = null;
			
			try {
				conn=ConnectionProvider.getConnection();
				ArticleDao dao = ArticleDao.getInstance();
				List<Article> list=dao.selectListPage(conn, (page-1)*10, 10);
				int totalCount=dao.selectTotalCount(conn);
				ArticlePage articlePage = new ArticlePage(totalCount, page, 10, list);
				
				ObjectMapper om = new ObjectMapper();
				String data = om.writeValueAsString(articlePage);
				
				res.setContentType("application/json;charset=utf-8");
				PrintWriter out = res.getWriter();
				out.print(data);
				out.flush();
				return null;
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
