<%-- display.jsp de Announcement --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="announcement/display.do" modelAttribute="announcement">
	
	<form:hidden path="id" />
	<form:hidden path="version" />


<b><spring:message code="announcement.moment"/>:&nbsp;</b><jstl:out value="${announcement.moment}"/>
<br/>

<b><spring:message code="announcement.title"/>:&nbsp;</b><jstl:out value="${announcement.title}"/>
<br/>

<b><spring:message code="announcement.description"/>:&nbsp;</b><jstl:out value="${announcement.description}"/>


<br/>

<b><spring:message code="announcement.rendezvous"/>:&nbsp;</b><jstl:out value="${announcement.rendezvous.name}"/>
<br/>

<security:authorize access="hasRole('ADMIN')">
<jstl:if test="${comment.id != 0}">
	<input type="submit" name="delete"
		value="<spring:message code="comment.delete" />"
		onclick="return confirm('<spring:message code="comment.confirm.delete" />')" />&nbsp;
</jstl:if>
</security:authorize>
</form:form>

<a href="javascript:window.history.back();">&laquo; <spring:message code="terms.back"/></a>

