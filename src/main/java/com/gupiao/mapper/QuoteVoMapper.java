package com.gupiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gupiao.vo.QuoteVo;

@Mapper
public interface QuoteVoMapper {
	
	@Select(value="select * from quote")
	List<QuoteVo> getQuoteList();
	
	@Insert(value="insert into quote (name,code,url) values (#{name},#{code},#{url})")
	int saveQuote(QuoteVo quoteVo);
	
	int bathSaveQuote(List<QuoteVo> list);
}
