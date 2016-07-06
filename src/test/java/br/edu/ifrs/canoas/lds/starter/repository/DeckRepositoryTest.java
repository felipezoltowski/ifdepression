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

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.service.DeckService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class DeckRepositoryTest {

	@Autowired
	DeckRepository deckRepository;
	
	@Autowired
	DeckService deckService;
	/**
	 * Test to find all decks.
	 */
	@Test
	public void testToFindAllDecks() {
		assertThat(deckRepository.findAll().size(), is(8));
	}	
	/**
	 * Test to find all decks that are waiting for approval.
	 */
	@Test
	public void testToFindAllDecksThatWerenotApproved() {
		assertThat(deckRepository.findByStatus(false).size(), is(3));
	}
	
	/**
	 * Test to find all decks that are approved.
	 */
	@Test
	public void testToFindAllDecksThatWereApproved() {
		assertThat(deckRepository.findByStatus(true).size(), is(5));
	}
	
}
