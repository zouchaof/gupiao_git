package com.gupiao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuoteTransDetailVo implements Serializable{
	
	private static final long serialVersionUID = -1783917292800481365L;
	private Integer id;
	private String code;
	private String transTime;//时间
	private BigDecimal beginPrice;//开盘价
	private BigDecimal endPrice;//收盘价
	private BigDecimal minPrice;//最低价
	private BigDecimal maxPrice;//最高价
	private Integer transCount;//交易量
	private Integer transTotal;//总交易金额（万元）
	private BigDecimal changePrice;//涨跌额
	private BigDecimal changePercent;//涨跌幅%
	private BigDecimal swing;//振幅
	private BigDecimal turnover;//换手率
	private int weekday;
	
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public BigDecimal getChangePrice() {
		return changePrice;
	}
	public void setChangePrice(BigDecimal changePrice) {
		this.changePrice = changePrice;
	}
	public BigDecimal getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(BigDecimal changePercent) {
		this.changePercent = changePercent;
	}
	public BigDecimal getSwing() {
		return swing;
	}
	public void setSwing(BigDecimal swing) {
		this.swing = swing;
	}
	public BigDecimal getTurnover() {
		return turnover;
	}
	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public BigDecimal getBeginPrice() {
		return beginPrice;
	}
	public void setBeginPrice(BigDecimal beginPrice) {
		this.beginPrice = beginPrice;
	}
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getTransCount() {
		return transCount;
	}
	public void setTransCount(Integer transCount) {
		this.transCount = transCount;
	}
	public Integer getTransTotal() {
		return transTotal;
	}
	public void setTransTotal(Integer transTotal) {
		this.transTotal = transTotal;
	}
	
}
