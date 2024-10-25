package com.br.spring.service;

import org.springframework.stereotype.Service;

import com.br.spring.dao.MemberDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao;
	
	
}
