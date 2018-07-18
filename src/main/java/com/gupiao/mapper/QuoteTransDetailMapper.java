package com.gupiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gupiao.vo.QuoteTransDetailVo;

@Mapper
public interface QuoteTransDetailMapper {
	
	@Select(value="select * from quote_trans_detail")
	List<QuoteTransDetailVo> getQuoteTransDetailVoList();
//	
//	@Insert(value="insert into quote (name,code,url) values (#{name},#{code},#{url})")
//	int saveQuote(QuoteVo quoteVo);
	
	int bathSaveQuoteDetail(List<QuoteTransDetailVo> list);
}
