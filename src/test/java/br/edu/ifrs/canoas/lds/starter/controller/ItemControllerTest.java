/*
 * 
 */
package br.edu.ifrs.canoas.lds.starter.controller;



import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Item;
import br.edu.ifrs.canoas.lds.starter.service.ItemService;

// TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
@WebAppConfiguration
public class ItemControllerTest extends BaseControllerTest {

	@Autowired 
	ItemController itemController;
	
	@Autowired
    private ItemService itemService;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		mockMvc = getMockMvc(itemController);
	}

	/**
	 * Test to list all items and check attributes.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToListAllItemsAndCheckAttributes() throws Exception {
		this.mockMvc.perform(post("/item/list"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("items"))
			.andExpect(model().attribute("items", hasSize(6)))
			.andExpect(model().attribute("items", hasItem(
                    allOf(
                            hasProperty("id", is(1L)),
                            hasProperty("name", is("Celular")),
                            hasProperty("description", is("Dispositivo de comunicação"))
                    )
            )))
			.andExpect(forwardedUrl(PRE_URL+"/item/list"+POS_URL))
			;
	}
	
	/**
	 * Test to create a new item and check atts.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToCreateANewItemAndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/item/create"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("item"))
			.andExpect(model().attribute("item", hasProperty("name", isEmptyOrNullString())))
			.andExpect(model().attribute("readonly",is(false)))
			.andExpect(forwardedUrl(PRE_URL+"/item/form"+POS_URL))
			;
	}
	
	/**
	 * Test to view item1 and check atts.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToViewItem1AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/item/view/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("item"))
			.andExpect(model().attribute("item", hasProperty("name", is("Celular"))))
			.andExpect(model().attribute("readonly",is(true)))
			.andExpect(forwardedUrl(PRE_URL+"/item/form"+POS_URL))
			;
	}
	
	/**
	 * Test to update item1 and check atts.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToUpdateItem1AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/item/edit/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("item"))
			.andExpect(model().attribute("item", hasProperty("name", is("Celular"))))
			.andExpect(model().attribute("readonly",is(false)))
			.andExpect(forwardedUrl(PRE_URL+"/item/form"+POS_URL))
			;
	}
	
	/**
	 * Test to check item2 delete it and check again.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToCheckItem2DeleteItAndCheckAgain() throws Exception {
		
		assertThat(itemService.get(2L).getName(), is("Caneta"));
		
		this.mockMvc.perform(post("/item/delete/2"))
			.andExpect(flash().attributeExists("message"))
			;
		
		assertThat(itemService.get(2L), is(nullValue()));

	}
	
	/**
	 * Test to delete item100 that does not exists.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToDeleteItem100ThatDoesNotExists() throws Exception {
		
		assertThat(itemService.get(100L), is(nullValue()));
		
		this.mockMvc.perform(post("/item/delete/100"))
		.andExpect(view().name("/item/form"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("message", containsString("failed to delete")))
		;

	}
	
	/**
	 * Test to save a form with valid data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToSaveAFormWithValidData() throws Exception {
		
		Long size = itemService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/item/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "some name")
                .param("description", "some description")
                .sessionAttr("item", new Item())
        )
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(view().name(containsString("redirect:/item/view/")))
	    .andExpect(flash().attributeExists("message"))
	    ;
		
		assertThat(itemService.list().spliterator().getExactSizeIfKnown(), is(size + 1L));

	}
	
	/**
	 * Test to save a form with bad data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToSaveAFormWithBadData() throws Exception {
		
		Long size = itemService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/item/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("someParam", "some param")
                .sessionAttr("item", new Item())
        )
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(status().isOk())
	    .andExpect(view().name("/item/form"))
	    .andExpect(model().attribute("readonly", is(false)))
	    ;
		
		assertThat(itemService.list().spliterator().getExactSizeIfKnown(), is(size));

	}

}
