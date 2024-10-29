package com.br.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.service.BoardService;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final PagingUtil pagingUtil;
	
	
	// 메뉴바에있는 메뉴 클릭시 		/board/list.do
	// 페이징바에 있는 페이지 클릭시	/board/list.do?page=xx
	@GetMapping("/list.do")
	public void list(@RequestParam(value="page",defaultValue="1") int currentPage,Model model) {
		
		int listCount = boardService.selectBoardListCount();
		
		PageInfoDto pi = pagingUtil.getPageInfoDto(listCount, currentPage, 5, 5);
		List<BoardDto> list = boardService.selectBoardList(pi);
		
		model.addAttribute("pi",pi);
		model.addAttribute("list",list);
		
		
	}
	
	
}
