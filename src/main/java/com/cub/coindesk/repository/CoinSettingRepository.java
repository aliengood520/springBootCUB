package com.cub.coindesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cub.coindesk.entity.CoinSetting;

public interface CoinSettingRepository extends JpaRepository<CoinSetting, String> {

    // 使用自動化命名規則進行條件搜尋
	CoinSetting findByCode(String code);

}