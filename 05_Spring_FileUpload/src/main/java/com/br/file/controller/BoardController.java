package com.br.file.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;
import com.br.file.service.BoardService;
import com.br.file.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	private final FileUtil fileUtil;
	
	@PostMapping("/insert1.do")
	public String insertOneFileBoard(BoardDto board,MultipartFile uploadFile) {
		// 라이브러리 추가 후 빈 등록을 해야만 값이 잘 담김
		log.	debug("board: {}",board);
		log.debug("attach : {}",uploadFile);
		
		AttachDto attach = null;
		
		if(uploadFile != null && !uploadFile.isEmpty()) { // 첨부파일이 존재하는 경우
			// 전달된 파일 업로드 처리
			// fileUtil 클래스의 fileupload 호출 (uploadFile 넘기면서)
			Map<String, String> map = fileUtil.fileupload(uploadFile);
			
			// 4) db에 기록할 정보를 자바객체(AttachDto)에 세팅
			attach = AttachDto.builder()
							  .filePath(map.get("filePath"))
							  .originalName(map.get("originalName"))
							  .filesystemName(map.get("filesystemName"))
							  .build();
			
		}
		int result = boardService.insertOneFileBoard(board, attach);
		
		
		if(result > 0) {
			log.debug("게시글 등록 성공");
		}else {
			log.debug("게시글 등록 실패");
		}
		
		
		return "redirect:/";
	}
	
	@PostMapping("/insert2.do")
	public String insertManyFileBoard(BoardDto board,List<MultipartFile> uploadFile) {
		
		
		List<AttachDto> list = new ArrayList<>();	
		for(MultipartFile mp : uploadFile) {
			if(mp != null && !mp.isEmpty()) {
				Map<String,String> map = fileUtil.fileupload(mp);
				list.add(AttachDto.builder().filePath(map.get("filePath")).originalName(map.get("originalName")).filesystemName(map.get("filesystemName")).build());
			} 
			
		}
		
		int result = boardService.insertManyFileBoard(board, list);
		
		if(list.isEmpty() && result == 1 || !list.isEmpty() && result == list.size()+1) {
			log.debug("등록 성공");
		}else {
			log.debug("등록 실패");
		}
		
		return "redirect:/";
		
	}
	
	@ResponseBody
	@PostMapping("/ajaxInsert.do")
	public String insertAjaxFileBoard(BoardDto board, MultipartFile uploadFile) {
		
		AttachDto attach = null;
		
		if(uploadFile != null && !uploadFile.isEmpty()) {
			Map<String,String> map = fileUtil.fileupload(uploadFile);
			attach = AttachDto.builder().filePath(map.get("filePath")).originalName(map.get("originalName")).filesystemName(map.get("filesystemName")).build();
		}
		
		int result = boardService.insertOneFileBoard(board, attach);
		
		if(result > 0) {
			log.debug("등록 성공");
			return "SUCCESS";
		}else {
			log.debug("등록 실패");
			return "FAIL";
		}
		
	}
	
	
	@ResponseBody
	@PostMapping("/ajaxInsert2.do")
	public String insertAjaxFileBoard(BoardDto board, List<MultipartFile> uploadFile) {
		
		List<AttachDto> attach = new ArrayList<>();
		if(uploadFile != null && !uploadFile.isEmpty()) {
			for(MultipartFile mf : uploadFile) {
				Map<String,String> map = fileUtil.fileupload(mf);
				attach.add(AttachDto.builder().filePath(map.get("filePath")).originalName(map.get("originalName")).filesystemName(map.get("filesystemName")).build());
				
			}
		}
		
		int result = boardService.insertManyFileBoard(board, attach);
		
		if(result > 0) {
			log.debug("등록 성공");
			return "SUCCESS";
		}else {
			log.debug("등록 실패");
			return "FAIL";
		}
		
	}
	
	@ResponseBody
	@GetMapping(value="/atlist.do",produces="application/json" )
	public List<AttachDto> selectAttachList() {
		
		return boardService.selectAttachList();  // "[{'fileNo':'1', ..}, {'fileNo':'1', ..}]"
		
		
		
	}
	
	
	@PostMapping("/editor/insert.do")
	public String editorInsertBoard(BoardDto board) {
		log.debug("board: {}",board);
		int result = boardService.insertOneFileBoard(board, null);
		
		if(result>0) {
			log.debug("에디터 게시글 등록 성공");
		}else {
			log.debug("에디터 게시글 등록 실패");
		}
		
		return "redirect:/";
	}
	
	
	@ResponseBody
	@PostMapping("editor/imageUpload.do")
	public String editorImageUpload(MultipartFile image) {
		
		Map<String,String> map = fileUtil.fileupload(image);
		return map.get("filePath")+ "/" + map.get("filesystemName");
		// /upload/20241025/
		
	}
	
	@ResponseBody
	@GetMapping(value="/editor/detail.do",produces="application/json")
	public BoardDto editorSelectBoard(int boardNo) {
		BoardDto b = boardService.selectBoard(boardNo);
		return b;
	}
	
	
	
	
	
	

}
