package kr.yi.board.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleContentDao2;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Article;
import kr.yi.board.model.ArticleContent;
import kr.yi.board.model.User;

public class ArticleWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/article/write.jsp";
		}
		else if(req.getMethod().equalsIgnoreCase("post")) {
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			User user = (User)req.getSession().getAttribute("Auth");
			
			Article article = new Article();
			article.setTitle(title);
			article.setWriter_id(user.getId());
			article.setWriter_name(user.getName());
			article.setRegdate(new Date());
			article.setModdate(new Date());
			
			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				ArticleDao articleDao = ArticleDao.getInstance();
				int article_no = articleDao.insert(conn, article);
				if(article_no<0) {
					throw new RuntimeException("fail to insert article table");
				}
				ArticleContentDao2 articleContentDao = ArticleContentDao2.getInstance();
				ArticleContent ac=new ArticleContent();
				ac.setArticle_no(article_no);
				ac.setContent(content);
				int result=articleContentDao.insert(conn, ac);
				
				if(result<0) {
					throw new RuntimeException("fail to insert articleContent table");
				}
				
				conn.commit();
				res.sendRedirect(req.getContextPath()+"/article/list.do");
				return null; //forward 막힘
				
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
