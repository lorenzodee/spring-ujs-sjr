<%@ include file="/WEB-INF/tags/_tagdirectives.tagf" %>
<%@ tag
	description="Renders a link back to the referrer 
		(a JavaScript back link will be used in place of
		a referrer if none exists)."
	example="&lt;tags:link-to-back&gt;Back&lt;/tags:link-to-back&gt;"
	dynamic-attributes="_dynamicAttributes" %>
<c:set var="_href" value="${not empty header.referer ? header.referer : 'javascript:window.history.back()'}" />
<a href="${_href}"<c:forEach var="_dynattr" items="${_dynamicAttributes}"><c:out value=" " />${_dynattr.key}="${_dynattr.value}"</c:forEach>><jsp:doBody /></a>
