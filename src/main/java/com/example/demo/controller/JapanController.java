package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JapanController {
	@Autowired
	private SqlSession ss;
	
	@GetMapping("/")
	public String home() {
		List<Map<String,String>> jList = ss.selectList("com.example.demo.JAPAN.selectJapan");
		return "방가"+jList.toString();
	}

}
