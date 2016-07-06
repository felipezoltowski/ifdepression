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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class NotificationServiceTest {
	
	@Autowired
	NotificationService notificationService;
	
	@Test(expected=MailSendException.class)
	public void tryToSendANullMessage(){
		notificationService.sendNotification(new Contact());
	}
	


}
