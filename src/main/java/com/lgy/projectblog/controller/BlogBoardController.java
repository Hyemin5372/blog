package com.lgy.projectblog.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lgy.projectblog.dto.BlogBoardDto;
import com.lgy.projectblog.dto.BlogDto;
import com.lgy.projectblog.dto.Criteria;
import com.lgy.projectblog.dto.PageDTO;
import com.lgy.projectblog.service.BlogBoardService;
import com.lgy.projectblog.session.Method;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogBoardController {

	@Autowired private BlogBoardService service;
//	@GetMapping("/list")
//	public String List(Model model,@RequestParam HashMap<String,String> param, HttpSession session) {
//		log.info("@# list");
//		ArrayList<BlogBoardDto> list = service.list(param);
//		model.addAttribute("list", list);
//		return "list";
//	}
//	페이징
	@RequestMapping("/list")
	public String boardList(Criteria cri ,@RequestParam HashMap<String,String> params, Model model) {
		log.info("@# list");
		log.info("@# cri ===> "+cri);
		
//		ArrayList<BDto> list = service.list();
//		model.addAttribute("list", list);
		model.addAttribute("list", service.list(cri));
//		model.addAttribute("pageMaker", new PageDTO(123, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getCount(params), cri));

		
		return "list";
	}
	@GetMapping("/write")
	public String Write(Model model) {
		log.info("@# GET write");
		return "write";
	}
	@PostMapping("/write")
	public String Write(Model model, @RequestParam HashMap<String,String> params, HttpSession session) {
		log.info("@# POST write");
		BlogDto user = new Method().userInfo(session);
		  if (user != null) {
		        // 로그인된 사용자 정보가 있을 경우, user_id를 BlogBoardDto에 설정
		        BlogBoardDto boardDto = new BlogBoardDto();
		        params.put("user_id", String.valueOf(user.getUser_id()));

		        service.write(params);

		        log.info("서비스단 실행됬는가");
		    } else {
		        // 로그인된 사용자 정보가 없을 경우에 대한 처리 (예: 로그인 페이지로 이동)
		    }

		    return "redirect:list";
		}
	@GetMapping("/read")
	public String read(Model model, @RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# read");
		log.info("board_id_ parameter check " + param.get("board_id"));
		BlogDto user = new Method().userInfo(session);
		log.info("누구냐 ???" + user);
		if (user != null) {
			BlogBoardDto dto = service.contentView(param,session);
			log.info("dto ???" + dto);
			model.addAttribute("board", dto);
			
			//댓글
			model.addAttribute("user_id", user.getUser_id());
//		model.addAttribute("board", service.contentView(param));
			Integer userId1 = user.getUser_id();
			Integer userId2 = dto.getUser_id();
//
//			boolean isCurrentUser = userId1.equals(userId2);
			boolean isCurrentUser = userId2 == 0 || userId1.equals(userId2);
			log.info("userid ==>"+ userId1);
			log.info("dtoid ==>"+ userId2);
	        model.addAttribute("isCurrentUser", isCurrentUser);
	        log.info("본인이냐 ???" + isCurrentUser);
			return"read";
			
		} else {
			model.addAttribute("user_id", -1);
			return "redirect:login";
		}
	}
//	@GetMapping("/edit")
//	public String Edit(Model model,@RequestParam HashMap<String, String> params, HttpSession session) {
//		log.info("@# edit");
//		model.addAttribute("board", service.contentView(params));
//		return "edit";
//	}
	@GetMapping("/edit")
	public String Edit(Model model, @RequestParam HashMap<String, String> params, HttpSession session) {
	    log.info("@# edit");
	    BlogBoardDto board = service.contentView(params,session);
	    log.info("board ???" + board);
	    model.addAttribute("board", board);
	    return "edit";
	}



//	@PostMapping("/edit")
	@PostMapping("/modifyboard")
	public String ModifyBoard(Model model,@RequestParam HashMap<String, String> params, HttpSession session,@ModelAttribute("cri") Criteria cri ,RedirectAttributes rttr) {
		log.info("@# modifyboard");
			service.modifyboard(params);
//			페이지 이동시 뒤에 페이지 번호, 글 갯수 추가
//			게시글 수정 후 해당 페이지에 머물기
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:list";

	}
	
	

	@GetMapping("/delete")
	public String DeleteBoard(Model model,@RequestParam HashMap<String, String> params, HttpSession session) {
		log.info("@# delete");

			service.deleteboard(params);

		return "redirect:list";
		
	}
		
}
