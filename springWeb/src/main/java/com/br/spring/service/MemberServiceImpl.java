package com.br.spring.service;

import org.springframework.stereotype.Service;

import com.br.spring.dao.MemberDao;
import com.br.spring.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao;

	@Override
	public MemberDto selectMember(MemberDto m) {
		return memberDao.selectMember(m);
	}

	@Override
	public int insertMember(MemberDto m) {
		return memberDao.insertMember(m);
	}

	@Override
	public int selectUserIdCount(String checkId) {
		return memberDao.selectUserIdCount(checkId);
	}

	@Override
	public int updateMember(MemberDto m) {
		return memberDao.updateMember(m);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(userId);
	}

	@Override
	public int updateProfileImg(MemberDto loginUser) {
		return memberDao.updateProfileImg(loginUser);
	}

	@Override
	public int updatePwd(MemberDto m) {
		return memberDao.updatePwd(m);
	}
	
	
}
