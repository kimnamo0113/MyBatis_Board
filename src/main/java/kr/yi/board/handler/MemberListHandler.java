package kr.yi.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.MemberDao;
import kr.yi.board.model.Member;
import kr.yi.board.util.MySqlSessionFactory;

public class MemberListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			SqlSession sqlSession = null;
			try {
				sqlSession=MySqlSessionFactory.openSession();
				MemberDao dao = sqlSession.getMapper(MemberDao.class);
				List<Member> list=dao.selectByAll();
				req.setAttribute("list", list);
				return "/WEB-INF/view/memberList.jsp";
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				sqlSession.close();
			}
			
		}
		else if(req.getMethod().equalsIgnoreCase("post")) {
			
		}
		return null;
	}

}
