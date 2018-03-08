<%-- list.jsp de Application --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="answerForms" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message var="questionHeader" code="answer.question" />
	<display:column property="question.content" title="${questionHeader}" />

	<spring:message var="answerHeader" code="answer.answer" />
	<display:column property="content" title="${anwerHeader}" />

	<jstl:if test="${canAnswer == true}">
		<display:column sortable="false">
			<a href="answer/user/create.do?questionId=${row.questionId}">
				<spring:message code="answer.respond" />
			</a>
		</display:column>
	</jstl:if>
</display:table>

<jstl:if test="${allAnswered == true}">
	<input type="button" value="<spring:message code="answer.continue"/>" onClick="relativeRedir('rendezvous/user/rspv.do?rendezvousId=${rendezvous.id}')"/>	
</jstl:if>
