package com.lgy.projectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
	public int user_id;
	private String username;
	private String email;
	private String password;
	
}
