package com.cub.coindesk.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinDesk {

    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date updateTime;
	List<CoinDetail> coinDetails = new ArrayList<CoinDetail>();



	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<CoinDetail> getCoinDetails() {
		return coinDetails;
	}

	public void setCoinDetails(List<CoinDetail> coinDetails) {
		this.coinDetails = coinDetails;
	}

}
