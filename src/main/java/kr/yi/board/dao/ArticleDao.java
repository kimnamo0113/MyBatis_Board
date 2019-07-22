package kr.yi.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.yi.board.model.Article;

public interface ArticleDao {
	public List<Article> selectListPage(Map<String, Object> map) throws SQLException;
	public List<Article> selectList() throws SQLException;
	public int selectTotalCount() throws SQLException;
}
