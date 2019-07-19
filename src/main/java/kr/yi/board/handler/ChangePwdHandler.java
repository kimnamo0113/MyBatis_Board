package kr.yi.board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.MemberDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.model.Member;
import kr.yi.board.model.User;

public class ChangePwdHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "WEB-INF/view/changePwdForm.jsp";
		}
		else if(req.getMethod().equalsIgnoreCase("post")) {
			Connection conn=null;
			conn=ConnectionProvider.getConnection();
			HttpSession session=req.getSession();
			User user=(User) session.getAttribute("Auth");
			String pass1=req.getParameter("pass1");
			String pass2=req.getParameter("pass2");
			MemberDao dao=MemberDao.getInstance();
			Member member=dao.selectById(conn, user.getId());
			int result=-1;
			
			if(member.getPassword().equals(pass1)==false) {
				result=-2;
				System.out.println("111");
				req.setAttribute("res", result);
				return "WEB-INF/view/changePwdForm.jsp";
			};
			member.setPassword(pass2);
			result=dao.modify(conn, member);
			
			if(result>0)
				return "WEB-INF/view/changePwdSuccess.jsp";
			
			
		}
		return null;
	}

}
