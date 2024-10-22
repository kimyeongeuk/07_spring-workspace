package com.br.sbatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("list",list);
		
		//return "notice/list";
	}
	
	@GetMapping("/detail.do")
	public void selectNoticeByNo(int no,Model model) {
		
		NoticeDto n = noticeService.selectNoticeByNo(no);
		model.addAttribute("notice",n);
		
	}
	
	@GetMapping("/enroll.do")
	public void noticeEnroll() {
		
	}
	
	@PostMapping("/insert.do")
	public String insertNotice(NoticeDto n,RedirectAttributes ra) {
		int result = noticeService.insertNotice(n);
		
		if(result > 0) {
			ra.addFlashAttribute("alert","등록성공");
			return "redirect:/notice/list.do";
		}
		ra.addFlashAttribute("alert","등록실패");
		return "main";
	}
	
	@GetMapping("/modify.do")
	public void modifyNotice(int no,Model model) {
		
		NoticeDto n = noticeService.selectNoticeByNo(no);
		model.addAttribute("notice",n);
	}
	
	@PostMapping("/modifyForm.do")
	public String modifyFormNotice(NoticeDto n,RedirectAttributes ra) {
		
		int result = noticeService.updateNotice(n);
		
		if(result > 0 ) {
			ra.addFlashAttribute("alert","수정 성공");
			return "redirect:/notice/detail.do?no="+n.getNo();
		}
		
		ra.addFlashAttribute("alert","수정 실패");
		return "redirect:/notice/list.do";
	}
	
	@PostMapping("/delete.do")
	public String deleteNotice(String[] deleteNo,RedirectAttributes ra) {
		
		int result = noticeService.deleteNotice(deleteNo);
		
		if(result>0) {
			ra.addFlashAttribute("alert","삭제 성공");
			return "redirect:/notice/list.do";
		}
		ra.addFlashAttribute("alert","삭제 실패");
		return "main";
	}
	
	
	
}
	
	
