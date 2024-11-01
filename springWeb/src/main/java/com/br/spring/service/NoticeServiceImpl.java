package com.br.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.spring.dao.NoticeDao;
import com.br.spring.dto.AttachDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDao noticeDao;

	@Override
	public List<NoticeDto> selectNotice(PageInfoDto pi) {
		return noticeDao.selectNotice(pi);
	}

	@Override
	public int insertNotice(NoticeDto n, List<AttachDto> at) {
		
		
		int result = noticeDao.insertNotice(n);
		
		if(result > 0 && !at.isEmpty()) {
			result = 0;
			for(AttachDto a : at) {
				result += noticeDao.insertAttach(a);
			}
		}
		
		
		
		return result;
	}

	@Override
	public NoticeDto detailNotice(int no) {
		return noticeDao.detailNotice(no);
	}

	@Override
	public int noticeListCount() {
		return noticeDao.noticeListCount();
	}

	
	
}
