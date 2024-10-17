package com.br.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.mvc.dao.NoticeDao;
import com.br.mvc.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // Service 역할의 클래스에 부여하는 Component 어노테이션 (빈스캐닝에 의해서 자동으로 빈 등록됨)
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDao noticeDao;
	
	
	@Override
	public List<NoticeDto> selectNotice() {
		return noticeDao.selectNoticeList();
	}

	@Override
	public NoticeDto selectNoticeByNo(int noticeNo) {
		return noticeDao.selectNoticeByNo(noticeNo);
	}

	@Override
	public NoticeDto modifyForm(int no) {
		return noticeDao.modifyForm(no);
	}

	@Override
	public NoticeDto updateNotice(int no, String title, String content) {
		return noticeDao.updateNotice(no,title,content);
	}

}
