<%--
 * register_Explorer.jsp
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

<form:form action="user/register_User.do" modelAttribute="userForm">
	
	<acme:textbox code="user.name" path="name"/>
	<br/>
	<acme:textbox code="user.surname" path="surname"/>
	<br/>
	<acme:textbox code="user.email" path="email"/>
	<br/>
	<acme:textbox code="user.phone" path="phone"/>
	<br/>
	<acme:textbox code="user.address" path="address"/>
	<br/>
	<acme:textbox code="user.birth" path="birth"/>
	<br/>
	<acme:textbox code="user.username" path="username"/>
	<br/>
	<acme:textbox code="user.password" path="password"/>
	<br/>
	<acme:textbox code="user.repeatPassword" path="repeatPassword"/>
	<br/>
	
	<form:checkbox path="acceptTerms"/>
	<form:label path="acceptTerms">
		<spring:message code="actor.acceptTerms" 
		<a href="javascript: relativeRedir('/');"> </a>  />
	</form:label>
	<br />
	
	<acme:submit name="user.save" code="save"/>
	<acme:submit name="user.cancel" code="cancel"/>
	
	<%-- 
	
	<form:label path="name">
		<spring:message code="actor.name" />:
		</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="actor.surname" />:
		</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="actor.email" />:
		</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phone">
		<spring:message code="actor.phone" />:
		</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />

	<form:label path="address">
		<spring:message code="actor.address" />:
		</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="birth">
		<spring:message code="actor.birth" />:
		</form:label>
	<form:input path="birth" />
	<form:errors cssClass="error" path="birth" />
	<br />

	<form:label path="userAccount.username">
		<spring:message code="actor.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors class="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="actor.password" />
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors class="error" path="userAccount.password" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	
	--%>
</form:form>
	
	
	
	
	
	
	
	
	
	
