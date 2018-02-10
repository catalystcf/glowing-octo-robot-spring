package com.aaroshka.atopical.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)	
@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getLinks( ) throws Exception {
		   mvc.perform( MockMvcRequestBuilders.get("/links").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string( containsString("linkId")));
	}

	/**
	 * Check that the Root is delivering the default value and not an error page
	 * @throws Exception
	 */
	@Test
	public void getRoot( ) throws Exception {
		   MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andReturn();
		   MockHttpServletResponse response = mvcResult.getResponse();
		   
		   assertEquals(200, response.getStatus() );
		   assertEquals( "Hello World!", response.getContentAsString() );	   
	}

}
