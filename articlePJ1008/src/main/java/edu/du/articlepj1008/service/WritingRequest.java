package edu.du.articlepj1008.service;

import edu.du.articlepj1008.dto.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WritingRequest {
	
	private String writerName;
	private String password;
	private String title;
	private String content;

}


