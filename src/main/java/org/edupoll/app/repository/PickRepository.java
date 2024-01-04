package org.edupoll.app.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.edupoll.app.model.Pick;
import org.edupoll.app.model.PicksAdkView;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PickRepository {
	private final SqlSession sqlSession;

	public int savePick(Pick one) {
		return sqlSession.insert("picks.savePick", one);
	}

	public int deleteById(int id) {
		return sqlSession.delete("picks.deleteById", id);
	}

	public int deleteByOwnerAndTarget(Pick pick) {
		return sqlSession.delete("picks.deleteByOwnerAndTarget", pick);
	}

	public int countByTarget(int targetProductId) {
		return sqlSession.selectOne("picks.countByTarget", targetProductId);
	}

	public int countByOwnerAndTarget(Pick pick) {
		return sqlSession.selectOne("picks.countByOwnerAndTarget", pick);
	}

	public List<PicksAdkView> findAllPickById(String id) {
		return sqlSession.selectList("picks.findAllPickById", id);

	}

}
