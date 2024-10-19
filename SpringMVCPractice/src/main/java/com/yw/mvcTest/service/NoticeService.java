package com.yw.mvcTest.service;

import java.util.List;

import com.yw.mvcTest.dto.Notice;

public interface NoticeService {

	// 목록조회
	List<Notice> selectNotice();

	// 게시글 상세조회
	Notice detailNotice(int no);

	// 게시글 수정
	Notice updateNotice(Notice n);

	// 게시글 삭제
	List<Notice> deleteNotice(int no); 
	
	
}
