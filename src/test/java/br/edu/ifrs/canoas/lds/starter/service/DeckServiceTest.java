/*
 * 
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

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Deck;
import br.edu.ifrs.canoas.lds.starter.repository.UserRepository;

// TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class DeckServiceTest {

	@Autowired
	DeckService deckService;
	UserProfileService userProfileService;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Test to list all Decks.
	 */
	@Test
	public void testToListAllDecks() {
		assertTrue(deckService.list().spliterator().estimateSize() > 0);
	}

	/**
	 * Test to get deck1 and check deckname.
	 */
	@Test
	public void testToGetDeck1AndCheckDeckname() {
		assertThat(deckService.get(1L), hasProperty("deckname", is("Hog Beatdown")));
	}

	/**
	 * Test to create and save a deck.
	 * 
	 */
	@Test 
	public void testToCreateAndSaveADeck() {
		Deck deck = new Deck();
		deck.setNickname("General User");
		deck.setEmail("123@123.123");
		deck.setArena(5);
		deck.setKingtowerlevel(10);
		deck.setClanname("In The Light");
		deck.setDeckname("Three Musketers");
		deck.setGuide("This deck blá blá blá...");
		deck.setStatus(false);
		deck.setDeletion(null);
		assertThat(deckService.save(deck), hasProperty("id",is(not(empty()))));
	}

	/**
	 * Test to find deck1 delete it and check again.
	 */
	@Test
	public void testToFindDeck1DeleteItAndCheckAgain() {
		assertThat(deckService.get(1L), hasProperty("deckname", is("Hog Beatdown")));
		deckService.delete(1L);
		assertThat(deckService.get(1L), is(nullValue()));
	}
	
	
	@Test
	public void testToFindAllDecks() {
		assertTrue(deckService.list().spliterator().estimateSize() > 0);
	}

}
