package springjs.web.servlet.mvc;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springjs.domain.model.Thing;
import springjs.domain.model.ThingRepository;

@Controller
@RequestMapping("/things")
public class ThingsController extends BaseController {

	/**
	 * Regular expression in the request mapping to accept UUID as identifier.
	 */
	// @formatter:off
	private static final String ID_REGEX_UUID = "/{id:"
			+ "[0-9a-fxA-FX]{8}-[0-9a-fxA-FX]{4}-[0-9a-fxA-FX]{4}-"
			+ "[0-9a-fxA-FX]{4}-[0-9a-fxA-FX]{12}}";
	// @formatter:on

	private ThingRepository thingRepository;

	@Autowired
	public ThingsController(ThingRepository projectRepository) {
		super("things");
		this.thingRepository = projectRepository;
	}

	@Index
	String index(Pageable pageable, Model model) {
		model.addAttribute("thingsPage", thingRepository.findAll(pageable));
		return indexViewName;
	}

	@Show(path = ID_REGEX_UUID)
	String show(@PathVariable UUID id, Model model) {
		Optional<Thing> thing = thingRepository.findById(id);
		if (!thing.isPresent()) {
			throw new NotFoundException(id);
		}
		model.addAttribute("thing", thing.get());
		return showViewName;
	}

	@Create
	String create(Model model) {
		model.addAttribute("thing", new Thing());
		return createViewName;
	}

	private boolean saveOrUpdate(Thing thing, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return false;
		}
		thing = thingRepository.save(thing);
		return true;
	}

	@Save
	String save(@Valid Thing thing, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (!saveOrUpdate(thing, bindingResult)) {
			// prepareCreateForm(model);
			return getCreateViewName();
		}
		redirectAttributes.addAttribute("id", thing.getId());
		redirectAttributes.addFlashAttribute("_notice", "Thing was successfully created.");
		return redirectToIndex;
	}

	@Save(produces = "text/javascript")
	String saveViaAjax(@Valid Thing thing, BindingResult bindingResult, Model model) {
		if (!saveOrUpdate(thing, bindingResult)) {
			// prepareCreateForm(model);
			return getCreateViewName();
		}
		thing = thingRepository.save(thing);
		return showViewName;
	}

	@Edit(path = ID_REGEX_UUID)
	String edit(@PathVariable UUID id, Model model) {
		Optional<Thing> thing = thingRepository.findById(id);
		if (!thing.isPresent()) {
			throw new NotFoundException(id);
		}
		model.addAttribute("thing", thing.get());
		return editViewName;
	}

	@Update(path = ID_REGEX_UUID)
	String update(@PathVariable UUID id, @Valid Thing thing, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		thing.setId(id);
		if (!saveOrUpdate(thing, bindingResult)) {
			// prepareEditForm(model);
			return editViewName;
		}
		redirectAttributes.addFlashAttribute("_notice", "Thing was successfully updated");
		return redirectToIndex;
	}

	@Update(path = ID_REGEX_UUID, produces = "text/javascript")
	String updateViaAjax(@PathVariable UUID id, @Valid Thing thing, BindingResult bindingResult, Model model) {
		thing.setId(id);
		if (!saveOrUpdate(thing, bindingResult)) {
			// prepareEditForm(model);
			return editViewName;
		}
		return showViewName;
	}

	@GetMapping(path = ID_REGEX_UUID, params = { "delete" })
	String deleteConfirm(@PathVariable UUID id, Model model) {
		Optional<Thing> thing = thingRepository.findById(id);
		if (!thing.isPresent()) {
			throw new NotFoundException(id);
		}
		model.addAttribute("thing", thing.get());
		return deleteViewName;
	}

	@DeleteMapping(path = ID_REGEX_UUID)
	String delete(@PathVariable UUID id) {
		thingRepository.deleteById(id);
		return redirectToIndex;
	}

	@DeleteMapping(path = ID_REGEX_UUID, produces = "text/javascript")
	String deleteViaAjax(@PathVariable UUID id, Model model) {
		thingRepository.deleteById(id);
		model.addAttribute("id", id);
		return deleteViewName;
	}

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such thing")
	@SuppressWarnings("serial")
	public static class NotFoundException extends RuntimeException {
		public NotFoundException(UUID id) {
			super("Thing [" + id + "] not found");
		}
	}

}
