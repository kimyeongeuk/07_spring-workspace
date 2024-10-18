package com.br.ajax.service;

import java.util.List;

import com.br.ajax.dto.MemberDto;

public interface MemberService {
	
	// 아이디와 비번으로 특정 회원 이름 조회
	String selectMemebrById(String userId,String userPwd);
	// 번호 가지고 회원 전체정보 조회
	MemberDto selectMemberByNo(int userNo);
	// 회원 전체 목록 조회하는 서비스
	List<MemberDto> selectMemberList();

}
