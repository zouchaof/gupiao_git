package com.gupiao.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gupiao.mapper.QuoteTransDetailMapper;
import com.gupiao.util.DateUtils;
import com.gupiao.util.HttpRequestUtil;
import com.gupiao.vo.QuoteTransDetailVo;
import com.gupiao.vo.QuoteVo;

@Component
public class QuoteDetailHandler {
	
	@Autowired
	private QuoteTransDetailMapper quoteTransDetailMapper;
	
	private String baseUrl = "http://quotes.money.163.com/trade/lsjysj_%s.html?year=%s&season=%s";
	private int beginYear = 2011;
	
	ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
	
	public void toHandleQuote(List<QuoteVo> list){
        if(list==null){
        	return;
        }
        for(int i=0;i<list.size();i++){
            OneHandleThread myTask = new OneHandleThread(list.get(i));
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
            executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
	}
	
	
	public List<QuoteTransDetailVo> getTransDetail(QuoteVo quote){
		List<QuoteTransDetailVo> list = new ArrayList<>();
		if(quote==null){
			return null;
		}
		
		int nowyear = getNowYear();
		
		for(int i=beginYear;i<=nowyear;i++){
			int countseason = 4;
			if(i==nowyear){
				countseason = getNowSeason();
			}
			for(int j=1;j<=countseason;j++){
				String url = String.format(baseUrl, quote.getCode(),i,j);
				String html = HttpRequestUtil.getMethod(url, "utf-8");
				Document document = Jsoup.parse(html);
				Elements elements = document.select("table.table_bg001.border_box.limit_sale>tbody>tr");
				if(elements!=null){
					for(Element el:elements){
						Elements elements2 = el.select("td");
						if(elements2!=null&&elements2.size()>10){
							QuoteTransDetailVo detailVo = new QuoteTransDetailVo();
							try{
								String dateStr = elements2.get(0).text().replace("-", "");
								detailVo.setCode(quote.getCode());
								detailVo.setTransTime(dateStr);
								detailVo.setBeginPrice(new BigDecimal(elements2.get(1).text()));
								detailVo.setMaxPrice(new BigDecimal(elements2.get(2).text()));
								detailVo.setMinPrice(new BigDecimal(elements2.get(3).text()));
								detailVo.setEndPrice(new BigDecimal(elements2.get(4).text()));
								detailVo.setTransCount(Integer.valueOf(elements2.get(7).text().replace(",", "")));
								detailVo.setTransTotal(Integer.valueOf(elements2.get(8).text().replace(",", "")));
								detailVo.setChangePrice(new BigDecimal(elements2.get(5).text()));
								detailVo.setChangePercent(new BigDecimal(elements2.get(6).text()));
								detailVo.setSwing(new BigDecimal(elements2.get(9).text()));
								detailVo.setTurnover(new BigDecimal(elements2.get(10).text()));
								detailVo.setWeekday(DateUtils.getWeekDay(dateStr, "yyyyMMdd"));
								list.add(detailVo);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
			
		}
		return list;
	}

	private int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	private int getNowSeason() {
		int i = Calendar.getInstance().get(Calendar.MONTH);
		if(i<=2){
			return 1;
		}else if(i<=5){
			return 2;
		}else if(i<=8){
			return 3;
		}else{
			return 4;
		}
	}
	
	class OneHandleThread implements Runnable{
		
		private QuoteVo quote;
		
		public OneHandleThread(QuoteVo quote) {
			this.quote=quote;
		}
		
		@Override
		public void run() {
			List<QuoteTransDetailVo> list = getTransDetail(quote);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			if(list==null){
				return;
			}
			int i = quoteTransDetailMapper.bathSaveQuoteDetail(list);
			if(i==list.size()){
				System.out.println(quote.getName()+"("+quote.getCode()+")"+"历史数据保存成功.");
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		System.out.println(new QuoteDetailHandler().getNowSeason());
		String url = "http://quotes.money.163.com/trade/lsjysj_601669.html?year=2017&season=4";
		String html = HttpRequestUtil.getMethod(url, "utf-8");
		Document document = Jsoup.parse(html);
		Elements elements = document.select("table.table_bg001.border_box.limit_sale>tbody>tr");
		if(elements!=null){
//			for(Element el:elements){
//				System.out.println();
//			}
			System.out.println(elements.get(0));
		}
	}
}
