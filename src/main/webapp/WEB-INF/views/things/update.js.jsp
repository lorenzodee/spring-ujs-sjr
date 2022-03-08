<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/javascript" %>
<c:set var="jsHtml">
	<spring:escapeBody htmlEscape="false" javaScriptEscape="true">
		<jsp:include page="_index.html.jsp" />
	</spring:escapeBody>
</c:set>

(function() {
  $('#things').replaceWith('${jsHtml}');
  const $modalForm = $('#modalForm');
  $modalForm.modal('hide');
})()
