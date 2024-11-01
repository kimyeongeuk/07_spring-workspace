package com.br.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDao {

	private final SqlSessionTemplate sqlSession;

	public List<NoticeDto> selectNotice(PageInfoDto pi) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() ,pi.getBoardLimit());
		return sqlSession.selectList("noticeMapper.selectNotice",null,rowBounds);
	}

	public int insertNotice(NoticeDto n) {
		return sqlSession.insert("noticeMapper.insertNotice",n);
	}

	public int insertAttach(AttachDto at) {
		return sqlSession.insert("noticeMapper.insertAttach",at);
	}

	public NoticeDto detailNotice(int no) {
		return sqlSession.selectOne("noticeMapper.detailNotice",no);
	}

	public int noticeListCount() {
		return sqlSession.selectOne("noticeMapper.noticeListCount");
	}
	
	
}
