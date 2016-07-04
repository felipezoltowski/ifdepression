package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.Contact;


@Service
public class NotificationService {
	
	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
	    this.javaMailSender = javaMailSender;
	}

	public SimpleMailMessage sendNotification(Contact contact) throws MailException {
	    // send email
		
	    SimpleMailMessage mail = new SimpleMailMessage();

	    if (contact != null){
		    mail.setTo("labifrs2016.1@gmail.com");
		    mail.setFrom(contact.getEmail());
		    mail.setSubject(contact.getType());
		    mail.setText("From:" + contact.getEmail() + "\n" +
		    contact.getContent() +"\n" +
		    "By: " + contact.getFullname());
		    
		    javaMailSender.send(mail);
		}
	    return mail;
	}
}
