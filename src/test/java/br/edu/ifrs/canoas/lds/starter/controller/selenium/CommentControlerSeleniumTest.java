//package br.edu.ifrs.canoas.lds.starter.controller.selenium;
//
//import static org.fluentlenium.core.filter.FilterConstructor.withText;
//
//import java.util.concurrent.TimeUnit;
//
//import org.fluentlenium.adapter.FluentTest;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.IntegrationTest;
//import org.springframework.boot.test.SpringApplicationContextLoader;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringStarterApplication.class, loader = SpringApplicationContextLoader.class)
//@WebAppConfiguration
//@IntegrationTest
//public class CommentControlerSeleniumTest extends FluentTest {
//	/**
//	 * @author Edward Ramos
//	 * @date: May/18/2016
//	 * @description: Functional Test
//	 */
//	@Before
//	@Ignore
//	public void login() {
//		goTo("http://localhost:8080/login");
//		fill("#username").with("123@123.123");
//		fill("#password").with("123");
//		submit("#btLogin");
//		find(".dropdown-toggle", withText("General User"));
//	}
//
//	/**
//	 * @author Edward Ramos
//	 * @date: May/18/2016
//	 * @description: Navigation Test
//	 */
//	@Test
//	@Ignore
//	public void testNavigationPost() {
//		goTo("http://localhost:8080/post/list");
//		await().atMost(5, TimeUnit.SECONDS)
//				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
//		findFirst("A", withText("Collaborative development of a fictitious system in class software development laboratory.")).click();
//		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Posts")));
//		
//		//CRUD a comment
//		findFirst("A", withText("Create Comment")).click();
//		fill("#CommentAuthor").with("User test");
//		fill("#CommentBody").with("Creating a comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Edit Comment")).click();
//		fill("#CommentAuthor").with("User test 2");
//		fill("#CommentBody").with("Creating a edited comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Edit Comment")).click();
//		findFirst("A", withText("Cancel Comment")).click();
//		alert().accept();
//		findFirst("A", withText("Delete Comment")).click();
//		alert().accept();
//		
//		//Reply
//		findFirst("A", withText("Reply Comment")).click();
//		fill("#CommentAuthor").with("User test");
//		fill("#CommentBody").with("Creating a reply comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Reply Comment")).click();
//		fill("#CommentAuthor").with("User test 2");
//		fill("#CommentBody").with("Creating a edited reply comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Reply Comment")).click();
//		findFirst("A", withText("Cancel Comment")).click();
//		alert().accept();
//		findFirst("A", withText("Delete Comment")).click();
//		alert().accept();
//		}
//
//	/**
//	 * @author Edward Ramos
//	 * @date: May/18/2016
//	 * @description: Navigation Test
//	 */
//	@Test
//	@Ignore
//	public void testNavigationArticle() {
//		goTo("http://localhost:8080/article/list");
//		await().atMost(5, TimeUnit.SECONDS)
//				.until(find("#DataTables_Table_0_info", withText("Showing 1 to 6 of 6 entries")));
//		
//		//Search
//		fill("#criteria").with("a");        
//        submit(".btn btn-default");
//        await().atMost(5, TimeUnit.SECONDS);
//		findFirst("A", withText("Get With the Program")).click();
//		await().atMost(5, TimeUnit.SECONDS).until(find(".title", withText("View Article")));
//		
//		//CRUD a comment
//		findFirst("A", withText("Create Comment")).click();
//		fill("#CommentAuthor").with("User test");
//		fill("#CommentBody").with("Creating a comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Edit Comment")).click();
//		fill("#CommentAuthor").with("User test 2");
//		fill("#CommentBody").with("Creating a edited comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Edit Comment")).click();
//		findFirst("A", withText("Cancel Comment")).click();
//		alert().accept();
//		findFirst("A", withText("Delete Comment")).click();
//		alert().accept();
//		
//		//Reply
//		findFirst("A", withText("Reply Comment")).click();
//		fill("#CommentAuthor").with("User test");
//		fill("#CommentBody").with("Creating a reply comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Reply Comment")).click();
//		fill("#CommentAuthor").with("User test 2");
//		fill("#CommentBody").with("Creating a edited reply comment.");
//		findFirst("A", withText("Save Comment")).click();
//		
//		findFirst("A", withText("Reply Comment")).click();
//		findFirst("A", withText("Cancel Comment")).click();
//		alert().accept();
//		findFirst("A", withText("Delete Comment")).click();
//		alert().accept();
//		}
//}