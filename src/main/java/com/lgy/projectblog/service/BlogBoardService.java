package com.lgy.projectblog.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.dto.Criteria;

public interface BlogBoardService {
	public ArrayList<BlogBoardDto> list (HashMap<String, String> param);
	public ArrayList<BlogBoardDto> list(Criteria cri);
	public void write (HashMap<String, String> param);
	public BlogBoardDto contentView (HashMap<String, String> param);
	public void modifyboard( HashMap<String, String> param);
	public void deleteboard(HashMap<String, String> param);
	BlogBoardDto contentView(HashMap<String, String> param, HttpSession session);
	public int getCount(HashMap<String, String> param);
}
