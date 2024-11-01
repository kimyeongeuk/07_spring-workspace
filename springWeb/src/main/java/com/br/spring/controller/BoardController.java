package com.br.spring.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.MemberDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.dto.ReplyDto;
import com.br.spring.service.BoardService;
import com.br.spring.util.FileUtil;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final PagingUtil pagingUtil;
	private final FileUtil fileUtil;
	
	
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
	
	
	@GetMapping("/search.do")
	public String search(@RequestParam(value = "page", defaultValue = "1")int currentPage,@RequestParam Map<String,String> search
										,Model model) {
		
		// Map<String,String> search ==> {condition=user_Id|board_title|..}
		
		int listCount = boardService.selectSearchListCount(search);
		
		PageInfoDto pi = pagingUtil.getPageInfoDto(listCount, currentPage, 5, 5);
		List<BoardDto> list = boardService.selectSearchList(search,pi);
		
		model.addAttribute("pi",pi);
		model.addAttribute("list",list);
		model.addAttribute("search",search);
		
		return "board/list";
	}
	
	@GetMapping("/regist.do")
	public void registPage() {}
	
	@PostMapping("/insert.do")
	public String regist(BoardDto b,List<MultipartFile> uploadFiles,HttpSession session,RedirectAttributes ra) {
		
		// board 테이블에 insert할 데이터
		b.setBoardWriter(String.valueOf(((MemberDto)session.getAttribute("loginUser")).getUserNo()));
		// 첨부파일 업로드 후에
		// attachment테이블에 insert할 데이터
		List<AttachDto> attachList = new ArrayList<>();
		for(MultipartFile file : uploadFiles) {
			if(file != null && !file.isEmpty()) {
				Map<String,String> map = fileUtil.fileupload(file, "board");
				attachList.add(AttachDto.builder().filePath(map.get("filePath")).originalName(map.get("originalName"))
												  .filesystemName(map.get("filesystemName"))
												  .refType("B").build());
			}
		}
		
		b.setAttachList(attachList); // 제목,내용,작성자회원번호, 첨부파일들 정보 담김
		
		int result = boardService.insertBoard(b);
		
		
		if(attachList.isEmpty() && result == 1 || !attachList.isEmpty() && result == attachList.size()) {
			ra.addFlashAttribute("alertMsg","게시글 등록 성공");
		}else {
			ra.addFlashAttribute("alertMsg","게시글 등록 실패");
		}
		
		return "redirect:/board/list.do";
		
	}

	
	@GetMapping("/increase.do") // 조회수 증가용 (타인의 글일 경우 호출) /board/detail.do 재요청
	public String increaseCount(int no) {
		
		boardService.updateIncreaseCount(no);
		return "redirect:/board/detail.do?no="+no;
	}
	
	
	
	
	
	
	@GetMapping("/detail.do") // 게시글 상세조회용 (내글일 경우 이걸로 바로 호출)
	public void detail(int no,Model model) {
		// 상세페이지에 필요한 데이터
		
		// 게시글(제목,작성자,작성일,내용) 데이터, 첨부파일(원본명,저장경로,실제파일명)들 데이터
		BoardDto b = boardService.selectBoard(no);
		
		
		model.addAttribute("b",b);
		
		
	}
	
	@ResponseBody
	@GetMapping(value="/rlist.do", produces="application/json")
	public List<ReplyDto> replyList(int no) {
		
		List<ReplyDto> list = boardService.selectReplyList(no);
		log.debug("{}",list);
		
		
		return list;
		
	}
	
	@ResponseBody
	@PostMapping("/rinsert.do")
	public String replyInsert(ReplyDto r,HttpSession session,RedirectAttributes ra) {
		
		r.setReplyWriter(String.valueOf(((MemberDto)session.getAttribute("loginUser")).getUserNo()));
		
		int result = boardService.insertReply(r);
		return result > 0 ? "SUCCESS" : "FAIL";
		
		
	}
	
	
	@PostMapping("/delete.do")
	public String delete(int no, RedirectAttributes ra) {
		
		int result = boardService.deleteBoard(no);
		
		if(result > 0) {
			ra.addFlashAttribute("alertMsg","게시글이 삭제되었습니다");
		}else {
			ra.addFlashAttribute("alertMsg","게시글 등록 실패");
		}
		
		
		return "redirect:/board/list.do";
	}
	
	
	@PostMapping("/modify.do")
	public void modifyPage(int no, Model model) {
		model.addAttribute("b",boardService.selectBoard(no));
		
	}
	
	@PostMapping("/update.do")
	public String modify(BoardDto b // 번호,제목,내용 담김 
					  ,String[] delFileNo // 삭제할 첨부파일 번호
					  ,List<MultipartFile> uploadFiles // 새로 넘어온 첨부파일들
					  , RedirectAttributes ra) {
		
		// 후에 db에 반영 성공시 삭제할 파일들 삭제 위해 미리 조회
		List<AttachDto> delAttachList = boardService.selectDelAttach(delFileNo);
		List<AttachDto> attachList = new ArrayList<>();
		for(MultipartFile file : uploadFiles) {
			if(file != null && !file.isEmpty()) {
				Map<String,String> map = fileUtil.fileupload(file, "board");
				attachList.add(AttachDto.builder().filePath(map.get("filePath"))
												  .originalName(map.get("originalName"))
												  .filesystemName(map.get("filesystemName"))
												  .refType("B")
												  .refNo(b.getBoardNo()).build());
			}
		}
		b.setAttachList(attachList);
		int result = boardService.updateBoard(b,delFileNo);
		
		if(result > 0) { // 성공
			ra.addFlashAttribute("alertMsg","성공적으로 수정되었습니다.");
			for(AttachDto at : delAttachList) {
				new File(at.getFilePath()+"/"+at.getFilesystemName()).delete();
			}
		}else { // 실패
			ra.addFlashAttribute("alertMsg","게시글 수정에 실패하였습니다.");
		}
		
		return "redirect:/board/detail.do?no="+b.getBoardNo();
		
		
	}
	
	
	
}
