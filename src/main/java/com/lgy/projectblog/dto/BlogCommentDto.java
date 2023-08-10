package com.lgy.projectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentDto {
	private int comment_id;
	private String content;
	private String created;
	private String username;
	private int user_id;
	private int board_id;

}
