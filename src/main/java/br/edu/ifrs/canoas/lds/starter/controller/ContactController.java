package br.edu.ifrs.canoas.lds.starter.controller;

import java.text.MessageFormat;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.lds.starter.domain.Contact;
import br.edu.ifrs.canoas.lds.starter.service.ContactService;
import br.edu.ifrs.canoas.lds.starter.service.NotificationService;
import br.edu.ifrs.canoas.lds.starter.service.UserProfileService;

@Controller
@RequestMapping("/contactus")
public class ContactController {
	
	@Autowired
	private NotificationService notificationService;
	
	private ContactService contactService;
	private MessageSource messageSource;

	@Autowired
	public ContactController(ContactService contactService, UserProfileService userProfileService, MessageSource messageSource){
		this.contactService = contactService;
		this.messageSource = messageSource;
	}
	/**
	 * Creates the.
	 *
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("contactus", new Contact());
		model.addAttribute("readonly", false);
		return "/contactus/form";
	}

	
	/**
	 * Send
	 * 
	 * @param deck
	 * @param bindingResult
	 * @param model
	 * @param redirectAttrs
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(@Valid Contact contact, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs,
			Locale locale) {
		if (!bindingResult.hasErrors()) {
			Contact savedContact = contactService.save(contact);
			model.addAttribute("readonly", true);
			
			int not = 0;	
			if(savedContact != null){
				try{
					notificationService.sendNotification(savedContact);
				} catch(MailException e){
					not = 1;
				}
				
				Long id = savedContact.getId();
				contactService.delete(id);
				
				if (not == 1) {
					redirectAttrs.addFlashAttribute("message2",
							MessageFormat.format(messageSource.getMessage("contactus.mail.failed", null, locale), null));
				} else {
					redirectAttrs.addFlashAttribute("message2",
							MessageFormat.format(messageSource.getMessage("contactus.mail.sent", null, locale), null));
				}
				return "redirect:/contactus/create";
			}
			model.addAttribute("readonly", false);
			return "redirect:/contactus/create";
		}
		return "redirect:/contactus/create";
	}
}
			
