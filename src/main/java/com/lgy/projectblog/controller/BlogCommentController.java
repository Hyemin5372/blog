package com.lgy.projectblog.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.BlogCommentDto;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.service.BlogCommentService;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogCommentController {
	
	@Autowired private BlogCommentService service;
	
	@PostMapping("/writecomment")
	@ResponseBody
	public ResponseEntity<String> WriteComment(Model model, @RequestParam HashMap<String,String> params, HttpSession session) {
		log.info("@# WriteComment");
		BlogDto user = new Method().userInfo(session);
		BlogBoardDto boardDto = new BlogBoardDto();
				model.addAttribute("board_id", params.get("board_id"));
		        log.info("board id 넘어가니?"+ params.get("board_id"));
		        log.info("넘어오니?");
		        service.writecomment(params, session);
		        log.info("넘어왔니?");
//		    } 

		  return ResponseEntity.status(HttpStatus.OK).body("success");
		}
	@GetMapping("/readcomment")
	public ResponseEntity< ArrayList<BlogCommentDto> > readComment(Model model, @RequestParam HashMap<String, String> params, HttpSession session) {
		String boardId = params.get("board_id");
	    log.info("board_id 정보 가지고 오는가?? " + boardId);
		 
		 ArrayList<BlogCommentDto> BlogCommentDto = service.contentViewcomment(params, session);
		log.info("댓글 내용"+BlogCommentDto);
		return ResponseEntity.status(HttpStatus.OK).body(BlogCommentDto);
	}
	@PostMapping("/editcomment")
	@ResponseBody
	public ResponseEntity<String> editComment(Model model, @RequestParam HashMap<String, String> params, HttpSession session) {
		log.info("수정컨트롤러 시작");
		service.modifycomment(params, session);
		log.info("수정컨트롤러 끝");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping("/deletecomment")
	public ResponseEntity<String> deleteComment(@RequestParam HashMap<String, String> params, HttpSession session) {
		service.deletecomment(params);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
}
