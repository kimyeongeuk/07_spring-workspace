package com.br.spring.dto;

import java.sql.Date;
import java.util.List;

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
public class NoticeDto {
	
	
	 private int noticeNo;
	 private String noticeTitle;
	 private String noticeWriter;
	 private String noticeContent;
	 private Date registDate;
	 
	 private List<AttachDto> attachList;

}
