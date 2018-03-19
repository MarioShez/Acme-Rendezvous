<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="categoryForm">
	<security:authorize access="hasRole('ADMIN')">
	
	<acme:textarea code="category.name" path="text"/>
	<br/>
	
	<acme:textarea code="category.description" path="text"/>
	<br/>
	
	<acme:select items="${categoryParent}" itemLabel="name" code="category.categoryParent" path="categoryParent"/>
	<br/>
	
</security:authorize>

	<input type="button" name="cancel"
			value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	
	
	<security:authorize access="hasRole('ADMIN')">
		<jstl:when test="${requestURI == 'category/admin/edit.do'}">
			<input type="submit" name="save"
				value="<spring:message code="category.save" />" />&nbsp; 
		</jstl:when>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${categoryForm.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="category.delete" />"
			onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
		
</form:form>