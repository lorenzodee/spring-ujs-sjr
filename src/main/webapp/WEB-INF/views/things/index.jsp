<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Experiments with Spring Boot</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="<c:url value='/favicon.ico' />" rel="icon" type="image/png" />
<link type="text/css" rel="stylesheet" href="<c:url value='/webjars/bootstrap/css/bootstrap.min.css' />" />
<link type="text/css" rel="stylesheet" href="<c:url value='/webjars/font-awesome/css/font-awesome.css' />" />
</head>
<body>
<div class="container">
	<h1>Things</h1>
	<c:if test="${empty thingsPage.content}">
	<p>There are no things yet.</p>
	</c:if>
	<c:if test="${not empty thingsPage.content}">
	<table id="things" class="table">
		<tr>
			<th>Name</th>
			<th><span class="sr-only">Show</span></th>
			<th><span class="sr-only">Edit</span></th>
			<th><span class="sr-only">Remove</span></th>
		</tr>
		<c:forEach var="thing" items="${thingsPage.content}">
		<tr id="${thing.id}">
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
	<br/>
	<p><a id="add-thing-button" class="btn btn-secondary" href="<c:url value='/things?create' />">Create...</a></p>
</div>
<div id="modalForm" class="modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p class="text-center">Loading...</p>
			</div>
		</div>
	</div>
</div>
<div class="d-flex justify-content-center align-items-center" style="position: absolute; top: 0; width: 100vw">
<div id="notifier" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="position: relative; top: 1em">
  <div class="toast-header">
  	<strong id="notifier-body"><c:out value="${_notice}" /></strong>
    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
</div>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/popper.js/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/rails-jquery-ujs.js"></script>
<c:if test="${not empty _notice}"><script>$('#notifier').toast('show')</script></c:if>
</body>
</html>
