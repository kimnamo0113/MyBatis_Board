package kr.yi.board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleContentDao2;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Article;
import kr.yi.board.model.ArticleContent;

public class ArticleReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//db article
		//db article_content
		String sNo=req.getParameter("no");
		String page=req.getParameter("page");
		String update=req.getParameter("update");
		int no = Integer.parseInt(sNo);
		Connection conn=null;
		
		try {
			conn=ConnectionProvider.getConnection();
			ArticleDao articleDao=ArticleDao.getInstance();
			ArticleContentDao2 acDao=ArticleContentDao2.getInstance();
			
			//get db article
			Article article = articleDao.selectByNo(conn, no);
			if(update==null) {
				articleDao.readCntUp(conn,article);
			}  
			//get db article_content
			ArticleContent ac = acDao.selectByNo(conn, no);
			req.setAttribute("article", article);
			req.setAttribute("ac", ac);
			req.setAttribute("page", page);
			return "/WEB-INF/view/article/read.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		
		return null;
	}

}
