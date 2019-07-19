package kr.yi.board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.MemberDao;
import kr.yi.board.jdbc.ConnectionProvider;
import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Member;
import kr.yi.board.model.User;

public class LoginHandler  implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/loginForm.jsp";
		}
		else if(req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("id");
			String pass=req.getParameter("password");
			


			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				MemberDao dao = MemberDao.getInstance();
				Member member = dao.selectById(conn, id);
				//id에 해당하는 회원이 있는가
				if(member==null) {	//회원없을떄
					req.setAttribute("notJoin", true);
					return "WEB-INF/view/loginForm.jsp";
				}
				//비밀번호가 일치하는가?
				if(member.getPassword().equals(pass)==false) { //비밀번호 불일치
					req.setAttribute("notMatchPassword", true);
					return "WEB-INF/view/loginForm.jsp";
				}
				
				//로그인처리
				HttpSession session = req.getSession();
				User user = new User(id,member.getName());
				session.setAttribute("Auth", user);
				return "index.jsp"; //홈화면
				
			}catch (Exception e) {
				
			}finally {
				JDBCUtil.close(conn);
			}
			
		}
		return null;
		
	}
	
}
