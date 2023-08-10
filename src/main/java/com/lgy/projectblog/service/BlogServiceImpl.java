package com.lgy.projectblog.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.projectblog.dao.BlogDao;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Service("BlogService")
@Slf4j
public class BlogServiceImpl implements BlogService{
	@Autowired
	private SqlSession sqlSession;//dao쪽 연결

	@Override
	public BlogDto loginYn(HashMap<String, String> param) {
		log.info("@# BlogServiceImpl ---- loginYn");
		BlogDao dao =sqlSession.getMapper(BlogDao.class);

		BlogDto dto = dao.loginYn(param);

		return dto;
	}



	@Override
	public void writeLogin(HashMap<String, String> param) {
		BlogDao dao =sqlSession.getMapper(BlogDao.class);
		dao.writeLogin(param);
		
	}

	@Override
	public void modify(HashMap<String, String> param, HttpSession session) {
	    BlogDao dao = sqlSession.getMapper(BlogDao.class);
	    BlogDto user = new Method().userInfo(session);
	    param.put("user", String.valueOf(user.getUser_id()));
	    log.info(param.toString());
	    dao.modify(param);
	}


	@Override

		public void delete(HashMap<String, String> param, HttpSession session) {
		log.info("@# BlogServiceImpl ---- delete");
		BlogDao dao =sqlSession.getMapper(BlogDao.class);
		log.info(param.toString());
		dao.delete(param);
		
	}

}
