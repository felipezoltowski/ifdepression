package br.edu.ifrs.canoas.lds.starter.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
@WebAppConfiguration
public class HomeControllerTest extends BaseControllerTest{
	
	@Autowired 
	HomeController homeController;
	
	@Before
	public void setup() {
		mockMvc = getMockMvc(homeController);
	}
	
	/** Author:Luciane
	 *  Date: 24/03/2016
	 *  Description: view test method
	 *  Shows a list of articles on page index.html
	 *  
	 *  Change: 23/04/2016 - Ricardo - Change test for article id 1
	 * */
			
	@Test
	@WithUserDetails("admin@123.123")
	public void testToViewArticles() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("articles", hasSize(5)))
			.andExpect(model().attribute("articles", hasItem(
		            allOf(
		                    hasProperty("title", is("Get With the Program")),
		                    hasProperty("teaser", is("DIY tips for adding coding to your analysis arsenal"))
		                   
		            )
		    )))
			.andExpect(forwardedUrl(PRE_URL+"/index"+POS_URL))
			;
	}

}
