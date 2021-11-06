package com.cub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cub.coindesk.entity.CoinSetting;
import com.cub.coindesk.vo.ApiReturn;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootCubApplication.class)
@WebAppConfiguration
public class CoinDeskControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	MockMvc mvc; // 創建MockMvc類的物件

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateCoinSettingError() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		JSONObject request = new JSONObject().put("name", "歐元");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/coindesk/coinsetting").headers(httpHeaders)
				.content(request.toString());
		// TODO
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println("getContentAsString="+result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		System.out.println("status="+status);
		Assert.assertEquals("錯誤", 400, status);
	}
//	@Test
	public void contextLoads() throws Exception {
		String uri = "/coindesk/all";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);
	}

//	@Test
	public void coinDetailLoads() throws Exception {
		String uri = "/coindesk/coinsetting/all";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		System.out.println("jsonString=" + jsonString);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);
		ApiReturn retsult = new Gson().fromJson(jsonString, ApiReturn.class);

		CoinSetting[] coinSettings = new Gson().fromJson(retsult.getData().toString(), CoinSetting[].class);// (CoinSetting)coinSettingList.get(0);
		List<CoinSetting> coinSettingList = Arrays.asList(coinSettings);// . (List<CoinSetting>)retsult.getData();
		if (coinSettingList.size() > 0) {
			CoinSetting coinSetting = coinSettingList.get(0);// new Gson().fromJson(tStr, CoinSetting.class);//
																// (CoinSetting)coinSettingList.get(0);
			String code = coinSetting.getCode();
			this.coinDetailOneLoads(code);
		}
	}

	@Test
	public void coinSeetingAllTest() throws Exception {
		this.testDeleteCoinSetting();
		this.testCreateCoinSetting();
		this.testQueryCoinSetting();
		this.testUpdateCoinSetting();
		this.coinDetailLoads();
		this.contextLoads();
	}

//	@Test
	public void testQueryCoinSetting() throws Exception {
		this.coinDetailOneLoads("EUR");
	}

	public void coinDetailOneLoads(String code) throws Exception {
		String uri = "/coindesk/coinsetting/" + code;
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);
	}

//	@Test
	public void testCreateCoinSetting() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		JSONObject request = new JSONObject().put("code", "EUR").put("name", "歐元");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/coindesk/coinsetting").headers(httpHeaders)
				.content(request.toString());
		// TODO
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);
	}

//	@Test
	public void testUpdateCoinSetting() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		JSONObject request = new JSONObject().put("code", "EUR").put("name", "歐元修改");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/coindesk/coinsetting").headers(httpHeaders)
				.content(request.toString());
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);

	}

//	@Test
	public void testDeleteCoinSetting() throws Exception {
		this.testDeleteCoinSetting("EUR");
	}

	public void testDeleteCoinSetting(String code) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		JSONObject request = new JSONObject();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/coindesk/coinsetting/" + code)
				.headers(httpHeaders).content(request.toString());
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		int status = result.getResponse().getStatus();
		Assert.assertEquals("錯誤", 200, status);

	}

}