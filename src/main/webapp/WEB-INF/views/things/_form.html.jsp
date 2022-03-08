<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<c:set var="action" value="/things${not empty thing.id ? '/'.concat(thing.id) : '' }" />
<c:set var="method" value="${not empty thing.id ? 'put' : 'post' }" />
<c:set var="submit" value="${not empty thing.id ? 'Update' : 'Create' }" />
<c:set var="submit" value="${not empty thing.id ? 'Update' : 'Create' }" />
<c:set var="disableWith" value="${not empty thing.id ? 'Updating...' : 'Creating...' }" />
			<form:form id="thing-form" modelAttribute="thing" action="${action}" method="${method}" data-remote="${param.remote}">
				<div class="form-group">
					<label for="name">Name</label>
					<form:input path="name" cssClass="form-control${status.error ? ' is-invalid' : ''}" maxlength="50" placeholder="e.g. Marketing Campaign" />
					<form:errors cssClass="invalid-feedback" path="name" />
				</div>
				<div class="form-group">
					<label for="description">Description (optional)</label>
					<form:input path="description" cssClass="form-control${status.error ? ' is-invalid' : ''}" maxlength="140" />
					<form:errors cssClass="invalid-feedback" path="description" />
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary" data-disable="true" data-disable-with="${disableWith}"><c:out value="${submit}" /></button>
	
				</div>
			</form:form>
