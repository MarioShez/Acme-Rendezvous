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

<form:form action="manager/register.do" modelAttribute="managerForm">
	
	<acme:textbox code="manager.name" path="name"/>
	<br/>
	<acme:textbox code="manager.surname" path="surname"/>
	<br/>
	<acme:textbox code="manager.vat" path="vat"/>
	<br/>
	<acme:textbox code="manager.email" path="email"/>
	<br/>
	<acme:textbox code="manager.phone" path="phone"/>
	<br/>
	<acme:textbox code="manager.address" path="address"/>
	<br/>
	<acme:textbox code="manager.birth" path="birth"/>
	<br/>
	<acme:textbox code="manager.userName" path="userAccount.username"/>
	<br/>
	<acme:password code="manager.password" path="userAccount.password"/>
	<br/>
	<acme:password code="manager.repeatPassword" path="repeatPassword"/>
	<br/>
	
	<acme:checkbox code="manager.acceptTerms" path="termsAndConditions"/>
	
	<a href="terms/list.do"><spring:message code="manager.acceptTermsLink"/></a>
	<br />
	
	<acme:submit name="save" code="manager.save"/>
	<acme:cancel url="welcome/index.do" code="manager.cancel"/>
	
</form:form>
	
	
	
	
	
	
	
	
	
	
