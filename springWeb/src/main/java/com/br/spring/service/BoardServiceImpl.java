package com.br.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.br.spring.dao.BoardDao;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;

	@Override
	public int selectBoardListCount() {
		return boardDao.selectBoardListCount();
	}

	@Override
	public List<BoardDto> selectBoardList(PageInfoDto pi) {
		return boardDao.selectBoardList(pi);
	}

	@Override
	public int selectSearchListCount(Map<String, String> search) {
		return 0;
	}

	@Override
	public List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi) {
		return null;
	}

	@Override
	public int insertBoard(BoardDto b) {
		return 0;
	}

	@Override
	public int updateIncreaseCount(int boardNo) {
		return 0;
	}

	@Override
	public BoardDto selectBoard(int boardNo) {
		return null;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return 0;
	}

	@Override
	public List<ReplyDto> selectReplyList(int boardNo) {
		return null;
	}

	@Override
	public int insertReply(ReplyDto r) {
		return 0;
	}
	
	
}
