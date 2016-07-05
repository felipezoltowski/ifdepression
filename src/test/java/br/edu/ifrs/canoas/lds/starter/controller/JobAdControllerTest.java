package br.edu.ifrs.canoas.lds.starter.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.service.JobAdService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class JobAdControllerTest extends BaseControllerTest{
	
	@Autowired 
	JobAdController jobAdController;
	
	@Autowired
    private JobAdService jobAdService;
	
	@Before
	public void setup() {
		mockMvc = getMockMvc(jobAdController);
	}
	

	/**
	 * @author Luciane
	 * @Date: 18/04/2016
	 * Description: Test to check Job3, delete it and check again.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToCheckJobAd7DeleteItAndCheckAgain() throws Exception {
		
		assertThat(jobAdService.get(7L), is(notNullValue()));
		assertThat(jobAdService.get(7L).getBusinessArea(), is("IT"));
		
		this.mockMvc.perform(post("/job/delete/7"))
			.andExpect(flash().attributeExists("message"))
			;
		
		assertThat(jobAdService.get(7L), is(nullValue()));

	}

	/**
	 * @author Luciane
	 * @Date: 18/04/2016
	 * Description: Test to delete JobAd1000 that does not exists.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToDeleteJobAd1000ThatDoesNotExists() throws Exception {
		
		assertThat(jobAdService.get(1000L), is(nullValue()));
		
		this.mockMvc.perform(post("/job/delete/1000"))
		.andExpect(view().name("redirect:/job/list"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;

	}

	
	/**
	 * @author Luciane
	 * @Date: 18/04/2016
	 * Description: Test to delete JobAd2 with invalid email.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToDeleteJobAd2WithInvalidEmail() throws Exception {
		assertThat(jobAdService.get(2L), is(notNullValue()));
		assertThat(jobAdService.get(2L).getBenefits(), is("Meal ticket and Health Insurance"));
		this.mockMvc.perform(post("/job/delete/2"))
		.andExpect(view().name("redirect:/job/list"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"));	
	}

	/**
	 * @author Luciane
	 * @Date: 18/04/2016
	 * Description: Test to view jobAD1 and check atts.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToViewJobAd1AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/job/view/1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("job"))
		.andExpect(model().attribute("job", hasProperty("title", is("Vaga TI"))))
		.andExpect(model().attribute("readonly",is(true)))
		.andExpect(forwardedUrl(PRE_URL+"/job/form"+POS_URL))
		;

		
	}
	
	/**
	 * @author Luciane
	 * @Date: 26/04/2016
	 * Description: Test to view jobAd with id invalid =100.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToViewJobAd100ThatDoesNotExists() throws Exception {
		assertThat(jobAdService.get(100L), is(nullValue()));
		
		this.mockMvc.perform(post("/job/view/100"))
		.andExpect(view().name("redirect:/job/list"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;		
	}
	
	/**
	 * @author Luciane
	 * @Date: 25/04/2016
	 * Description: Test method search
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testShowJobAdListAndCheckAtts() throws Exception{
		this.mockMvc.perform(get("/job/search?criteria=Luciane"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("jobs"))
		.andExpect(model().attribute("jobs", hasSize(1)))
		.andExpect(model().attribute("jobs", hasItem(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("Vaga TI")),
                        hasProperty("benefits", is("Vale-refeição e Vale transporte"))
                )
        )))
		.andExpect(forwardedUrl(PRE_URL+"/job/list"+POS_URL))
		;
		
	}

}
