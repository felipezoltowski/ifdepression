/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * Test to find all items that are checked.
	 */
	@Test
	public void testToFindAllItemsThatAreChecked() {
		assertThat(itemRepository.findChecked().isEmpty(), is(false));
	}

}
