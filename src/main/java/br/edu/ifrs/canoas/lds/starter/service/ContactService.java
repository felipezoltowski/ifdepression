package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.Contact;
import br.edu.ifrs.canoas.lds.starter.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository contactRepository;

	/**
	 * Instantiates a new contact service.
	 *
	 * @param repo
	 *            the repo
	 */
	@Autowired
	public ContactService(ContactRepository repo) {
		this.contactRepository = repo;
	}
	
	/**
	 * Gets the.
	 *
	 * @param id
	 *            the id
	 * @return the article
	 */
	public Contact get(Long id) {
		return contactRepository.findOne(id);
	}
	
	/**
	 * Save.
	 *
	 * @param courses
	 *            the courses
	 * @return the course
	 */
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	public void delete(Long id) {
		contactRepository.delete(id);	
	}

}
