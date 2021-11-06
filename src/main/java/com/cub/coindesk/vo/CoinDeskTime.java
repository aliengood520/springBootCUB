package com.cub.coindesk.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CoinDeskTime {
	private String updated = "";
	
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedISO;
//	private String updatedISO = "";
	private String updateduk = "";
	
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Date getUpdatedISO() {
		return updatedISO;
	}
	public void setUpdatedISO(Date updatedISO) {
		this.updatedISO = updatedISO;
	}
	public String getUpdateduk() {
		return updateduk;
	}
	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}
	
	
}
