<%@ include file="/WEB-INF/tags/_tagdirectives.tagf" %>
<%@ attribute name="path" type="java.lang.String" required="true" rtexprvalue="true" description="Path to objects" %>
<%@ attribute name="oid" type="java.lang.String" required="true" rtexprvalue="true" description="Object ID value" %>
<%@ attribute name="var" type="java.lang.String" required="false" rtexprvalue="false" description="Optional output variable" %>
<c:set var="_path" value="${fn.startsWith(path, '/') ? path : '/'.concat(path)}" />
<spring:url var="_href" value="${_path}/{id}"><%-- /articles/42?delete --%>
	<spring:param name="id" value="${oid}" />
	<spring:param name="delete" />
</spring:url>
<c:choose>
    <c:when test="${not empty var}">
        <c:set property="${var}" target="${requestScope}" value="${_href}" />
    </c:when>
    <c:otherwise>
    	${_href}
    </c:otherwise>
</c:choose>
