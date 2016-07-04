package br.edu.ifrs.canoas.lds.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.starter.domain.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long>{
	
	List<Deck> findAll();
	
	List<Deck> findByStatus(boolean b);
	
}
