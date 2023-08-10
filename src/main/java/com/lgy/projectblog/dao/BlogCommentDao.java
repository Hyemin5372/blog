package com.lgy.projectblog.dao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.lgy.projectblog.dto.BlogCommentDto;

public interface BlogCommentDao {
	public void writecomment (@RequestParam HashMap<String, String> param);
	public ArrayList<BlogCommentDto> contentViewcomment(@RequestParam HashMap<String, String> param, HttpSession session);
	public ArrayList<BlogCommentDto> contentViewcomment(@RequestParam HashMap<String, String> param);
	public void modifycomment(@RequestParam HashMap<String, String> param);
	public void deletecomment(HashMap<String, String> param);
}
