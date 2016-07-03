package br.edu.ifrs.canoas.lds.starter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.starter.domain.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long>{

	List<Deck> findByStatus(boolean b);
	
}
