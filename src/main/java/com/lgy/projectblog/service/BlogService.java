package com.lgy.projectblog.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.lgy.projectblog.dto.BlogDto;


public interface BlogService {
	public BlogDto loginYn (HashMap<String, String> param);//로그인
//	public BlogDto loginYn (String username);//로그인	
	public void writeLogin(HashMap<String, String> param);//회원가입
	public void modify(HashMap<String, String> param,HttpSession session);//비밀번호 수정
//	public void delete(HashMap<String, String> param);//회원 탈퇴
	public void delete(HashMap<String, String> param, HttpSession session);//회원 탈퇴

}
