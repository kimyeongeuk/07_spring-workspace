package com.yw.mvcTest.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yw.mvcTest.dto.Notice;


@Repository
public class NoticeDao {
	
	
	private List<Notice> dbList = Arrays.asList(new Notice(1,"테스트1","내용1"), new Notice(2,"테스트2","내용2"),new Notice(3,"테스트3","내용3"));
	
	private List<Notice> dbList2 = new ArrayList<>();

	public List<Notice> selectNotice() {
		dbList2.add(new Notice(1,"테스트1","내용1"));
		dbList2.add(new Notice(2,"테스트2","내용2"));
		dbList2.add(new Notice(3,"테스트3","내용3"));
		
		return dbList2;
	}


	public Notice detailNotice(int no) {
		
		for(Notice n : dbList2) {
			if(n.getNo() == no) {
				return n;
			}
		}
		
		return null;
	}



	public Notice updateNotice(Notice n) {
		
		for(Notice not : dbList2) {
			if(not.getNo() == n.getNo()) {
				not.setTitle(n.getTitle());
				not.setContent(n.getContent());
				return not;
			}
		}
		
		return null;
	}


	public List<Notice> deleteNotice(int no) {

		
		
		for(int i=0;i<dbList2.size();i++) {
			if(dbList2.get(i).getNo() == no) {
				dbList2.remove(i);
			}
		}
		
		return dbList2;
	}

}
