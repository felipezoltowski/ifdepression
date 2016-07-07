package br.edu.ifrs.canoas.lds.starter.controller.selenium;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.assertj.core.api.Assertions.assertThat;

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
public class DeckControllerAdminSeleniumTest extends FluentTest {
	
	@Before
	public void login() {
		goTo("http://localhost:8080/login");
		fill("#username").with("admin@123.123");
		fill("#password").with("123");
		submit("#btLogin");
		find(".a", withText("Admin User"));
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
	 * @description:  Approve a Deck test
	 *
	 **/
	@Test
	public void testApproveDeck() {
		goTo("http://localhost:8080/deck/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("DeckList")));
		findFirst("a", withText("Hog Beatdown")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".h4", withText("Deck View")));
		
		assertThat(find("#DeckDeletion").isEmpty());
		submit("#btSubmitForm");
		}
	/**
	 * @description:  Reject a Deck test
	 *
	 **/
	@Test
	public void testRejectDeck() {
		goTo("http://localhost:8080/deck/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("DeckList")));
		findFirst("a", withText("Hog Beatdown")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".h4", withText("Deck View")));
		
		assertThat(find("#DeckDeletion").isEmpty());
		fill("#DeckDeletion").with("reject");
		submit("#btSubmitForm");
		}
	/**
	 * @description:  Reject a Deck test
	 *
	 **/
	@Test
	public void testSaveDeck() {
		goTo("http://localhost:8080/deck/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("DeckList")));
		findFirst("a", withText("Hog Beatdown")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".h4", withText("Deck View")));
		findFirst("#editbutton").click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".h4", withText("Submit Deck")));
		
		assertThat(find("#DeckName").getText().equalsIgnoreCase("Hog Beatdown"));
		fill("#DeckName").with("Test name deck");
		
		assertThat(find("#DeckDeletion").isEmpty());
		fill("#DeckDeletion").with("save");
		submit("#btSubmitForm");
		}
}