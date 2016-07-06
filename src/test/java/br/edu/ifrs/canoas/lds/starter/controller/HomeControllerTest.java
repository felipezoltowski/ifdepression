package br.edu.ifrs.canoas.lds.starter.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
}
