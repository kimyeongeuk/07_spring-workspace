package com.yw.mvcTest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yw.mvcTest.dto.Notice;
import com.yw.mvcTest.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeservice;
	
	@RequestMapping("/list.do")
	public String selectNotice(Model model) {
		
		List<Notice> list = noticeservice.selectNotice();
		
		model.addAttribute("list",list);
		return "notice/list";
	}
	
	@RequestMapping("/detail.do")
	public String detailNotice(Model model,int no) {
		
		Notice n = noticeservice.detailNotice(no);
		
		model.addAttribute("list",n);
		return "notice/detail";
		
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyFormNotice(Model model,int no) {
		Notice n = noticeservice.detailNotice(no);
		
		model.addAttribute("list",n);
		return "notice/modifyForm";
	}
	
	
	@RequestMapping("/update.do")
	public String updateNotice(Notice n, RedirectAttributes ra) {
		
		Notice notice = noticeservice.updateNotice(n);
		
		if(notice != null) {
			ra.addFlashAttribute("alert","수정성공");
		}else {
			ra.addFlashAttribute("alert","수정실패");
		}
		
		
		return "redirect:/notice/detail.do?no="+n.getNo();
	}
	
	@RequestMapping("/delete.do")
	public String deleteNotice(int no, RedirectAttributes ra,Model model) {
		
		List<Notice> result = noticeservice.deleteNotice(no);
		
		if(result.isEmpty()) {
			ra.addFlashAttribute("alert","삭제실패");
			model.addAttribute("list",result);
		}else {
			ra.addFlashAttribute("alert","삭제성공");
			model.addAttribute("list",result);
		}
		
		return "redirect:/notice/list.do";
	}
	
	

}
