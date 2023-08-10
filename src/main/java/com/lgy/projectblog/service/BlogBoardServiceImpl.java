package com.lgy.projectblog.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.projectblog.dao.BlogBoardDao;
import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.dto.Criteria;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("BlogBoardService")
public class BlogBoardServiceImpl implements BlogBoardService {
	@Autowired
	private SqlSession sqlSession;//dao쪽 연결

	@Override
	public ArrayList<BlogBoardDto> list(HashMap<String, String> param) {
		BlogBoardDao dao =sqlSession.getMapper(BlogBoardDao.class);
		return dao.list(param);
	}

	@Override
	public void write(HashMap<String, String> param) {
		BlogBoardDao dao =sqlSession.getMapper(BlogBoardDao.class);
//		 
		dao.write(param);
	}

	@Override
	public BlogBoardDto contentView(HashMap<String, String> param, HttpSession session) {
		log.info("BlogBoardServiceImpl ===> contentView with Session");
		log.info("board_id_ parameter check " + param.get("board_id"));
		BlogBoardDao dao = sqlSession.getMapper(BlogBoardDao.class);
		log.info("BlogBoardServiceImpl ===> sqlSession 바로 아래");
		
		
		
		BlogDto user = new Method().userInfo(session);
		log.info("BlogBoardServiceImpl ===> user 바로 아래" + user);
		
		param.put("user_id", user.getUsername());
		log.info("BlogBoardServiceImpl ===> put 바로 아래");
		
		return dao.contentView(param);
	}

	@Override
	public void modifyboard(HashMap<String, String> param) {
		BlogBoardDao dao =sqlSession.getMapper(BlogBoardDao.class);
		dao.modifyboard(param);
		
	}

	@Override
	public void deleteboard(HashMap<String, String> param) {
		BlogBoardDao dao =sqlSession.getMapper(BlogBoardDao.class);
		dao.deleteboard(param);
	}

	@Override
	public BlogBoardDto contentView(HashMap<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BlogBoardDto> list(Criteria cri) {
		log.info("@# BlogBoardServiceImpl.list(Criteria cri) start");
		log.info("@# cri ===>"+cri);
		
		
		BlogBoardDao dao = sqlSession.getMapper(BlogBoardDao.class);
		
		log.info("@# BlogBoardServiceImpl.list(Criteria cri) end");
		
		return dao.listWithPaging(cri);
	
	}

	@Override
	public int getCount(HashMap<String, String> param) {
		BlogBoardDao dao = sqlSession.getMapper(BlogBoardDao.class);
		return dao.getCount(param);
	}

}
