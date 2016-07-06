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
		this.mockMvc.perform(post("/deck/create"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("deck"))
			.andExpect(model().attribute("deck", hasProperty("deckname", isEmptyOrNullString())))
			.andExpect(model().attribute("readonly",is(false)))
			.andExpect(forwardedUrl(PRE_URL+"/deck/form"+POS_URL))
			;
	}
	@Test
	public void testToViewDeckNameDeck1AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/deck/view/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("deck"))
			.andExpect(model().attribute("deck", hasProperty("deckname", is("Hog Beatdown"))))
			.andExpect(forwardedUrl(PRE_URL+"/deck/view"+POS_URL))
			;
	}
	
	@Test
	@WithUserDetails("admin@123.123")
	public void testToCheckDeck7DeleteItAndCheckAgain() throws Exception {
		
		assertThat(deckService.get(7L), is(notNullValue()));
		assertThat(deckService.get(7L).getDeckname(), is("Skull Musketere"));
		
		this.mockMvc.perform(post("/deck/delete/7"))
			.andExpect(flash().attributeExists("message"))
			;
		
		assertThat(deckService.get(7L), is(nullValue()));

	}

	@Test
	public void testToViewDeck100ThatDoesNotExists() throws Exception {
		assertThat(deckService.get(100L), is(nullValue()));
		
		this.mockMvc.perform(post("/deck/view/100"))
		.andExpect(view().name("/deck/view"))
		.andExpect(status().is2xxSuccessful())
		;		
	}
		
}
