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

public class ArticleModifyHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			int no = Integer.parseInt(req.getParameter("no"));
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				ArticleDao arDao = ArticleDao.getInstance();
				ArticleContentDao2 acDao = ArticleContentDao2.getInstance();
				
				Article article=arDao.selectByNo(conn, no);
				ArticleContent ac=acDao.selectByNo(conn, no);
				req.setAttribute("article", article);
				req.setAttribute("ac", ac);
				return "/WEB-INF/view/article/modify.jsp";
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
			
			  
			
		} else if (req.getMethod().equalsIgnoreCase("post")) {

			Connection conn = null;
			int article_no=Integer.parseInt(req.getParameter("article_no"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				ArticleDao arDao = ArticleDao.getInstance();
				ArticleContentDao2 acDao=ArticleContentDao2.getInstance();
				
				Article article = new Article();
				ArticleContent ac = new ArticleContent();
				
				article.setArticle_no(article_no);
				article.setTitle(title);
				article.setModdate(new Date());
				ac.setArticle_no(article_no);
				ac.setContent(content);
				
				int result1=arDao.modify(conn, article);
				int result2=acDao.modify(conn, ac);
				
				if(result1<0 || result2<0) {
					throw new RuntimeException("fail to insert articleContent table");
				}
				
				article = arDao.selectByNo(conn, article_no);
				ac = acDao.selectByNo(conn, article_no);
				
				conn.commit();
				res.sendRedirect(req.getContextPath()+"/article/read.do?no="+article.getArticle_no()+"&update=yes");
				return null;
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn);
			}
			
		}
		return null;
	}

}
