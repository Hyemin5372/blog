package com.lgy.projectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogBoardDto {
	private int board_id;
	private String title;
	private String content;
//	private Date created;
	private int user_id;
//	private String user_id;
	
}
