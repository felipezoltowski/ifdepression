package br.edu.ifrs.canoas.lds.starter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class CommentRepositoryTest {
	
	@Autowired
	CommentRepository commentRepository;

	
	/**
	 * @author Luciane
	 * @Date:30/03/2016
	 * @Descrition: Test to findOne comment and check 
	 * property content.
	 * 
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 */
	@Test
	public void testFindOneCommentAndCheckPropertyComment() {
		assertThat(commentRepository.findOne(13L), hasProperty("body", is("Body 13 - Aponta body 11")));
	}
	
	/**
	 * @author Luciane
	 * @Date:30/03/2016
	 * @Descrition: Test to find all comments that are checked.
	 * 
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 */
	@Test
	public void testToFindAllComments() {
		assertThat(commentRepository.findAll().size(), is(18));
	}

}
