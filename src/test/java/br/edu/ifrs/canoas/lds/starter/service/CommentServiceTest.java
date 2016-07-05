package br.edu.ifrs.canoas.lds.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Comment;

//TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class CommentServiceTest {
	
	@Autowired
	CommentService commentService;
	
	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * @Description: Test to list all comments.
	 */
	@Test
	public void testToListAllComments() {
		assertTrue(commentService.list().spliterator().estimateSize() > 0);
	}

	
	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * @Description: Test to create and save an comment.
	 */
	@Test
	public void testToCreateAndSaveAnComment() {
		Comment comment = new Comment();
		comment.setBody("My Comment");
		assertThat(commentService.save(comment), hasProperty("id", is(not(empty()))));
	}

	
	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * @Description: Test to get item1 and check name.
	 * 
	 *  Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 */
	@Test
	public void testToGetComment12AndCheckAuthor() {
		assertThat(commentService.get(12L), hasProperty("body", is("Body 12 - Aponta body 11")));
	}

	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * @Description: Test to find comment1 delete it and check again.
	 * Test to find item2 delete it and check again.
	 * 
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 */
	@Test
	public void testToFindComment12DeleteItAndCheckAgain() {
		assertThat(commentService.get(12L), hasProperty("body", is("Body 12 - Aponta body 11")));
		commentService.delete(12L);
		assertThat(commentService.get(12L), is(nullValue()));
	}

}
