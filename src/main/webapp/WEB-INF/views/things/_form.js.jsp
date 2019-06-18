<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/javascript" %>
<c:set var="doubleQuote" value='"' />
<c:set var="backslashDoubleQuote" value='\\"' />
<% pageContext.setAttribute("carriageReturnChar", "\r"); %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<c:import var="html" url="/WEB-INF/views/things/_form.jsp" />
<c:set var="html" value="${fn:replace(html, doubleQuote, backslashDoubleQuote)}" />
<c:set var="html" value="${fn:replace(html, carriageReturnChar, '')}" />
<c:set var="html" value="${fn:replace(html, newLineChar, '')}" />

<c:set var="title" value="${fn:replace(param.title, doubleQuote, backslashDoubleQuote)}" />
<c:set var="title" value="${fn:replace(title, carriageReturnChar, '')}" />
<c:set var="title" value="${fn:replace(title, newLineChar, '')}" />

(function() {
  const $modalForm = $('#modalForm');
  $modalForm.find('.modal-body').html('${html}');
  // $('#thing-form').replaceWith('${html}');
  if (!$modalForm.is(':visible')) {
    $modalForm.find('.modal-title').html('${title}');
    $modalForm.modal('show');
  }
})()
