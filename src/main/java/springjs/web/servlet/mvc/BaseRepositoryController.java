package springjs.web.servlet.mvc;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class BaseRepositoryController<T, ID> extends BaseController {

	// TODO Separate domain entity type and UI-specific form type

	protected final PagingAndSortingRepository<T, ID> repository;

	public BaseRepositoryController(String path,
			PagingAndSortingRepository<T, ID> repository) {
		super(path);
		this.repository = repository;
	}

	/**
	 * This method simply returns the {@link BaseController#indexViewName} field.
	 * This method is intended to be overridden and annotated to be a request
	 * handler (e.g. annotated with {@link Index}).
	 * 
	 * @param pageable
	 *            the pageable object
	 * @param model
	 *            to contain model attributes rendered by the view
	 * @return the view name for index
	 */
	String index(Pageable pageable, Model model) {
		// TODO Use a separate query service
		// The simplest implementation of query service is to call
		// PagingAndSortingRepository#findAll(Pageable).
		return indexViewName;
	}

	/**
	 * This method simply returns the {@link BaseController#showViewName} field.
	 * This method is intended to be overridden and annotated to be a request
	 * handler (e.g. annotated with {@link Show}).
	 * 
	 * @param id
	 *            the unique identifier
	 * @param model
	 *            to contain model attributes rendered by the view
	 * @return
	 */
	String show(@PathVariable ID id, Model model) {
		// TODO Use a separate query service
		// The simplest implementation of query service is to call
		// PagingAndSortingRepository#findById(ID).
		Optional<T> object = repository.findById(id);
		if (!object.isPresent()) {
			// TODO Throw exception that would be respond with a 404
		}
		return showViewName;
	}

	String create(Model model) {
		// prepareCreateForm(model);
		return createViewName;
	}

	String save(@Valid T form, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			// prepareCreateForm(model);
			return createViewName;
		}
		// TODO Allow UI-specific form type to adapt to domain entity type
		// repository.save(form.toDomainEntity());
		return redirectToShow;
	}

	String edit(@PathVariable ID id, Model model) {
		// TODO Use a separate query service
		// The simplest implementation of query service is to call
		// PagingAndSortingRepository#findById(ID).
		Optional<T> object = repository.findById(id);
		if (!object.isPresent()) {
			// TODO Throw exception that would be respond with a 404
		}
		// prepareEditForm(model);
		return editViewName;
	}

	String update(@PathVariable ID id, @Valid T form, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			// prepareEditForm(model);
			return editViewName;
		}
		repository.save(form);
		return redirectToShow;
	}

	// deleteConfirm
	// delete

}
