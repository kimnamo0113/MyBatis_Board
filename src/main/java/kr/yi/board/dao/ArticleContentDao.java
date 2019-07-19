package kr.yi.board.dao;

import kr.yi.board.model.ArticleContent;

public interface ArticleContentDao {
	int insert(ArticleContent ac);
	ArticleContent selectByNo(int articleNo);
	int modify(ArticleContent ac);
}
