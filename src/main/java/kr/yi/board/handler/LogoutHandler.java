package kr.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.yi.board.controller.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//false : 세션이 있다면 그 세션을 리턴하지만 세션이 존재하지 않는다면 null을 리턴한다.
		//true(default): 세션이 이미 있는지 확인을 하여, 이미 있다면 그 세션을 반환시키고, 없으면 새로운 세션을 생성한다.
		HttpSession session=req.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "index.jsp";
	}

}
