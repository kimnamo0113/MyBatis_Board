package kr.yi.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.yi.board.model.Member;

public interface MemberDao {
	
	public void insert(Member member) throws SQLException;
	public Member selectById(Member member) throws SQLException;
	public List<Member> selectByAll() throws SQLException;
	public void modify(Member member) throws SQLException;
	public void modify(Map<String,Object> map) throws SQLException;
	
}
