package com.lgy.projectblog.session;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lgy.projectblog.dto.BlogDto;

public class Method {
	public static BlogDto userInfo(HttpSession session){//로그인된 세션 정보
		if (session.getAttribute("userInfo") != null) {
			return(BlogDto)session.getAttribute("userInfo");
		}
		return null;
	}
//	문자열을 암호화
		public static String encodePassword(String password) {	
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			return passwordEncoder.encode( password );
			
			
		}	
//		

}
