package br.edu.ifrs.canoas.lds.starter.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Comment;
import br.edu.ifrs.canoas.lds.starter.service.CommentService;

//TODO: Auto-generated Javadoc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
@WebAppConfiguration
public class CommentControllerTest extends BaseControllerTest{
	
	@Autowired 
	CommentController commentController;
	
	@Autowired
    private CommentService commentService;
	
	@Before
	public void setup() {
		mockMvc = getMockMvc(commentController);
	}
	

	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * Description: Test to save a comment with valid data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToSaveACommentWithValidData() throws Exception {
		
		Long size = commentService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/article/comment/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content", "some content")
                .param("article.id", "1") //You should set the null attributes that are in the HTML form
                .sessionAttr("comment", new Comment())
        )
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(view().name(containsString("redirect:/article/view/")))
	    //.andExpect(flash().attributeExists("message"))//>> Does not exists this message in CommentController method.
	    ;
		
		assertThat(commentService.list().spliterator().getExactSizeIfKnown(), is(size + 1L));

	}
	
	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * Description: Test to save a form with bad data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToSaveACommentWithBadData() throws Exception {
		
		Long size = commentService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/article/comment/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content", " ")
                .param("article.id", "s") //You should set the null attributes that are in the HTML form
                .sessionAttr("comment", new Comment())
        )
		.andDo(MockMvcResultHandlers.print())
		.andExpect(view().name(containsString("redirect:/article/view/")))
		//.andExpect(flash().attributeExists("message"))//>> Does not exists this message in CommentController method.
	    ;
		
		assertThat(commentService.list().spliterator().getExactSizeIfKnown(), is(size));

	}

	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * Description: Test to check comment1 delete it and check again.
	 *
	 * Modified by: Luciane
	 * @date: 15/05/2015
	 * @description: I changed the id because new inserts broke the tests
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToCheckComment12DeleteItAndCheckAgain() throws Exception {
		
		assertThat(commentService.get(12L), hasProperty("body", is("Body 12 - Aponta body 11")));
		commentService.delete(12L);
		assertThat(commentService.get(12L), is(nullValue()));
		
		
	}
	
	/**
	 * @author Luciane
	 * @Date: 03/04/2016
	 * Description: Test to delete comment100 that does not exists.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithUserDetails("admin@123.123")
	public void testToDeleteComment100ThatDoesNotExists() throws Exception {
		
		/*assertThat(commentService.get(100L), is(nullValue()));
		
		this.mockMvc.perform(post("/article/comment/delete/100"))
		.andExpect(view().name("/article/view"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("message", containsString("failed to delete")))
		;*/

	}
	
	/**
	 * @author :Edward Ramos
	 * @throws Exception 
	 * @Date : May/18/2016
	 * @Description: Save a comment in post.
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToSaveCommentInThePost() throws Exception{
		Long size = commentService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/post/comment/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content", "some content")
                .param("post.id", "1") 
                .sessionAttr("comment", new Comment())
        )
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(view().name(containsString("redirect:/post/view/")))
	    //.andExpect(flash().attributeExists("message"))//>> Does not exists this message in CommentController method.
	    ;
		
		assertThat(commentService.list().spliterator().getExactSizeIfKnown(), is(size + 1L));
	}
	
		
}
