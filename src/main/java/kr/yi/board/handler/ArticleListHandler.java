package kr.yi.board.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.yi.board.controller.CommandHandler;
import kr.yi.board.dao.ArticleDao;
import kr.yi.board.model.Article;
import kr.yi.board.model.ArticlePage;
import kr.yi.board.util.MySqlSessionFactory;

public class ArticleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			String sPage = req.getParameter("page"); // 현재 선택한 페이지 번호

			int page = 1;
			if (sPage != null) {
				page = Integer.parseInt(sPage);
			}
			SqlSession sqlSession = null;

			try {
				sqlSession = MySqlSessionFactory.openSession();
				ArticleDao dao = sqlSession.getMapper(ArticleDao.class);
				Map<String, Object> map = new HashMap<>();
				map.put("startRow", (page - 1) * 10);
				map.put("size", 10);
				List<Article> list = dao.selectListPage(map);
				System.out.println(list);
				int totalCount = dao.selectTotalCount();
				ArticlePage articlePage = new ArticlePage(totalCount, page, 10, list);

				// req.setAttribute("list", list);
				req.setAttribute("articlePage", articlePage);
				return "/WEB-INF/view/article/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				sqlSession.close();
			}
		}
		return null;
	}
}
