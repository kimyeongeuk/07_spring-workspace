package com.br.spring.service;

import java.util.List;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

public interface NoticeService {
	
	// 공지사항 목록 불러오기
	List<NoticeDto> selectNotice(PageInfoDto pi);
	// 공지사항 등록
	int insertNotice(NoticeDto n, List<AttachDto> at);
	// 공지사항 상세
	NoticeDto detailNotice(int no);
	// 공지사항 갯수 조회
	int noticeListCount();
	
	
	
}
