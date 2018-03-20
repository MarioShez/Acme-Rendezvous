<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="category" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="category/admin/edit.do?categoryId=${row.id }"><spring:message code="category.edit"/></a>
		</display:column>
	</security:authorize>
	
	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="category.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />

	<display:column>
		<a href="rendezvous/listByCategory.do?categoryId=${row.id}"><spring:message code="category.rendezvous"/></a>
	</display:column>
	
	<display:column>
		<a href="category/list.do?categoryId=${row.id}"><spring:message code="category.categories"/></a>
	</display:column>
	
	
</display:table>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="category/admin/create.do"><spring:message code="category.create"/></a>
			<br/>
		</display:column>
	</security:authorize>

	
