package com.br.mvc.service;

import java.util.List;

import com.br.mvc.dto.NoticeDto;

public interface NoticeService {
	
	// 목록조회
	List<NoticeDto> selectNotice();
	// 상세조회
	NoticeDto selectNoticeByNo(int noticeNo);
	
	
	NoticeDto modifyForm(int no);
	
	
	NoticeDto updateNotice(int no, String title, String content);

}
