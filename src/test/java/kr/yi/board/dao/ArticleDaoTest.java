package kr.yi.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.yi.board.model.Article;
import kr.yi.board.util.MySqlSessionFactory;

public class ArticleDaoTest {
	
	@Test
	public void testListPage() {
		SqlSession sqlSession=null;
		try {
			sqlSession=MySqlSessionFactory.openSession();
			ArticleDao dao = sqlSession.getMapper(ArticleDao.class);
			Map<String,Object> map=new HashMap<>();
			map.put("startRow", 0);
			map.put("size", 10);
			List<Article> list=dao.selectListPage(map);
			
			for(Article article:list) {
				System.out.println(article);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
