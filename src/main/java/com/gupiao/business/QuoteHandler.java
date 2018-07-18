package com.gupiao.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.gupiao.util.HttpRequestUtil;
import com.gupiao.vo.QuoteVo;

@Component
public class QuoteHandler {
	
	private String parentUrl = "http://quote.eastmoney.com/stocklist.html";

	/**
	 * 获取股票列表，（目标：有新的就新增）
	 * @return
	 */
	public List<QuoteVo> getQuoteList(){
		List<QuoteVo> list = new ArrayList<QuoteVo>();
		String html = HttpRequestUtil.getMethod(parentUrl,"gbk");
		if(StringUtils.isBlank(html)){
			return null;
		}
		Document document = Jsoup.parse(html);
		Elements elements = document.select("div#quotesearch>ul>li>a[href]");
//		System.out.println(elements.get(0));
		if(elements!=null){
			for(int i=0;i<elements.size();i++){
				Element el = elements.get(i);
				String content = el.text();
				String href = el.attr("href");
				if(!content.contains("(")||!content.contains(")")){
					continue;
				}
				String name = StringUtils.substringBefore(content, "(");
				String code = StringUtils.substringBetween(content, "(", ")");
				QuoteVo quoteVo = new QuoteVo();
				quoteVo.setName(name);
				quoteVo.setCode(code);
				quoteVo.setUrl(href);
				list.add(quoteVo);
			}
		}
		return list;
	}
	
	
	
	
	
}
