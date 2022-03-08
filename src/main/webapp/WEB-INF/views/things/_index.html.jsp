<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/html; charset=utf-8" %>
	<div id="things">
		<c:if test="${empty thingsPage.content}">
		<p>There are no things yet.</p>
		</c:if>
		<c:if test="${not empty thingsPage.content}">
		<table class="table">
			<tr>
				<th>Name</th>
				<th><span class="sr-only">Show</span></th>
				<th><span class="sr-only">Edit</span></th>
				<th><span class="sr-only">Remove</span></th>
			</tr>
			<c:forEach var="thing" items="${thingsPage.content}">
			<tr id="thing_${thing.id}">
				<td>
					<c:out value="${thing.name}"/>
				</td>
				<td>
					<a href="<c:url value='/things/${thing.id}' />">Show</a>
				</td>
				<td>
					<a href="<c:url value='/things/${thing.id}?edit' />" data-remote="true">Edit</a>
				</td>
				<td>
					<a href="<c:url value='/things/${thing.id}?delete' />" data-remote="true" data-method="delete" data-confirm="Are you sure?">Remove</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
