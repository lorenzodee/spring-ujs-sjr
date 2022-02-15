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
	<div class="row">
		<div class="col-12 col-md-10 offset-md-1 col-lg-8 offset-lg-2">
			<p><tags:link-to-back>&laquo; Back</tags:link-to-back></p>
			<h1>Create New Thing</h1>
			<jsp:include page="_form.html.jsp" />
		</div>
	</div>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/popper.js/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/rails-jquery-ujs.js"></script>
</body>
</html>
