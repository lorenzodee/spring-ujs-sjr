<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/javascript" %>
<jsp:include page="_form.js.jsp">
  <jsp:param name="remote" value="true" />
  <jsp:param name="title" value="Edit Thing" />
</jsp:include>