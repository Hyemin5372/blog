package com.lgy.projectblog.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.service.BlogService;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogController {
	@Autowired
	private BlogService service;//service쪽 연결

	
	@RequestMapping("/login")
	public String login() {
		log.info("@# login");
		return "login";
	}
//		@RequestMapping("/login_yn")
//		public String login_yn(@RequestParam HashMap<String, String> param,HttpSession session) {
//			log.info("login");
//			BlogDto dto = service.loginYn(param);
//			
////			service에서 가지고 온 결과=>조건문
//			if (dto != null) {
//				
//				session.setAttribute("userInfo", dto);
//				return "redirect:list";
//			} else {
//				return "redirect:login";
//			}
	@PostMapping("/login_yn")
	public String login_yn(Model model,@RequestParam HashMap<String, String> params, HttpSession session) {
	    log.info("login");
	    String strFromInput = params.get("password");
	    BlogDto dto = service.loginYn(params);

	    if (dto != null) {
	        // 데이터베이스에서 가져온 암호화된 비밀번호
	        String encryptedPasswordFromDB = dto.getPassword();

	        // 사용자가 입력한 비밀번호를 암호화
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encryptedPasswordFromUser = passwordEncoder.encode(strFromInput);

	        // 데이터베이스에 저장된 암호화된 비밀번호와 사용자가 입력한 암호화된 비밀번호 비교
	        if (passwordEncoder.matches(strFromInput, encryptedPasswordFromDB)) {
	            // 로그인 성공
	            session.setAttribute("userInfo", dto);
	            log.info("user정보"+dto.getUser_id());

	            model.addAttribute("userInfo", dto);
	            
	            return "redirect:list";
//	            return "redirect:mypage";
	        } else {
	            // 비밀번호가 일치하지 않을 경우
	            return "redirect:login?error=1"; // 에러 파라미터를 추가하여 로그인 페이지로 전달
	        }
	    } else {
	        // 사용자 정보가 존재하지 않을 경우 (로그인 실패)
	        return "redirect:login?error=2"; // 에러 파라미터를 추가하여 로그인 페이지로 전달
	    }
	}


//	}
//		@RequestMapping("/list")
//		public String bloglist() {
//			
//			return "list";
//		}
		@RequestMapping("/register")
		public String register() {
			return "register";
		}
		@RequestMapping("/registerOk")
//		request가 view에 name값을 찾아감
		public String registerOk(@RequestParam HashMap<String, String> param) {
			String password = param.get("password");
		    String encryptedPassword = Method.encodePassword(password); // 비밀번호 암호화
		    param.put("password", encryptedPassword);
			service.writeLogin(param);
			
			return "redirect:login";
		}
		
		
		@GetMapping("/modify")
		public String modify(HttpSession session, Model model) {
		    if (Method.userInfo(session) == null) {
		    	log.info("modify화면");
		        return "redirect:login";
		    } else {
		        BlogDto user = Method.userInfo(session);
		        model.addAttribute("user", user);
		        log.info("modify화면 user정보"+user);
		        return "modifyInfo";
		    }
		}

		@PostMapping("/modify")
		public ResponseEntity<String> modify(@RequestParam HashMap<String, String> param, HttpSession session) {
		   log.info("modify 실행!!");
			// 현재 비밀번호
		    String strFromInput = param.get("password");

		    // 로그인 된 세션으로 아이디 값을 추가
		    BlogDto user = Method.userInfo(session);
		    param.put("user_id",String.valueOf(user.getUser_id()) );
//		    param.put("user_id", user.getUser_id());
		    log.info( "userid 가지고 오니?"+ user.getUser_id());
		    

		    // 데이터베이스에 있는 비밀번호
		    BlogDto dto = service.loginYn(param);
		    String passDatabase = dto.getPassword();

		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    boolean passwordMatches = passwordEncoder.matches(strFromInput, passDatabase);

		    if (passwordMatches) {
		        String password = param.get("newPassword");
		        String password2 = param.get("passwordConfirmation");

		        // 새 비밀번호와 새 비밀번호 확인이 일치하고, 비밀번호 란이 공백이 아닌 경우
		        if (password.equals(password2) && !password.isEmpty()) {
		            // 입력받은 새 비밀번호를 암호화
		            String encryptedPassword = passwordEncoder.encode(password);

		            // 암호화된 비밀번호를 params에 설정
		            param.put("password", encryptedPassword);

		            // 비밀번호 수정
		            service.modify(param, session);

		            return new ResponseEntity<String>("ok", HttpStatus.OK);
		            
		        } else {
		            return new ResponseEntity<String>("비밀번호와 비밀번호 확인이 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
		        }
		    }
		    return new ResponseEntity<String>("현재 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
		}

		// 회원 탈퇴 화면
	    
		@GetMapping("/deleteuser")
//		@PostMapping("/deleteuser")
		public String Delete(Model model,@RequestParam HashMap<String, String> params,HttpSession session) {
		        BlogDto user = Method.userInfo(session);
		        model.addAttribute("user", user);
		        log.info("delete화면 user정보"+user);
		        service.delete(params, session);
		        return "redirect:login";
		    
		}

	
}
