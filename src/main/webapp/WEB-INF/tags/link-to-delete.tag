<%@ include file="/WEB-INF/tags/_tagdirectives.tagf" %>
<%@ tag dynamic-attributes="_dynamicAttributes" %>
<%@ attribute name="path" type="java.lang.String" required="true" rtexprvalue="true" description="Path to objects" %>
<%@ attribute name="oid" type="java.lang.String" required="true" rtexprvalue="true" description="Object ID value" %>
<%@ attribute name="remote" type="java.lang.Boolean" required="false" rtexprvalue="false" description="Optional flag to use Ajax when true" %>
<a href="<tags:url-to-delete oid='${oid}' path='${path}' />" data-method="delete"<c:if test="${remote}"><c:out value=" " />data-remote="true"</c:if><c:forEach var="_dynattr" items="${_dynamicAttributes}"><c:out value=" " />${_dynattr.key}="${_dynattr.value}"</c:forEach>><jsp:doBody /></a>
