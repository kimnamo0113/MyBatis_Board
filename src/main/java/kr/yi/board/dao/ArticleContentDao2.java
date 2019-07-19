package kr.yi.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import kr.yi.board.jdbc.JDBCUtil;
import kr.yi.board.model.Article;
import kr.yi.board.model.ArticleContent;

public class ArticleContentDao2 {
	private static final ArticleContentDao2 dao=new ArticleContentDao2();
	
	public static ArticleContentDao2 getInstance() {
		return dao;
	}
	
	public int insert(Connection conn, ArticleContent ac) throws SQLException {
		PreparedStatement pstmt=null;
		
		try {
			String sql="insert into article_content values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ac.getArticle_no());
			pstmt.setString(2, ac.getContent());
			return pstmt.executeUpdate();
		}finally {
			JDBCUtil.close(pstmt);
		}
	}
	public ArticleContent selectByNo(Connection conn,int articleNo) throws SQLException {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql = "select * from article_content where article_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ArticleContent ac=new ArticleContent();
				ac.setArticle_no(rs.getInt(1));
				ac.setContent(rs.getString(2));
				return ac;
			}
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		
		return null;
	}

	public int modify(Connection conn, ArticleContent ac) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			String sql="UPDATE board.article_content\r\n" + 
					"SET content=? WHERE article_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ac.getContent());
			pstmt.setInt(2, ac.getArticle_no());
			int res=pstmt.executeUpdate();
			if(res>0) {
				return res;
			}
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
}
