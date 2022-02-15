package springjs.web.servlet.mvc;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springjs.domain.model.Thing;
import springjs.domain.model.ThingRepository;

@Controller
@RequestMapping("/things")
class ThingsController {

	/**
	 * Regular expression in the request mapping to accept UUID as identifier.
	 */
	// @formatter:off
	private static final String ID_REGEX_UUID = "/{id:"
			+ "[0-9a-f]{8}-(?:[0-9a-f]{4}-){3}[0-9a-f]{12}}";
	// @formatter:on

	@Autowired
	private ThingRepository thingRepository;
//	@Autowired
//	private MessageSource messageSource;

	@GetMapping
	String index(Pageable pageable, Model model) {
		model.addAttribute("thingsPage", thingRepository.findAll(pageable));
		return "things/index";
	}

	@GetMapping(path = ID_REGEX_UUID)
	String show(@PathVariable UUID id, Model model) {
		Thing thing = thingRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("thing", thing);
		return "things/show";
	}

	@GetMapping(params = "create")
	String create(Model model) {
		model.addAttribute("thing", new Thing());
		prepareFormModels(model);
		return "things/create";
	}

	@PostMapping
	String save(@Valid Thing thing, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			prepareFormModels(model);
			return "things/create";
		}
		thing = thingRepository.save(thing);
		// @formatter:off
		/*
		redirectAttributes.addFlashAttribute("message",
				this.messageSource.getMessage("thing.created",
						new Object[] { thing.getId() }, locale));
		*/
		// @formatter:on
		return "redirect:/things";
	}

	@GetMapping(path = ID_REGEX_UUID, params = "edit")
	String edit(@PathVariable UUID id, Model model) {
		Thing thing = thingRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("thing", thing);
		prepareFormModels(model);
		return "things/edit";
	}

	@PutMapping(path = ID_REGEX_UUID)
	String update(@PathVariable UUID id, @Valid Thing thing, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		thing.setId(id);
		if (bindingResult.hasErrors()) {
			prepareFormModels(model);
			return "things/edit";
		}
		thing = thingRepository.save(thing);
		// @formatter:off
		/*
		redirectAttributes.addFlashAttribute("message",
				this.messageSource.getMessage("thing.updated",
						new Object[] { thing.getId() }, locale));
		*/
		// @formatter:on
		return "redirect:/things";
	}

	@DeleteMapping(path = ID_REGEX_UUID)
	String delete(@PathVariable UUID id) {
		try {
			this.thingRepository.deleteById(id);
			return "redirect:/things";
		}
		catch (EmptyResultDataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	void prepareFormModels(Model model) {
		// Provide models to support the create/edit <form> (e.g. drop lists)
	}

}
