package com.yw.mvcTest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yw.mvcTest.dao.NoticeDao;
import com.yw.mvcTest.dto.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	
	private final NoticeDao noticedao;
	
	@Override
	public List<Notice> selectNotice() {
		
		List<Notice> list = noticedao.selectNotice();
		return list;
	}

	@Override
	public Notice detailNotice(int no) {
		
		Notice n = noticedao.detailNotice(no);
		
		return n;
	}

	@Override
	public Notice updateNotice(Notice n) {
		
		Notice no = noticedao.updateNotice(n);
		
		return no;
	}

	@Override
	public List<Notice> deleteNotice(int no) {
		
		List<Notice> result = noticedao.deleteNotice(no);
		
		return result;
	}

}
