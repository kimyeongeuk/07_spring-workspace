package com.br.sbatis.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.sbatis.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDao {
	
	private final SqlSessionTemplate sqlSession;

	public List<NoticeDto> selectNoticeList() {
		return sqlSession.selectList("noticeMapper.selectNoticeList");
	}
	
	public NoticeDto selectNoticeByNo(int no) {
		return sqlSession.selectOne("noticeMapper.selectNoticeByNo",no);
	}

	public int insertNotice(NoticeDto n) {
		return sqlSession.insert("noticeMapper.insertNotice",n);
	}
	
	
	public int updateNotice(NoticeDto n) {
		return sqlSession.update("noticeMapper.updateNotice",n);
	}

	public int deleteNotice(String[] deleteNo) {
		return sqlSession.delete("noticeMapper.deleteNotice",deleteNo);
	}
	
	
	

}
