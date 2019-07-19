package kr.yi.board.model;

public class ArticleContent {
	int article_no;
	String content;
	
	public ArticleContent() {
		super();
	}
	public ArticleContent(int article_no, String text) {
		super();
		this.article_no = article_no;
		this.content = text;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String text) {
		this.content = text;
	}
	@Override
	public String toString() {
		return "ArticleContent [article_no=" + article_no + ", text=" + content + "]";
	}
	
	
}
