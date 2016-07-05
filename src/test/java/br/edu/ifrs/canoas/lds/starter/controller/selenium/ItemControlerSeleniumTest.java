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

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IFSkillsApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class ItemControlerSeleniumTest extends FluentTest {

	@Before
	@Ignore
	public void login() {
		goTo("http://localhost:8080/login");
		fill("#username").with("123@123.123");
		fill("#password").with("123");
		submit("#btLogin");
		find(".dropdown-toggle", withText("General User"));
	}

	
	@Test
	@Ignore
	public void testNavigation() {
		goTo("http://localhost:8080/item/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Software")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Items")));
		findFirst("A", withText("Edit")).click();
		submit("#btSubmitForm");
		findFirst("A", withText("Edit")).click();
		findFirst("A", withText("Cancel")).click();
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 6 of 6 entries");

		findFirst("A", withText("Edit")).click();
		findFirst("A", withText("Cancel")).click();

		findFirst("A", withText("Delete")).click();
		alert().accept();
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 5 of 5 entries")));
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 5 of 5 entries");

		findFirst("A", withText("New")).click();
		assertThat(find("#ItemName").isEmpty());
		submit("#btSubmitForm");
		findFirst("A", withText("Cancel")).click();
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 5 of 5 entries")));
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 5 of 5 entries");
	}

	@Ignore
	@Test
	public void testToList6Items() {
		goTo("http://localhost:8080/item/list");
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 6 of 6 entries");
	}

	
	@Test
	@Ignore
	public void testToViewAndEditItemDetailsWithNoErrors() {
		goTo("http://localhost:8080/item/list");
		await().atMost(10, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Hardware")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Items")));
		assertThat(find("#itemDate").getValue()).isEqualTo("28/04/2016");
		findFirst("A", withText("Edit")).click();
		fill("#ItemName").with("Cadeira Atualizada");
		fill("#itemDescription").with("Descricao Atualizada");
		fill("#itemDate").with("28/04/2015");
		find("#activeCb").click();
		submit("#btSubmitForm");
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Items")));

		assertThat(find(".alert-success").getText()).isEqualTo("Item saved!");
		assertThat(find("#ItemName").getValue()).isEqualTo("Cadeira Atualizada");
		assertThat(find("#itemDescription").getValue()).isEqualTo("Descricao Atualizada");
		assertThat(find("#itemDate").getValue()).isEqualTo("28/04/2015");

	}

	
	@Test
	@Ignore
	public void testToViewAndEditItemDetailsWithErrors() {
		goTo("http://localhost:8080/item/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Cadeira")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Items")));
		assertThat(find("#itemDate").getValue()).isEqualTo("28/04/2016");
		findFirst("A", withText("Edit")).click();
		fill("#ItemName").with("");
		fill("#itemDescription").with("");
		fill("#itemDate").with("");
		find("#activeCb").click();
		submit("#btSubmitForm");

		assertThat(find(".help-block").getText()).isEqualTo("Name is required.");

		fill("#ItemName").with("Cadeira Atualizada");
		submit("#btSubmitForm");
		assertThat(find(".help-block").getText()).isEqualTo("size must be between 1 and 255");

		fill("#itemDescription").with("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Praesent sed pharetra quam. Duis luctus, urna sit amet bibendum suscipit, orci "
				+ "odio varius felis, ornare cursus dolor tortor sed velit. Etiam lectus nunc, "
				+ "pellentesque varius ante vel, maximus ornare metus. Nullam cursus, eros id "
				+ "pharetra tincidunt, lacus turpis auctor tellus, ut eleifend mauris leo non tortor. "
				+ "Proin sed tristique quam. Sed ultricies, magna quis ornare venenatis, neque magna "
				+ "porta leo, id maximus quam enim quis massa. Nulla accumsan suscipit odio eu dictum. "
				+ "Integer congue ipsum sed mattis mattis.Donec feugiat diam velit, tristique pulvinar "
				+ "tortor lacinia eget. Nunc maximus lectus nec lorem feugiat tempor. In lacinia maximus "
				+ "cursus. Etiam eu congue ex. Integer at scelerisque nulla. Quisque at arcu rutrum, "
				+ "tincidunt nulla at, interdum augue. Mauris euismod, tortor at vestibulum commodo, "
				+ "libero nisi vehicula libero, tristique cursus turpis sapien quis arcu.");
		submit("#btSubmitForm");
		assertThat(find(".help-block").getText()).isEqualTo("size must be between 1 and 255");

		fill("#itemDescription").with("Descricao Atualizada");
		fill("#itemDate").with("26/04/2020");
		submit("#btSubmitForm");
		assertThat(find(".help-block").getText()).isEqualTo("must be in the past");

		fill("#itemDate").with("26/04/2010");
		submit("#btSubmitForm");

		assertThat(find(".alert-success").getText()).isEqualTo("Item saved!");
		assertThat(find("#ItemName").getValue()).isEqualTo("Cadeira Atualizada");
		assertThat(find("#itemDescription").getValue()).isEqualTo("Descricao Atualizada");
		assertThat(find("#itemDate").getValue()).isEqualTo("26/04/2010");

	}

	
	@Test
	@Ignore
	public void testToCreateAndDeleteAnItem() {
		goTo("http://localhost:8080/item/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("New")).click();
		assertThat(find("#ItemName").isEmpty());
		fill("#ItemName").with("Teste");
		assertThat(find("#ItemDescription").isEmpty());
		fill("#itemDescription").with("Teste Selenium");
		assertThat(find("#itemDate").isEmpty());
		fill("#itemDate").with("28/04/2016");
		find("#activeCb").click();
		submit("#btSubmitForm");
		assertThat(find(".alert-success").getText()).isEqualTo("Item saved!");
		assertThat(find("#ItemName").getValue()).isEqualTo("Teste");
		assertThat(find("#itemDescription").getValue()).isEqualTo("Teste Selenium");
		assertThat(find("#itemDate").getValue()).isEqualTo("28/04/2016");
		goTo("http://localhost:8080/item/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 7 of 7 entries")));
		findFirst("A", withText("Teste"));
		assertThat(find("a", withText("Teste")).getText().compareToIgnoreCase("Teste"));
		findFirst("A", withText("Delete")).click();
		alert().accept();
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 6 of 6 entries");
	}

}