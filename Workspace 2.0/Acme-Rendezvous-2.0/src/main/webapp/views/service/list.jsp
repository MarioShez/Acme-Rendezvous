<%-- list.jsp de Application --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="requests" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		
	<spring:message var="rendezvousHeader" code="request.rendezvous"/>
	<display:column property="rendezvous.name" title="${rendezvousHeader}"/>
	
	<spring:message var="serviceHeader" code="request.service"/>
	<display:column property="service.name" title="${serviceHeader}"/>
	
	<spring:message var="commentHeader" code="request.comment"/>
	<display:column property="comment" title="${commentHeader}"/>
		
</display:table>

<security:authorize access="hasRole('USER')">
	<a href="request/user/create.do"><spring:message code="request.create"/></a>
	<br/>
</security:authorize>

<acme:cancel code="request.cancel" url="welcome/index.do"/>