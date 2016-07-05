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

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Article;
import br.edu.ifrs.canoas.lds.starter.repository.UserRepository;

// TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class ArticleServiceTest {

	@Autowired
	ArticleService articleService;
	@Autowired
	UserRepository userRepository;

	/**
	 * Test to list all articles.
	 */
	@Test
	public void testToListAllArticles() {
		assertTrue(articleService.list().spliterator().estimateSize() > 0);
	}

	/**
	 * Test to get article1 and check title.
	 * 
	 * Change: 23/04/2016 - Ricardo - Change test for article id 2
	 * 
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: Was not working because the id was informed of a post and not an article
	 * 
	 */
	@Test
	public void testToGetArticle2AndCheckTitle() {
		assertThat(articleService.get(2L), hasProperty("title", is("Get With the Program")));
	}

	/**
	 * Test to create and save an article.
	 * 
	 */
	@Test 
	public void testToCreateAndSaveAnArticle() {
		Article article = new Article();
		article.setTitle("My Title");
		article.setSlug("My Slug");
		article.setPostedOn(new Date());
		article.setAuthor(userRepository.findOne(1L));
		article.setBody("My body");
		assertThat(articleService.save(article), hasProperty("id",is(not(empty()))));
	}

	/**
	 * Test to find article2 delete it and check again.
	 */
	@Test
	@Ignore
	public void testToFindArticle2DeleteItAndCheckAgain() {
		assertThat(articleService.get(2L), hasProperty("title", is("Get With the Program")));
		articleService.delete(2L);
		assertThat(articleService.get(2L), is(nullValue()));
	}
	
	/**
	 * Author: Luciane
	 * Date: 30/03/2016
	 * Description: Test to find for slug article1.
	 * 
	 * Change: 23/04/2016 - Ricardo - Change test for article id 1
	 */
	@Test
	public void testToGetSlugAndCheckTitle() {
		assertThat(articleService.get("get-program"), hasProperty("title", is("Get With the Program")));
	}
	
	/**
	 * Author: Luciane
	 * Date: 30/03/2016
	 * Description: Test to findAll articles .
	 * 
	 * Dúvida: Está correto este teste???
	 */
	@Test
	public void testToFindAllArticles() {
		assertTrue(articleService.findAll().spliterator().estimateSize() > 0);
	}

}
