package springjs.web.servlet.mvc;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.http.MediaType;

public class RequestedMediaTypes {

	public static final MediaType TEXT_JAVASCRIPT;

	static {
		TEXT_JAVASCRIPT = MediaType.valueOf("text/javascript");
	}

	private final List<MediaType> mediaTypes;

	public RequestedMediaTypes(List<MediaType> mediaTypes) {
		Objects.nonNull(mediaTypes);
		this.mediaTypes = mediaTypes;
	}

	public boolean accepts(MediaType mediaType) {
		return this.mediaTypes.stream()
				.anyMatch(mediaType::isCompatibleWith);
	}

	public boolean acceptsTextHtml() {
		return accepts(MediaType.TEXT_HTML);
	}

	public boolean acceptsTextJavascript() {
		return accepts(TEXT_JAVASCRIPT);
	}

	public <T> T respondTo(
			MediaType mediaType1, Supplier<T> view1,
			Supplier<T> defaultView) {
		return respondTo(mediaType1, view1, null, null, null, null, defaultView);
	}

	public <T> T respondTo(
			MediaType mediaType1, Supplier<T> view1,
			MediaType mediaType2, Supplier<T> view2,
			Supplier<T> defaultView) {
		return respondTo(mediaType1, view1, mediaType2, view2, null, null, defaultView);
	}

	public <T> T respondTo(
			MediaType mediaType1, Supplier<T> view1,
			MediaType mediaType2, Supplier<T> view2,
			MediaType mediaType3, Supplier<T> view3,
			Supplier<T> defaultView) {
		// Starts with the most "preferred" media type
		Supplier<T> viewSupplier = null;
		for (MediaType mediaType : this.mediaTypes) {
			if (mediaType.equalsTypeAndSubtype(MediaType.ALL)) {
				continue; // skip
			}
			if (mediaType.isCompatibleWith(mediaType1)) {
				viewSupplier = view1;
			}
			else if (mediaType2 != null && mediaType.isCompatibleWith(mediaType2)) {
				viewSupplier = view2;
			}
			else if (mediaType3 != null && mediaType.isCompatibleWith(mediaType3)) {
				viewSupplier = view3;
			}
			if (viewSupplier != null) {
				return viewSupplier.get();
			}
		}
		return defaultView.get();
	}

	public <T> T respondTo(Map<MediaType, Supplier<T>> views, Supplier<T> defaultView) {
		// Starts with the most "preferred" media type
		Supplier<T> viewNameSupplier = null;
		for (MediaType mediaType : this.mediaTypes) {
			viewNameSupplier = views.keySet()
					.stream()
					.filter(mediaType::isCompatibleWith)
					.findFirst() // returns Optional<MediaType>
					.map(views::get) // returns Supplier<String>
					.orElse(null);
			if (viewNameSupplier != null) {
				return viewNameSupplier.get();
			}
		}
		return defaultView.get();
	}

}
