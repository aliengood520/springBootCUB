package com.cub.coindesk.vo;

import java.util.Map;

public class CoinDeskOrigin {

	String disclaimer = "";
	String chartName = "";
	CoinDeskTime time = new CoinDeskTime();
	Map<String, CoinDetail> bpi;

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public CoinDeskTime getTime() {
		return time;
	}

	public void setTime(CoinDeskTime time) {
		this.time = time;
	}

	public Map<String, CoinDetail> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, CoinDetail> bpi) {
		this.bpi = bpi;
	}

}
