package com.thinksys.passwordSelfService.junit;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thinksys.passwordSelfService.bean.PasswordBean;
import com.thinksys.passwordSelfService.web.LdapController;

public class LdapControllerTest {

	private Logger logger = LoggerFactory.getLogger(LdapControllerTest.class);
	
	private MockMvc mockmvc;
	private LdapController ldapController =new LdapController();

	@Before
	public void setup(){

		this.mockmvc= MockMvcBuilders .standaloneSetup(ldapController) .build();
    }
	
	@Test
	public void validate_changePassword() throws Exception{

		PasswordBean passwordBean= new PasswordBean();
		passwordBean.setNewPassword("anuj@123");
		passwordBean.setUsername("singh.anuj");
		passwordBean.setOldPassword("thinksys@123");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(passwordBean);
		
		mockmvc.perform(post("/changep").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(content().string("{\"responseCode\":200,\"responseDescription\":\"Invalid user name or old password !\"}"))
		.andReturn();
	}

	@Test
	public void validate_admin() throws Exception{

		MvcResult result = mockmvc.perform(get("/test"))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andReturn();
	}
}