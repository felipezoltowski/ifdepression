package br.edu.ifrs.canoas.lds.starter.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.service.DeckService;

//TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
@WebAppConfiguration
public class DeckControllerTest extends BaseControllerTest{
	
	@Autowired 
	DeckController deckController;
	
	@Autowired
    private DeckService deckService;
	
	@Before
	public void setup() {
		mockMvc = getMockMvc(deckController);
	}

	@Test
	public void testToCreateNewDeckAndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/deck/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("deck"))
			.andExpect(model().attribute("decks", hasProperty("deckname", isEmptyOrNullString())))
			.andExpect(model().attribute("readonly",is(false)))
			.andExpect(forwardedUrl(PRE_URL+"/deck/new"+POS_URL))
			;
	}
	@Test
	public void testToViewSlugArticle1AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/article/view/get-program/"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("article"))
			.andExpect(model().attribute("article", hasProperty("title", is("Get With the Program"))))
			.andExpect(forwardedUrl(PRE_URL+"/article/view"+POS_URL))
			;
	}
	
	@Test
	@WithUserDetails("admin@123.123")
	@Ignore
	public void testToCheckArticle3DeleteItAndCheckAgain() throws Exception {
		
		assertThat(articleService.get(7L), is(notNullValue()));
		assertThat(articleService.get(7L).getTitle(), is("A Face to Remember"));
		
		this.mockMvc.perform(post("/article/delete/7"))
			.andExpect(flash().attributeExists("message"))
			;
		
		assertThat(articleService.get(7L), is(nullValue()));

	}

	@Test
	@WithUserDetails("admin@123.123")
	public void testToDeleteArticle1000ThatDoesNotExists() throws Exception {
		
		assertThat(articleService.get(1000L), is(nullValue()));
		
		this.mockMvc.perform(post("/article/delete/1000"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection())
		;

	}

	/**
	 * Change: 23/04/2016 - Ricardo - Change test for article id
	 * @throws Exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	@Ignore
	public void testToDeleteArticle2WithInvalidEmail() throws Exception {
		assertThat(articleService.get(2L), is(notNullValue()));
		assertThat(articleService.get(2L).getTitle(), is("Get With the Program"));
		this.mockMvc.perform(post("/article/delete/2"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"));	
	}
		
}
