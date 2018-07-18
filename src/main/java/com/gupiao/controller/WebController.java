package com.gupiao.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gupiao.service.QuoteDetailService;
import com.gupiao.service.QuoteService;

@RestController
@RequestMapping("/web")
public class WebController {
	
	private static final Logger logger =  LoggerFactory.getLogger(WebController.class);
	
	@RequestMapping("index")
	public String index(ModelMap map){
		logger.info("这里是controller");
		map.put("title", "hello world");
		return "index"; // 注意，不要在最前面加上/，linux下面会出错
	}
	
	@Resource
	private QuoteService quoteService;
	@Resource
	private QuoteDetailService quoteDetailService;
	
//	@RequestMapping("test")
//	@ResponseBody
//	public String index3(HttpServletRequest request){
//		quoteDetailService.selectAll();
//	}
	
	@RequestMapping()
	@ResponseBody
	public String index2(HttpServletRequest request){
		quoteService.test();
		return request.getSession().getId()+"---"+request.getLocalPort();
	}
}
