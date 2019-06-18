<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
			<h1><c:out value="${thing.name}" /></h1>
			<c:if test="${empty thing.description}">
			<p class="text-muted">No description was given.</p>
			</c:if>
			<c:if test="${not empty thing.description}">
			<p><c:out value="${thing.description}" /></p>
			</c:if>
