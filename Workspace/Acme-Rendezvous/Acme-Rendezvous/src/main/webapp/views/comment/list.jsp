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
	name="comment" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="comment/admin/edit.do?commentId=${row.id}"><spring:message code="comment.edit"/></a>
		</display:column>
	</security:authorize>
	
	<spring:message code="comment.moment" var="momentHeader" />
	<spring:message var="formatDate" code="comment.format.date"/>
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="false" />

	<spring:message code="comment.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="false" />
	
	<spring:message code="comment.rendezvous" var="rendezvousHeader" />
	<display:column property="rendezvous.name" title="${rendezvousHeader}"	sortable="true" />
	
	<spring:message code="comment.user" var="userHeader" />
	<display:column property="user.name" title="${userHeader}"	sortable="true" />

	<spring:message code="comment.replies" var="repliesHeader"/>
	<display:column title="${repliesHeader}">
		<a href="comment/user/listReplies.do?commentId=${row.id}">
			<spring:message code="comment.replyComment"/>
		</a>
	</display:column>
	
	<%-- 
	<spring:message code="comment.createReply" var="createReplyHeader"/>
	<display:column title="${createReplyHeader}">
		<a href="comment/user/editReplies.do?commentId=${row.id}">
			<spring:message code="comment.replyComment"/>
		</a>
	</display:column>
	--%>
	
</display:table>

	<security:authorize access="hasRole('USER')">
		<div>
			<a href="comment/user/edit.do">
				<button>
					<spring:message code="comment.create" />
				</button>
			</a>
		</div>
	</security:authorize>
