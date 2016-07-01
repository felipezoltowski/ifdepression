package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.Deck;
import br.edu.ifrs.canoas.lds.starter.repository.DeckRepository;

@Service
public class DeckService {

	private DeckRepository deckRepository;

	/**
	 * Instantiates a new course service.
	 *
	 * @param repo
	 *            the repo
	 */
	@Autowired
	public DeckService(DeckRepository repo) {
		this.deckRepository = repo;
	}

	/**
	 * List.
	 *
	 * @return the iterable
	 */
	public Iterable<Deck> list() {
		return deckRepository.findAll();
	}

	/**
	 * Gets the.
	 *
	 * @param id
	 *            the id
	 * @return the course
	 */
	public Deck get(Long id) {
		return deckRepository.findOne(id);
	}

	/**
	 * Save.
	 *
	 * @param courses
	 *            the courses
	 * @return the course
	 */
	public Deck save(Deck decks) {
		return deckRepository.save(decks);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id) {
		deckRepository.delete(id);
	}

}
