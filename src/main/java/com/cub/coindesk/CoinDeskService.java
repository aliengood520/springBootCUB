package com.cub.coindesk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cub.coindesk.entity.CoinSetting;
import com.cub.coindesk.repository.CoinSettingRepository;
import com.cub.coindesk.vo.CoinDesk;
import com.cub.coindesk.vo.CoinDeskOrigin;
import com.cub.coindesk.vo.CoinDetail;
import com.cub.util.HttpUtil;
import com.google.gson.Gson;

@Service
public class CoinDeskService {

	@Autowired
	CoinSettingRepository coinSettingRepository;

	public CoinDesk getNewAll() throws Exception {
		String jsonString = HttpUtil.getUrlData("https://api.coindesk.com/v1/bpi/currentprice.json", "UTF8");

		CoinDesk coinDesk = new CoinDesk();
		CoinDeskOrigin data = new Gson().fromJson(jsonString, CoinDeskOrigin.class);

		coinDesk.setUpdateTime(data.getTime().getUpdatedISO());
		Map<String, CoinDetail> bpi = data.getBpi();
		for (String key : bpi.keySet()) {
			CoinDetail coinDetail = bpi.get(key);
			CoinSetting coinSetting = coinSettingRepository.findByCode(coinDetail.getCode());
			coinDetail.setName(coinSetting == null ? coinDetail.getCode() : coinSetting.getName());

			coinDesk.getCoinDetails().add(coinDetail);
		}
		return coinDesk;
	}

	public List<CoinSetting> findCoinSettingAll() {
		return coinSettingRepository.findAll();
	}

	public CoinSetting findCoinSettingByCode(String code) {
		return coinSettingRepository.findByCode(code);
	}

	public CoinSetting createCoinSetting(CoinSetting coinSetting) {
		CoinSetting coinSettingExist = coinSettingRepository.findByCode(coinSetting.getCode());
		if (coinSettingExist != null) {
			return null;
		}
		return coinSettingRepository.save(coinSetting);
	}

	public CoinSetting saveCoinSetting(CoinSetting coinSetting) {
		return coinSettingRepository.save(coinSetting);
	}

	public void deleteCoinSetting(String code) throws Exception {
		CoinSetting coinSettingExist = coinSettingRepository.findByCode(code);
		if (coinSettingExist == null) {
			throw new Exception("資料不存在!!");
		}
		coinSettingRepository.deleteById(code);
	}

}
