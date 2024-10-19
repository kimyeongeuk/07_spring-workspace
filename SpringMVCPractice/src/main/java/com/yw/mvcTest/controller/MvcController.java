package com.yw.mvcTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
	
	
	@RequestMapping(value={"/","main.do"}, method=RequestMethod.GET)
	public String mainPage() {
		return "main";
	}
	
	

}
