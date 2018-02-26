<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('ADMIN')">

<spring:message code="administrator.rendezvousPerUserLabel"/>
<jstl:out value="${rendezvousPerUserLabel}"></jstl:out>
<table class="displaytag"  name="rendezvousPerUser">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${rendezvousPerUser}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>
<%--
<spring:message code="administrator.ratioUserRendezvousLabel" />
<jstl:out value="${ratioUserRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="ratioUserRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioUserRendezvous}"></jstl:out>
		</td>
	</tr>
</table>
--%>

<spring:message code="administrator.userPerRendezvousLabel"/>
<jstl:out value="${userPerRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="userPerRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${userPerRendezvous}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.rsvpdPerUserLabel"/>
<jstl:out value="${rsvpdPerUserLabel}"></jstl:out>
<table class="displaytag"  name="rsvpdPerUser">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${rsvpdPerUser}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>
 
<spring:message code="administrator.topRendezvousLabel"/>
<jstl:out value="${topRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="topRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.top" var="topHeader" />
			<jstl:out value="${topHeader}"></jstl:out>
		</th>
	</tr>
	<jstl:forEach var="datos" items="${topRendezvous}">
	<tr>
		<td>
			<jstl:out value="${datos.name}"></jstl:out>
		</td>
	</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.announcementsPerRendezvousLabel"/>
<jstl:out value="${announcementsPerRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="announcementsPerRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${announcementsPerRendezvous}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>


<spring:message code="administrator.rendezvousNumberAnnouncementsLabel"/>
<jstl:out value="${rendezvousNumberAnnouncementsLabel}"></jstl:out>
<table class="displaytag"  name="rendezvousNumberAnnouncements">
	<tr>
		<th>
			<spring:message code="administrator.rendezvous" var="rendezvousHeader" />
			<jstl:out value="${rendezvousHeader}"></jstl:out>
		</th>
	</tr>
	<jstl:forEach var="datos" items="${rendezvousNumberAnnouncements}">
	<tr>
		<td>
			<jstl:out value="${datos.name}"></jstl:out>
		</td>
	</tr>
	</jstl:forEach>
</table>

<spring:message code="administrator.rendezvousLinkedLabel"/>
<jstl:out value="${rendezvousLinkedLabel}"></jstl:out>
<table class="displaytag"  name="rendezvousLinked">
	<tr>
		<th>
			<spring:message code="administrator.rendezvous" var="rendezvousHeader" />
			<jstl:out value="${rendezvousHeader}"></jstl:out>
		</th>
	</tr>
	<jstl:forEach var="datos" items="${rendezvousLinked}">
	<tr>
		<td>
			<jstl:out value="${datos.name}"></jstl:out>
		</td>
	</tr>
	</jstl:forEach>
</table>


<spring:message code="administrator.questionsPerRendezvousLabel"/>
<jstl:out value="${questionsPerRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="questionsPerRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${questionsPerRendezvous}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>
<%--
<spring:message code="administrator.answersPerRendezvousLabel"/>
<jstl:out value="${answersPerRendezvousLabel}"></jstl:out>
<table class="displaytag"  name="answersPerRendezvous">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${datos}"></jstl:out>
		</td>
	</tr>
</table>
--%>
<spring:message code="administrator.repliesPerCommentLabel"/>
<jstl:out value="${repliesPerCommentLabel}"></jstl:out>
<table class="displaytag"  name="repliesPerComment">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${repliesPerComment}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

</security:authorize>


