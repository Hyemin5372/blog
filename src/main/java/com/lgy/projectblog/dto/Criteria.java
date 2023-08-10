package com.lgy.projectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Criteria {
	private int pageNum;//페이지 번호
	private int amount;//페이지당 글 갯수
	
	public Criteria() {
		this(1, 5);
	}
}
