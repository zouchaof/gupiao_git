package com.gupiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gupiao.business.QuoteDetailHandler;
import com.gupiao.business.QuoteHandler;
import com.gupiao.mapper.QuoteTransDetailMapper;
import com.gupiao.mapper.QuoteVoMapper;
import com.gupiao.vo.QuoteVo;

@Service
public class QuoteService {
	
	@Autowired
	private QuoteVoMapper quoteVoMapper;
	@Resource
	private QuoteDetailHandler detailHandler;
	@Autowired
	private QuoteTransDetailMapper quoteTransDetailMapper;
	
	public void test(){
//		QuoteVo quote = new QuoteVo();
//		quote.setName("测试");
//		quote.setCode("99");
//		quote.setUrl("123");
//		quoteVoMapper.saveQuote(quote);
//		List<QuoteVo> list= quoteVoMapper.getQuoteList();
//		List<QuoteVo> list= QuoteHandler.getQuoteList();
//		int i = quoteVoMapper.bathSaveQuote(list);
//		System.out.println(i);
//		List<QuoteVo> list= quoteVoMapper.getQuoteList();
//		QuoteVo quoteVo = list.get(0);
		QuoteVo quoteVo = new QuoteVo();
		quoteVo.setCode("601669");
		quoteVo.setName("中国电建");
		int i = quoteTransDetailMapper.bathSaveQuoteDetail(detailHandler.getTransDetail(quoteVo));
		System.out.println(i);
	}
	
}
