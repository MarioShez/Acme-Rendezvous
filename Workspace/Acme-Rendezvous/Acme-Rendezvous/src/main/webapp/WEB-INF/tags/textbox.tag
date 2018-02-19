<%--
 * textbox.tag
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="name" required="true" %>
<%@ attribute name="surname" required="true" %>
<%@ attribute name="email" required="true" %>
<%@ attribute name="phone" required="false" %>
<%@ attribute name="address" required="false" %>
<%@ attribute name="birth" required="true" type="java.util.Date" %>
<%@ attribute name="username" required="true" %>
<%@ attribute name="repeatPassword" required="true" %>



<%@ attribute name="readonly" required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<%-- Definition --%>

<div>
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${path}" readonly="${readonly}" />	
	<form:errors path="${path}" cssClass="error" />
</div>

<div>
	<form:label path="${name}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${name}" readonly="${readonly}" />	
	<form:errors path="${name}" cssClass="error" />
</div>	

<div>
	<form:label path="${surname}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${surname}" readonly="${readonly}" />	
	<form:errors path="${surname}" cssClass="error" />
</div>	

<div>
	<form:label path="${email}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${email}" readonly="${readonly}" />	
	<form:errors path="${email}" cssClass="error" />
</div>	

<div>
	<form:label path="${phone}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${phone}" readonly="${readonly}" />	
	<form:errors path="${phone}" cssClass="error" />
</div>	

<div>
	<form:label path="${address}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${address}" readonly="${readonly}" />	
	<form:errors path="${address}" cssClass="error" />
</div>	

<div>
	<form:label path="${birth}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${birth}" readonly="${readonly}" />	
	<form:errors path="${birth}" cssClass="error" />
</div>	

<div>
	<form:label path="${username}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${username}" readonly="${readonly}" />	
	<form:errors path="${username}" cssClass="error" />
</div>	

<div>
	<form:label path="${repeatPassword}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${repeatPassword}" readonly="${readonly}" />	
	<form:errors path="${repeatPassword}" cssClass="error" />
</div>