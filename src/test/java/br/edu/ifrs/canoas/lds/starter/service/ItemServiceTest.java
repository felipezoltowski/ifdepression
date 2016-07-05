/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Item;

// TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class ItemServiceTest {

	@Autowired
	ItemService itemService;

	/**
	 * Test to list all items.
	 */
	@Test
	public void testToListAllItems() {
		assertTrue(itemService.list().spliterator().estimateSize() > 0);
	}

	/**
	 * Test to get item1 and check name.
	 */
	@Test
	public void testToGetItem1AndCheckName() {
		//assertThat(itemService.get(1L), hasProperty("subject", isEmptyString()));
	}

	/**
	 * Test to create and save an item.
	 */
	@Test
	public void testToCreateAndSaveAnItem() {
		Item item = new Item();
		item.setName("My Name");
		assertThat(itemService.save(item), hasProperty("id", is(not(empty()))));
	}

	/**
	 * Test to find item2 delete it and check again.
	 */
	@Test
	public void testToFindItem2DeleteItAndCheckAgain() {
		assertThat(itemService.get(2L), hasProperty("name", is("Caneta")));
		itemService.delete(2L);
		assertThat(itemService.get(2L), is(nullValue()));
	}

}
