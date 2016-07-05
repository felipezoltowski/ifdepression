/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class ArticleRepositoryTest {

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleService articleService;
	
	/**
	 * Test to find all articles that are checked.
	 */
	@Test
	public void testToFindAllArticlesThatAreChecked() {
		assertThat(articleRepository.findChecked().size(), is(5));
	}
	
	/**
	 * @author Edward
	 * @Date:14/04/2016
	 * @Description: Test to find by slug article.
	 * 
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 * 
	 */
	@Test
	public void testToFindOnePublicArticle() {
		assertThat(articleRepository.findByPrivateArticle(false).size(), is(5));
	}
	
	
	/**
	 * @author Luciane
	 * @Date:30/03/2016
	 * @Description: Test to find by slug article.
	 * 
	 * Change: 23/04/2016 - Ricardo - Change test for article id 1
	 */
	@Test
	public void testToFindBySlugArticleAndCheckPropertyUserId(){
		assertThat(articleRepository.findBySlug("get-program"), hasProperty("title", is("Get With the Program")));
		
	}
}
