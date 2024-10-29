package com.br.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDao {
	
	private final SqlSessionTemplate sqlSession;

	public int selectBoardListCount() {
		return sqlSession.selectOne("boardMapper.selectBoardListCount");
	}

	public List<BoardDto> selectBoardList(PageInfoDto pi){
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() ,pi.getBoardLimit());
		return sqlSession.selectList("boardMapper.selectBoardList",null,rowBounds);
	}
	

}
