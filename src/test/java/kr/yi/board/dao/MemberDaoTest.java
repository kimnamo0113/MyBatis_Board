package kr.yi.board.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.yi.board.model.Member;
import kr.yi.board.util.MySqlSessionFactory;

public class MemberDaoTest {
	
//	@Test
	public void testInsert() {
		SqlSession sqlSession = null;
		try {
			sqlSession=MySqlSessionFactory.openSession();
			MemberDao dao=sqlSession.getMapper(MemberDao.class);
			Member member=new Member();
			member.setMemberid("test10");
			member.setName("test10");
			member.setPassword("test10");
			member.setRegdate(new Date());
			dao.insert(member);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	@Test
	public void  testUpdate() {
		SqlSession sqlSession=null;
		try {
			sqlSession=MySqlSessionFactory.openSession();
			MemberDao dao = sqlSession.getMapper(MemberDao.class);
			Map<String,Object> map=new HashMap<>();
			map.put("password", "1234");
			map.put("memberid", "test10");
			dao.modify(map);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
