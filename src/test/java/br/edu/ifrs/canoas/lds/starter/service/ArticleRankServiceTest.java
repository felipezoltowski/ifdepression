package br.edu.ifrs.canoas.lds.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class ArticleRankServiceTest {

	@Autowired
	ArticleRankService articleRankService;
	
	@Autowired
	ArticleService articleService;
	
	@Test
	public void testToCheckTheArticleRank() {
		assertThat(articleRankService.getRank(articleService.get(1L)), is(new Float(3)));
	}

}
