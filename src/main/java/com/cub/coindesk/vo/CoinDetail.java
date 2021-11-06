package com.cub.coindesk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class CoinDetail {

	String code = "";
	String name = "";
	
    @JsonProperty("rate_float")
	@SerializedName(value = "rate_float")
	double rateFloat = 0;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(double rateFloat) {
		this.rateFloat = rateFloat;
	}


}
