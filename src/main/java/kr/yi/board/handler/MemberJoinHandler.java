package kr.yi.board.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.MemberDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Member;

public class MemberJoinHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/joinForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String id =req.getParameter("id");
			String name=req.getParameter("name");
			String password=req.getParameter("password");
			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				MemberDao dao = MemberDao.getInstance();
				Member member = new Member();
				member.setMemberid(id);
				member.setName(name);
				member.setPassword(password);
				member.setRegdate(new Date());
				
				
				Member dbMember = dao.selectById(conn, id);
				if(dbMember!=null) {//id 사용 유저잇음
					req.setAttribute("duplicatedId", true);
					return "/WEB-INF/view/joinForm.jsp";
				}
				dao.insert(conn, member);
				
				return "/WEB-INF/view/joinSuccess.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
			
		}
		return null;
	}
	
}
