package com.cub.coindesk;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cub.coindesk.entity.CoinSetting;
import com.cub.coindesk.vo.ApiReturn;

@RestController
@RequestMapping("/coindesk")
public class CoinDeskController {

	@Autowired
	CoinDeskService coinDeskService;

	@RequestMapping(value = "/all", produces = "application/json; charset=utf-8")
	public Object getNewAll() {
		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		try {
			apiReturn.setData(coinDeskService.getNewAll());
		} catch (Exception ex) {

			apiReturn.setRtnCode("9");
			apiReturn.setMsg("error:" + ex.getMessage());
		}
		return apiReturn;
	}
	@RequestMapping(value = "/coinsetting/all", produces = "application/json; charset=utf-8")
	public Object findAll() {
		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		apiReturn.setData(coinDeskService.findCoinSettingAll());
		return apiReturn;
	}

	@RequestMapping(value = "/coinsetting/{code}", produces = "application/json; charset=utf-8")
	public Object read(@PathVariable String code) {
		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		apiReturn.setData(coinDeskService.findCoinSettingByCode(code));
		return apiReturn;
	}
	
	

	@RequestMapping(value = "/coinsetting",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object create(@Valid @RequestBody CoinSetting coinSetting) {
		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		CoinSetting coinSettingAfter = coinDeskService.createCoinSetting(coinSetting);
		if (coinSettingAfter == null) {
			apiReturn.setRtnCode("9");
			apiReturn.setMsg("create fail");
		}
		return apiReturn;
	}

	@RequestMapping(value = "/coinsetting",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object update(@RequestBody CoinSetting coinSetting) {

		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		CoinSetting coinSettingAfter = coinDeskService.saveCoinSetting(coinSetting);
		if (coinSettingAfter == null) {
			apiReturn.setRtnCode("9");
			apiReturn.setMsg("update fail");
		}
		return apiReturn;
	}
	

	@RequestMapping(value = "/coinsetting/{code}", method = RequestMethod.DELETE)
	 public Object delete(@PathVariable String code) {
		ApiReturn apiReturn = new ApiReturn();
		apiReturn.setRtnCode("0");
		apiReturn.setMsg("ok");
		try {
			coinDeskService.deleteCoinSetting(code);
		} catch (Exception ex) {
			apiReturn.setRtnCode("9");
			apiReturn.setMsg("error:" + ex.getMessage());
		}
		return apiReturn;
	 }	
}
