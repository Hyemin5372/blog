package com.lgy.projectblog.dao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.Criteria;

public interface BlogBoardDao {
	public ArrayList<BlogBoardDto> list (HashMap<String, String> param);
	public ArrayList<BlogBoardDto> listWithPaging(Criteria cri);
	public void write (HashMap<String, String> param);
	public BlogBoardDto contentView (HashMap<String, String> param, HttpSession session);
	public BlogBoardDto contentView (HashMap<String, String> param);
	public void modifyboard(HashMap<String, String> param);
	public void deleteboard(HashMap<String, String> param);
	public int getCount(HashMap<String, String> param);
}
