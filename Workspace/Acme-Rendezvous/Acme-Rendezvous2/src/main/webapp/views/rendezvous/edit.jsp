<%-- edit.jsp de Application --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="announcements"/>
	<form:hidden path="comments"/>
	<form:hidden path="questions"/>
	<form:hidden path="organiser"/>
	<form:hidden path="attendants"/>
	<form:hidden path="linkedRendezvouses"/>
	<form:hidden path="deleted"/>
	<jstl:if test="${rendezvous.finalVersion == false}">
		<form:hidden path="finalVersion"/>
	</jstl:if>
	
	<acme:textbox code="rendezvous.name" path="name" readonly="${rendezvous.finalVersion}"/>
	<br/>
	
	<acme:textarea code="rendezvous.description" path="description" readonly="${rendezvous.finalVersion}"/>
	<br/>
	
	<acme:textbox code="rendezvous.picture" path="picture" readonly="${rendezvous.finalVersion}"/>
	<br/>
	
	<acme:textbox code="rendezvous.moment" path="moment" placeholder="dd/MM/yyyy HH:mm" readonly="${rendezvous.finalVersion}"/>
	<br/>
	
	<fieldset>
		<legend><spring:message code="rendezvous.gpsCoordinate"/>:</legend>
		
		<acme:textbox code="rendezvous.gpsCoordinate.latitude" path="gpsCoordinate.latitude" placeholder="(-90.0, 90.0)" readonly="${rendezvous.finalVersion}"/>
		<br/>
		<acme:textbox code="rendezvous.gpsCoordinate.longitude" path="gpsCoordinate.longitude" placeholder="(-180.0, 180.0)" readonly="${rendezvous.finalVersion}"/>
		<br/>
	</fieldset>
	
	<acme:checkbox code="rendezvous.adult" path="adult" disabled="${rendezvous.finalVersion}"/>
	<br/>
	
	<jstl:if test="${rendezvous.finalVersion == false}">
		<acme:checkbox code="rendezvous.finalVersion" path="finalVersion" disabled="${rendezvous.finalVersion}"/>
		<br/>
	</jstl:if>
	
	<jstl:if test="${rendezvous.id != 0}">
		<a href="rendezvous/user/list-link.do?rendezvousId=${rendezvous.id}"><spring:message code="rendezvous.assignRendezvouses"/></a>
		<br/>
		
		<a href="announcement/user/list.do?rendezvousId=${rendezvous.id}"><spring:message code="rendezvous.listAnnouncements"/></a>
		<br/>
		
		<a href="question/user/list.do?rendezvousId=${rendezvous.id}"><spring:message code="rendezvous.listQuestions"/></a>
		<br/>
	</jstl:if>
	
	<jstl:if test="${rendezvous.finalVersion == false and rendezvous.deleted == false}">
		<acme:submit name="save" code="rendezvous.save"/>
		&nbsp;
		<jstl:if test="${rendezvous.id != 0}">
			<acme:submit name="delete" code="rendezvous.delete"/>
			&nbsp;
		</jstl:if>
	</jstl:if>
	
	<acme:cancel url="rendezvous/user/list.do" code="rendezvous.cancel"/>
	
</form:form>