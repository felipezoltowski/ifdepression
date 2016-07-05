package br.edu.ifrs.canoas.lds.starter.controller.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.filter.Filter;
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
public class PostControlerSeleniumTest extends FluentTest {
	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Before
	@Ignore
	public void login() {
		goTo("http://localhost:8080/login");
		fill("#username").with("123@123.123");
		fill("#password").with("123");
		submit("#btLogin");
		find(".dropdown-toggle", withText("General User"));
	}

	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Test
	@Ignore
	public void testNavigation() {
		goTo("http://localhost:8080/post/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Collaborative development of a fictitious system in class software development laboratory.")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Posts")));
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
		assertThat(find("#PostTitle").isEmpty());
		submit("#btSubmitForm");
		findFirst("A", withText("Cancel")).click();
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 5 of 5 entries")));
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 5 of 5 entries");
	}
	
	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Ignore
	@Test
	public void testToList6Posts() {
		goTo("http://localhost:8080/post/list");
		assertThat(find("#DataTables_Table_0_info").getText()).isEqualTo("Showing 1 to 6 of 6 entries");
	}

	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Test
	@Ignore
	public void testToViewAndEditPostDetailsWithNoErrors() {
		goTo("http://localhost:8080/post/list");
		await().atMost(10, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Body 11")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Posts")));
		assertThat(find("#PostSubject").getText()).isEqualTo("Subject11");
		findFirst("A", withText("Edit")).click();
		fill("#PostTitle").with("Title11 Atualizado");
		fill("#PostSubject").with("Subject11 Atualizado");
		fill("#postTags").with("PostTag11-1, PostTag11-2");
		//como eu faria o teste com o summernote?
		find("#PostStatus").click();
		submit("#btSubmitForm");
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Posts")));

		assertThat(find(".alert-success").getText()).isEqualTo("Post saved!");
		assertThat(find("#PostTitle").getValue()).isEqualTo("Title11 Atualizado");
		assertThat(find("#PostSubject").getValue()).isEqualTo("Subject11 Atualizado");
		assertThat(find("#postTags").getValue()).isEqualTo("PostTag11-1, PostTag11-2");

	}

	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Test
	@Ignore
	public void testToViewAndEditPostDetailsWithErrors() {
		goTo("http://localhost:8080/post/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("Collaborative development of a fictitious system in class software development laboratory.")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("Post Items")));
		assertThat(find("#PostTitle").getText()).isEqualTo("Welcome to IFSkills");
		findFirst("A", withText("Edit")).click();
		fill("#PostTitle").with("");
		fill("#postSubject").with("");
		fill("#postTags").with("");
		//colocar aqui o body
		find("#PostStatus").click();
		submit("#btSubmitForm");

		assertThat(find(".help-block").getText()).isEqualTo("Name is required.");

		fill("#postTitle").with("Post Atualizado");
		submit("#btSubmitForm");
		//este daqui acho que não tem ver como invelidar outros campos
		/*assertThat(find(".help-block").getText()).isEqualTo("size must be between 1 and 255");

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

		fill("#postTitle").with("Post Atualizado");
		fill("#itemDate").with("26/04/2020");
		submit("#btSubmitForm");
		assertThat(find(".help-block").getText()).isEqualTo("must be in the past");

		fill("#itemDate").with("26/04/2010");*/
		submit("#btSubmitForm");

		//revisar estas prorpiedades
		assertThat(find(".alert-success").getText()).isEqualTo("Post saved!");
		assertThat(find("#PostTitle").getValue()).isEqualTo("Post Atualizado");
		assertThat(find("#PostSubject").getValue()).isEqualTo("Subject Atualizado");
		//assertThat(find("#posTags").getValue()).isEqualTo("Tags Atualizadas");
		

	}

	/**
	 * @author Luciane
	 * @date:15/05/016
	 * @description: Functional Test
	 */
	@Test
	@Ignore
	public void testToCreateAndDeleteAnPost() {
		goTo("http://localhost:8080/post/list");
		await().atMost(5, TimeUnit.SECONDS)
				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
		findFirst("A", withText("New")).click();
		assertThat(find("#PostTitle").isEmpty());
		fill("#PostTitle").with("Teste");
		assertThat(find("#PostSubject").isEmpty());
		fill("#PostSubject").with("Teste Selenium");
		assertThat(find("#postTags").isEmpty());
		fill("#postTags").with("Tags, Teste");
		//colocar aqui o body
		find("#PostStatus").click();
		submit("#btSubmitForm");
		assertThat(find(".alert-success").getText()).isEqualTo("Post saved!");
		assertThat(find("#PostTitle").getValue()).isEqualTo("Teste");
		assertThat(find("#PostSubject").getValue()).isEqualTo("Teste Selenium");
		assertThat(find("#postTags").getText()).isEqualTo("Tags, Teste");
		//colocar body
		goTo("http://localhost:8080/post/list");
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
	/**
	 * @author Felipe
	 * @date 18/05/2016
	 * @description Functional Test
	 */
	@Test
	@Ignore
	public void testToViewOnePostFromHomePage(){
		goTo("http://localhost:8080");
		findFirst("h2", withText("WelcomeToIFSkills"));
		assertThat(findFirst("h2", withText("WelcomeToIFSkills")).getText().compareToIgnoreCase("WelcomeToIFSkills"));
		findFirst("a", withText("read more..")).click();
		assertThat(findFirst("a", withText("read more..")).getText().compareToIgnoreCase("read more.."));
		assertThat(find("#PostTitle", withText("WelcomeToIFSkills")).getText().equalsIgnoreCase("WelcomeToIFSkills"));
		assertThat(find("#PostSubject", withText("Informática TI")).getText().equalsIgnoreCase("Informática TI"));
	}


}