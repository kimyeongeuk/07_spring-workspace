package com.br.sbatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.sbatis.dto.NoticeDto;
import com.br.sbatis.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	
	@GetMapping("/list.do") // /notice/list.do
	public void noticeList(Model model) {
		
		List<NoticeDto> list = noticeService.selectNoticeList();
		log.debug("list : {}",list);
		model.addAttribute("list",list);
		
		//return "notice/list";
	}
	
	
	
	
	
}
