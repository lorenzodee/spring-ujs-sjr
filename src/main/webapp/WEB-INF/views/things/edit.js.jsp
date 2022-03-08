<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/javascript" %>
<c:set var="jsHtml">
	<spring:escapeBody htmlEscape="false" javaScriptEscape="true">
		<jsp:include page="_form.html.jsp" />
		<%-- render the same form with error messages --%>
	</spring:escapeBody>
</c:set>

<c:set var="jsTitle">
	<spring:escapeBody htmlEscape="false" javaScriptEscape="true">Edit Thing</spring:escapeBody>
</c:set>

(function() {
  const $modalForm = $('#modalForm');
  $modalForm.find('.modal-body').html('${jsHtml}');
  // $('#thing-form').replaceWith('${jsHtml}');
  if (!$modalForm.is(':visible')) {
    $modalForm.find('.modal-title').html('${jsTitle}');
    $modalForm.modal('show');
  }
})()
