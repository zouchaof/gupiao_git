package com.gupiao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gupiao.business.QuoteDetailHandler;
import com.gupiao.mapper.QuoteTransDetailMapper;
import com.gupiao.mapper.QuoteVoMapper;
import com.gupiao.vo.QuoteTransDetailVo;
import com.gupiao.vo.QuoteVo;

@Service
public class QuoteDetailService {
	
	@Autowired
	private QuoteVoMapper quoteVoMapper;
	@Autowired
	private QuoteTransDetailMapper quoteTransDetailMapper;
	@Autowired
	private QuoteDetailHandler detailHandler;
	
	public void test(){
		List<QuoteVo> list= quoteVoMapper.getQuoteList();
		detailHandler.toHandleQuote(list);
	}
	
	public List<QuoteTransDetailVo> selectAll(){
		return quoteTransDetailMapper.getQuoteTransDetailVoList();
	}
}
