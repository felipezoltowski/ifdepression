/**
 * @author: Aline G
 * @date: 06/04/2016
 * @description: Test of sendNotification()
 */
package br.edu.ifrs.canoas.lds.starter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class NotificationServiceTest {
	
	@Autowired
	NotificationService notificationService;


	/*@Test
	public void testSendNotification() {
		Article article = new Article();
		article.setAuthor(userRepository.findOne(9L));
		article.setTitle("My Title");
		article.setTeaser("This is the article's teaser.");
		article.setBody("This is the article's body!!!");
		x
		/**
		 * This test result in a error: "is(notNullValue)".
		 */		
		/*assertThat(notificationService.sendNotification(article), is(notNullValue()));
		assertThat(notificationService.sendNotification(article), hasProperty("to",is(not(empty()))));
		
	}*/
	
	
	/**
	 * @author Luciane
	 * Date: 19/04/2016
	 * Description: Test method SendMessage
	 * if if the email is not sent it expects the exception
	 * MailSendException
	 */
	@Test(expected=MailSendException.class)
	public void tryToSendANullMessage(){
		notificationService.sendNotification(new SimpleMailMessage());
	}
	


}
