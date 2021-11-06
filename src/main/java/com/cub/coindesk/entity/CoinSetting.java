package com.cub.coindesk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//宣告為實體(@Entity)
@Entity
//對應資料庫表名稱
@Table(name = "coin_setting")
public class CoinSetting {

	@Id
	@Column(name = "code")
    @NotEmpty
	private String code;

	@Column(name = "name")
	private String name;

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
	
	
}
