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

<form:form action="${requestURI }" modelAttribute="comment">
	<security:authorize access="hasRole('USER') or hasRole('ADMIN')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="replies" />
	<form:hidden path="moment" />
	

	<acme:textarea code="comment.text" path="text"/>
	<br/>
	<acme:textbox code="comment.picture" path="picture"/>
	
	<br/>
	<acme:select items="${rendezvous }" itemLabel="name" code="comment.rendezvous" path="rendezvous"/>
	 <%--
	<acme:select items="${commentParent}" itemLabel="picture" code="comment.commentParent"  path="commentParent"/>
	--%>
	<br/>
</security:authorize>
	<%-- <jstl:choose>
		

			<form:label path="rendezvous">
				<spring:message code="comment.rendezvous" />:
			</form:label>
			<form:select path="rendezvous">
				<form:option item="null" value="0" label="----" />
				<form:options items="${rendezvous}" itemLabel="name" />
			</form:select>
			<form:errors cssClass="error" path="rendezvous" />
			<br />

		
	</jstl:choose> --%>

	<input type="button" name="cancel"
			value="<spring:message code="comment.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	
	
	<security:authorize access="hasRole('USER')">
		<input type="submit" name="save"
			value="<spring:message code="comment.save" />" />&nbsp; 
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${comment.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="comment.delete" />"
			onclick="return confirm('<spring:message code="comment.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
		
</form:form>