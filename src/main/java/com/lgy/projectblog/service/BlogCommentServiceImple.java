package com.lgy.projectblog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.projectblog.dao.BlogCommentDao;
import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.BlogCommentDto;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("BlogCommentService")
public class BlogCommentServiceImple implements BlogCommentService {
	
	@Autowired SqlSession sqlSession;
	
	@Override
		public void writecomment(HashMap<String, String> param, HttpSession session) {
		BlogCommentDao dao = sqlSession.getMapper(BlogCommentDao.class);
        BlogBoardDto boardDto = new BlogBoardDto();
        Date date = new Date();
        
        param.put("created", String.valueOf(date));
        param.put("user_id", String.valueOf(Method.userInfo(session).getUser_id()));
        log.info("유저 아이디 잘 전달하니?"+String.valueOf(Method.userInfo(session).getUser_id()));
        
        log.info("========================================================================");
        log.info("param 값에는 잘 저장되있니? => " + param.toString() );
        log.info("========================================================================");
		dao.writecomment(param);
		
	}

	@Override
	public ArrayList<BlogCommentDto> contentViewcomment(HashMap<String, String> param, HttpSession session) {
		BlogCommentDao dao = sqlSession.getMapper(BlogCommentDao.class);
		
		 
		 ArrayList<BlogCommentDto> result = dao.contentViewcomment(param);
		    log.info("Result from dao: " + result);
		return result;
	}

	@Override
	public void modifycomment(HashMap<String, String> param, HttpSession session) {
		BlogCommentDao dao = sqlSession.getMapper(BlogCommentDao.class);
		log.info("수정까지 오니?");
		dao.modifycomment(param);
	}

	@Override
	public void deletecomment(HashMap<String, String> param) {
		BlogCommentDao dao = sqlSession.getMapper(BlogCommentDao.class);
		dao.deletecomment(param);
		
	}

}
