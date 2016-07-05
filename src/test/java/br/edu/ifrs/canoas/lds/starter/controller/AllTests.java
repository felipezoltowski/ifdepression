/*
 * 
 */
package br.edu.ifrs.canoas.lds.starter.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.edu.ifrs.canoas.lds.starter.controller.selenium.ItemControlerSeleniumTest;
import br.edu.ifrs.canoas.lds.starter.controller.selenium.PostControlerSeleniumTest;

@RunWith(Suite.class)
@SuiteClasses({ ItemControllerTest.class
	, HomeControllerTest.class
	, ArticleControllerTest.class
	, CommentControllerTest.class
	, JobAdControllerTest.class
	, ItemControlerSeleniumTest.class
	, PostControllerTest.class
	, PostControlerSeleniumTest.class})
public class AllTests {

}

