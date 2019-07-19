package kr.yi.board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;

public class ArticleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn=null;
		int no=Integer.parseInt(req.getParameter("no"));
		try {
			conn=ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			int result=dao.delete(conn,no);
			if(result>0) {
				System.out.println(req.getContextPath());
				res.sendRedirect(req.getContextPath()+"/article/list.do");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		
		
		
		return null;
	}

}
