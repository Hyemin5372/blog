package com.lgy.projectblog.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.lgy.projectblog.dto.BlogCommentDto;

public interface BlogCommentService {
//	public void writecomment (HashMap<String, String> param);
	public void writecomment (HashMap<String, String> param, HttpSession session);
//	public ArrayList<BlogCommentDto> contentViewcomment(@RequestParam HashMap<String, String> param);
	public ArrayList<BlogCommentDto> contentViewcomment(@RequestParam HashMap<String, String> param, HttpSession session);
	public void modifycomment(@RequestParam HashMap<String, String> param, HttpSession session);
	public void deletecomment(HashMap<String, String> param);

}
