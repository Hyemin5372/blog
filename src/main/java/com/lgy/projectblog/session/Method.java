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
			
			// 문자열을 암호화하기 위해 필요한 확장 기능(디펜던시) 가져옴,  이름은 passwordEncoder 이라고 작명 
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			//String str1 = "";
			//String str2 = passwordEncoder.encode( str1 );
			return passwordEncoder.encode( password );
			
			
		}	
//		public String getDate() {
//			LocalDateTime now = LocalDateTime.now();
//			Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 작성", new Locale("ko", "KR"));
//			String formattedDate = sdf.format(date);
//			
//			return formattedDate;
//		}
//		

}
