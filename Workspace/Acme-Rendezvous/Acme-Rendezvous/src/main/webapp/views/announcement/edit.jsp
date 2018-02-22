<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action ="${requestURI }" modelAttribute="announcement">

	<form:hidden path="id"/>
	<form:form path="version"/>
	
<security:authorize access="hasRole('USER')">

	<form:hidden path="moment"/>

	<acme:textbox code="announcement.title" path="title"/>
	
	<acme:textbox code="announcement.description" path="description"/>
	
	<acme:select items="${rendezvous}" itemLabel="rendezvous" 
			code="annoncement.rendezvous" path="rendezvous"/>
	

</security:authorize>

<input type="submit" name="save"
		value="<spring:message code="announcement.save" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="announcement.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />

</form:form>	