package org.edupoll.app.repository;

import org.apache.ibatis.session.SqlSession;
import org.edupoll.app.model.Account;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountsRepository {
	
	private final SqlSession sqlSession;
	
	public int save(Account account) {
		return sqlSession.insert("accounts.save", account);
	}
	
	public int update(Account account) {
		return sqlSession.update("accounts.update", account);
	}
	
	public Account findById(String id) {
		return sqlSession.selectOne("accounts.findById", id);
	}
}
