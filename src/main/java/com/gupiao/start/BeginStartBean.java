package com.gupiao.start;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.gupiao.service.QuoteDetailService;
import com.gupiao.service.QuoteService;

@Component
public class BeginStartBean implements InitializingBean{

	@Resource
	private QuoteDetailService quoteDetailService;
	@Resource
	private QuoteService quoteService;
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(1);
//		quoteService.test();
	}

}
