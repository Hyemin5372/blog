package com.lgy.projectblog.dao;

import java.util.HashMap;

import com.lgy.projectblog.dto.BlogDto;

public interface BlogDao {
	public BlogDto loginYn (HashMap<String, String> param);//로그인
	public void writeLogin(HashMap<String, String> param);//회원가입
	public void modify(HashMap<String, String> param);//비밀번호 수정
	public void delete(HashMap<String, String> param);//회원 탈퇴

}
