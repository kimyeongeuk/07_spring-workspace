package com.br.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.MemberDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.service.NoticeService;
import com.br.spring.util.FileUtil;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	private final FileUtil fileUtil;
	private final PagingUtil pagingUtil;
	
	@GetMapping("/list.do")
	public void listPage(@RequestParam(value="page",defaultValue="1") int currentPage, Model model) {
		
		int listCount = noticeService.noticeListCount();
		
		PageInfoDto pi = pagingUtil.getPageInfoDto(listCount, currentPage, 5, 5);
		
		
		List<NoticeDto> list = noticeService.selectNotice(pi);

		model.addAttribute("list",list);
		model.addAttribute("pi",pi);
				
	}
	
	@GetMapping("/regist.do")
	public void insertPage() {}
	
	
	@PostMapping("/insert.do")
	public String insert(NoticeDto n,List<MultipartFile> uploadFiles,HttpSession session,RedirectAttributes ra) {
		
		List<AttachDto> at = new ArrayList<>();
		
		for(MultipartFile m : uploadFiles) {
			if(m != null && !m.isEmpty()) {
				Map<String,String> map = fileUtil.fileupload(m, "notice");
				at.add(AttachDto.builder().filePath(map.get("filePath"))
										  .originalName(map.get("originalName"))
										  .filesystemName(map.get("filesystemName")).build());
			}
			
		}
		n.setAttachList(at);
		n.setNoticeWriter(String.valueOf(((MemberDto)session.getAttribute("loginUser")).getUserNo()));
		int result = noticeService.insertNotice(n,at);
		
		if(result == 1 && at.isEmpty() || result == at.size()) {
			ra.addFlashAttribute("alertMsg","등록 성공");
		}else {
			ra.addFlashAttribute("alertMsg","등록 실패");
		}
		 
		return "redirect:/notice/list.do";
		
	}
	
	
	@GetMapping("/detail.do")
	public void detailPage(int no, Model model) {
		
		NoticeDto n = noticeService.detailNotice(no);
		
		model.addAttribute("notice",n);	
		
	}
	
	
	
	
}
