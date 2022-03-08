package springjs.web.servlet.mvc;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestedMediaTypesMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private final ContentNegotiationManager contentNegotiationManager;

	public RequestedMediaTypesMethodArgumentResolver() {
		this(new ContentNegotiationManager(new HeaderContentNegotiationStrategy()));
	}

	public RequestedMediaTypesMethodArgumentResolver(
			ContentNegotiationManager contentNegotiationManager) {
		Objects.nonNull(contentNegotiationManager);
		this.contentNegotiationManager = contentNegotiationManager;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return RequestedMediaTypes.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return new RequestedMediaTypes(
				this.contentNegotiationManager.resolveMediaTypes(webRequest));
	}

}
