package br.edu.ifrs.canoas.lds.starter.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.starter.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	
}
