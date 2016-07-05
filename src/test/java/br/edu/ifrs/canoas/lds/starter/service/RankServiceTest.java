package br.edu.ifrs.canoas.lds.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Document;
import br.edu.ifrs.canoas.lds.starter.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class RankServiceTest {
/**
 * @author Felipe
 * @date 18/05/2016
 */
	@Autowired
	DocumentService docService;
	PostService postService;
	
	@Test
	public void testToCheckThePostRank() {
		Document document = new Post();
		document.setId(1L);
		docService.findOne(document);
	}
		
	
}
