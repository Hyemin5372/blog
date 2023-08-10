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
//		String pw = param.get("password");
		BlogDto dto = dao.loginYn(param);
//			
//		if (dto == null) {	// 아이디 존재 하지 않음
//			return null;
//		} else {
//			if (pw.equals(dto.getPassword())) {
//				log.info("@# BlogServiceImpl ---- " + dto.getPassword());
//				return dto;//둘다 맞음
//				
//			} else {
//
//				return null;
//			}
//		}
		return dto;
	}
//	@Override
//	public BlogDto loginYn(HashMap<String, String> param) {
//		BlogDao dao = sqlSession.getMapper(BlogDao.class);
//	    log.info("@# BlogServiceImpl ---- loginYn");
//	    String username = param.get("username"); // HashMap에서 username을 가져옴
//	    BlogDto dto = dao.loginYn(param); // username을 기준으로 검색
//
//	    if (dto == null) {
//	        // 아이디 존재하지 않음
//	        return null;
//	    } else {
//	        // 검색된 유저 정보와 입력한 비밀번호를 비교하여 처리하는 로직
//	        // (기존 코드와 동일하게 유지)
//	        String pw = param.get("password");
//	        if (pw.equals(dto.getPassword())) {
//	            log.info("@# BlogServiceImpl ---- " + dto.getPassword());
//	            return dto;
//	        } else {
//	            return null;
//	        }
//	    }
//	}


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
//	public void delete(HashMap<String, String> param) {
		public void delete(HashMap<String, String> param, HttpSession session) {
		log.info("@# BlogServiceImpl ---- delete");
		BlogDao dao =sqlSession.getMapper(BlogDao.class);
		log.info(param.toString());
		dao.delete(param);
		
	}

}
