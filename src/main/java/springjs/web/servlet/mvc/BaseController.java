package springjs.web.servlet.mvc;

public abstract class BaseController {

	public static final String DEFAULT_INDEX_VIEWNAME = "index";
	public static final String DEFAULT_SHOW_VIEWNAME = "show";
	public static final String DEFAULT_CREATE_VIEWNAME = "create";
	public static final String DEFAULT_EDIT_VIEWNAME = "edit";
	public static final String DEFAULT_DELETE_VIEWNAME = "delete";

	protected final String indexViewName;
	protected final String showViewName;
	protected final String createViewName;
	protected final String editViewName;
	protected final String deleteViewName;

	protected final String redirectToIndex;
	protected final String redirectToShow;

	public BaseController(String path) {
		if (path == null || path.trim().isEmpty()) {
			throw new IllegalArgumentException("Path cannot be null or empty");
		}
		String viewBaseName = path;
		// Remove leading slash (if any)
		if (viewBaseName.startsWith("/")) {
			viewBaseName = viewBaseName.substring(1);
		}
		// Add trailing slash (if none)
		if (!viewBaseName.endsWith("/")) {
			viewBaseName = viewBaseName + "/";
		}

		this.indexViewName = viewBaseName + "index";
		this.showViewName = viewBaseName + "show";
		this.createViewName = viewBaseName + "create";
		this.editViewName = viewBaseName + "edit";
		this.deleteViewName = viewBaseName + "delete";

		String pathBase = path;
		// Remove leading slash (if any)
		if (pathBase.startsWith("/")) {
			pathBase = pathBase.substring(1);
		}
		// Remove trailing slash (if any)
		if (pathBase.endsWith("/")) {
			pathBase = pathBase.substring(0, pathBase.length() - 1);
		}
		this.redirectToIndex = "redirect:/" + pathBase;
		this.redirectToShow = "redirect:/" + pathBase + "/{id}";
	}

	/**
	 * Returns the view name for index.
	 *
	 * @return the view name for index.
	 */
	public String getIndexViewName() {
		return indexViewName;
	}

	/**
	 * Returns the view name for show.
	 *
	 * @return the view name for show.
	 */
	public String getShowViewName() {
		return showViewName;
	}

	/**
	 * Returns the view name for create.
	 *
	 * @return the view name for create.
	 */
	public String getCreateViewName() {
		return createViewName;
	}

	/**
	 * Returns the view name for edit.
	 *
	 * @return the view name for edit.
	 */
	public String getEditViewName() {
		return editViewName;
	}

	/**
	 * Returns the view name for delete.
	 *
	 * @return the view name for delete.
	 */
	public String getDeleteViewName() {
		return deleteViewName;
	}

	/**
	 * Returns the view name for index (with redirect prefix).
	 *
	 * @return the view name for index (with redirect prefix).
	 */
	public String getRedirectToIndex() {
		return redirectToIndex;
	}

	/**
	 * Returns the view name for show (with redirect prefix).
	 *
	 * @return the view name for show (with redirect prefix).
	 */
	public String getRedirectToShow() {
		return redirectToShow;
	}

}
