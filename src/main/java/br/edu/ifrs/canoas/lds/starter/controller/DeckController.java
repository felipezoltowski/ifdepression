package br.edu.ifrs.canoas.lds.starter.controller;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.lds.starter.domain.Deck;
import br.edu.ifrs.canoas.lds.starter.domain.Role;
import br.edu.ifrs.canoas.lds.starter.domain.User;
import br.edu.ifrs.canoas.lds.starter.service.DeckService;
import br.edu.ifrs.canoas.lds.starter.service.UserDetailsImpl;
import br.edu.ifrs.canoas.lds.starter.service.UserProfileService;

@Controller
@RequestMapping("/deck")
public class DeckController {
	
	private DeckService deckService;
	private UserProfileService userProfileService;
	private MessageSource messageSource;

	@Autowired
	public DeckController(DeckService deckService, UserProfileService userProfileService, MessageSource messageSource){
		this.deckService = deckService;
		this.userProfileService = userProfileService;
		this.messageSource = messageSource;
	}
	
	/**
	 * List Method.
	 */
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("decks", deckService.listApprovedDecks());
		return "/deck/list";
	}
	
	@RequestMapping("/listadmin")
	public String listadmin(Model model){
	    model.addAttribute("decks", deckService.list());
        return "/deck/list";
	}
	
	/**
	 * Delete.
	 *
	 * @return the string
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs, Locale locale) {
		Deck deck = deckService.get(id);
		deckService.delete(id);

		redirectAttrs.addFlashAttribute("message",
				MessageFormat.format(messageSource.getMessage("deck.deleted", null, locale), deck.getDeckname()));

		return "redirect:/deck/list";
	}
	/**
	 * Edits the.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{id}")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("deck", deckService.get(id));
		model.addAttribute("readonly", false);
		return "/deck/form";
	}
	/**
	 * Creates the.
	 *
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("deck", new Deck());
		model.addAttribute("readonly", false);
		return "/deck/form";
	}
	
	/**
	 * View.
	 *
	 */
	@RequestMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("deck", deckService.get(id));
		model.addAttribute("readonly", true);
		return "/deck/form";
	}
	
	/**
	 * Save
	 * 
	 * @param deck
	 * @param bindingResult
	 * @param model
	 * @param redirectAttrs
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Deck deck, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs,
			Locale locale) {
		if (!bindingResult.hasErrors()) {
			deck.setStatus(false);
			deck.setDeletion(null);
			Deck savedDeck = deckService.save(deck);
			model.addAttribute("readonly", true);
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("deck.saved", null, locale));
			return "redirect:/deck/view/" + savedDeck.getId() + "?success";
		}
		model.addAttribute("readonly", false);
		return "/deck/form";
	}

	/**
	 * 
	 * Evaluate Method 
	 * 
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/evaluate",  method = RequestMethod.POST)
	public String evaluate(Deck deck, Model model, RedirectAttributes redirectAttrs,
			Locale locale) {
				
		if (deck.getDeletion().equalsIgnoreCase("reject")) {
			deck.setStatus(false);
			deckService.delete(deck.getId());
			return "redirect:/deck/listadmin";
			
		} else {
			deck.setStatus(true);
		}
		
		try {
			//save the changes
            Deck savedDeck = deckService.save(deck);
            model.addAttribute("readonly", true);
			
			redirectAttrs.addFlashAttribute("message", 
					MessageFormat.format(messageSource.getMessage("deck.evaluation.success", null, locale), null));

	        return "redirect:/deck/view/" + savedDeck.getId() + "?success";
				
			} catch (Exception e) {
				redirectAttrs.addFlashAttribute("message5",
						MessageFormat.format(messageSource.getMessage("deck.evaluation.failed", null, locale), null));	
				model.addAttribute("readonly", false);
								
				return "/deck/form";
			}
		
	}
}
