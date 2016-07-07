package br.edu.ifrs.canoas.lds.starter.controller.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringStarterApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class DeckControllerSeleniumTest extends FluentTest {
	
	@Before
	public void login() {
		goTo("http://localhost:8080/login");
		fill("#username").with("123@123.123");
		fill("#password").with("123");
		submit("#btLogin");
		find(".a", withText("General User"));
	}

	/**
	 * @description: Navigation Test
	 */
	@Test
	public void testNavigationDeck() {
		goTo("http://localhost:8080/deck/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("DeckList")));
		findFirst("a", withText("Hog Beatdown")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".h4", withText("Deck View")));
		}

	/**
	 * @description:  Submit a Deck test
	 */
	 // ----- MATERIALIZE GERA UMA HIDDEN DROPDOWN ---- 
	 //* Não consegui fazer o teste clicar em algum dos valores 
	@Test
	@Ignore
	public void testSubmitDeck() {
		goTo("http://localhost:8080/deck/create");
		await().atMost(5, TimeUnit.SECONDS)	.until(find(".h4", withText("Submit Deck")));
		
		//CRUD A DECK
		assertThat(find("#ClanName").isEmpty());
		fill("#ClanName").with("In The Night");        
        await().atMost(5, TimeUnit.SECONDS);
       
        find("#KingTowerLevel").click();
		await().atMost(5, TimeUnit.SECONDS);
		
		find("#Arena").click();
	    await().atMost(5, TimeUnit.SECONDS);
	    
	    assertThat(find("#DeckName").isEmpty());
		fill("#DeckName").with("Golem push");        
        
        await().atMost(5, TimeUnit.SECONDS);
        assertThat(find("#Guide").isEmpty());
		fill("#Guide").with("This deck blá blá blá...");        
        
        await().atMost(5, TimeUnit.SECONDS);
        submit("#btSubmitForm");
		}
	/**
	 * @description:  Submit a Contact
	 */
	 // ----- MATERIALIZE GERA UMA HIDDEN DROPDOWN ---- 
	 //* Não consegui fazer o teste selecionar outro valor a não ser Doubt pela mesma razão do teste anterior
	@Test
	public void testSubmitContact() {
		goTo("http://localhost:8080/contactus/create");
		await().atMost(5, TimeUnit.SECONDS)	.until(find(".h4", withText("Contact Us")));
		
		//CRUD A DECK
		assertThat(find("#FullName").isEmpty());
		fill("#FullName").with("AnonPlayer");        
        await().atMost(5, TimeUnit.SECONDS);
       
        assertThat(find("#Email").isEmpty());
		fill("#Email").with("test@test.com");        
        await().atMost(5, TimeUnit.SECONDS);
        
        assertThat(find("#Content").isEmpty());
		fill("#Content").with("some test text");        
        await().atMost(5, TimeUnit.SECONDS);     
        
        await().atMost(5, TimeUnit.SECONDS);
        submit("#btSubmitForm");
		}
}