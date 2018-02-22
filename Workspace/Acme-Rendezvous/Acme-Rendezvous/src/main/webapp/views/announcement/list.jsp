<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="announcement" requestURI="${requestURI }" id="row">	
	
	<spring:message code="announcement.moment" var="momentHeader"/>
	<display:colum property="moment" title="${momentHeader}" soportable="true"/>
	
	<spring:message code="announcement.title" var="titleHeader"/>
	<display:colum property="title" title="${titleHeader}" soportable="true"/>
	
	<spring:message code="announcement.description" var="descriptionHeader"/>
	<display:colum property="description" title="${descriptionHeader}" soportable="true"/>
	
	<spring:message code="announcement.rendezvous" var="rendezvousHeader"/>
	<display:colum title="${rendezvous}">
		<a hred="rendezvous/display.do?rendezvousId=${row.id}">
		<spring:message code="rendezvous.display"/></a>
	</display:colum>
	
<security:authorize access="hasRole('USER')">>
	
	<div>
		<a href="annoncement/create.do">
			<button>
				<spring:message code="annoncement.create" />
			</button>
		</a>
	</div>
	
</security:authorize>
	
	

</display:table>