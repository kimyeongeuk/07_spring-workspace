package com.br.spring.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReplyDto {
	
	
	 private int replyNo;
	 private String replyContent;
	 private int refBno;
	 private String replyWriter;
	 private Date registDate;
	 private String status;

}