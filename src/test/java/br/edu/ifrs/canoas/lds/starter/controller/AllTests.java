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
@SuiteClasses({ HomeControllerTest.class
	, DeckControllerTest.class})
public class AllTests {

}

