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

<form:form action="request/user/edit.do" modelAttribute="requestForm">

	<form:hidden path="id"/>
	<jstl:if test="${requestForm.rendezvous != null}">
		<form:hidden path="rendezvous"/>
	</jstl:if>
	
	<jstl:if test="${requestForm.rendezvous == null}">
		<acme:select items="${rendezvouses}" itemLabel="name" code="request.rendezvous" path="rendezvous"/>
	</jstl:if>
	
	<acme:select items="${service}" itemLabel="name" code="" path=""/>
	
	<fieldset>
		<legend><spring:message code="request.creditCard"/>:</legend>
		
		<acme:textbox code="request.creditCard.holder" path="holder"/>
		<br/>
		<acme:textbox code="request.creditCard.brand" path="brand"/>
		<br/>
		<acme:textbox code="request.creditCard.number" path="number"/>
		<br/>
		<acme:textbox code="request.creditCard.expirationMonth" path="expirationMonth" placeholder="MM"/>
		<br/>
		<acme:textbox code="request.creditCard.expirationYear" path="expirationYear" placeholder="YY"/>
		<br/>
		<acme:textbox code="request.creditCard.cvv" path="cvv" placeholder="###"/>
		<br/>
	</fieldset>
	
	<acme:textarea code="request.comment" path="comment"/>
		
	<acme:submit name="save" code="service.save"/>
	&nbsp;
	<jstl:choose>
		<jstl:when test="${requestForm == null}">
			<acme:cancel url="welcome/index.do" code="request.cancel"/>
		</jstl:when>
		<jstl:when test="${requestForm != null}">
			<acme:cancel url="rendezvous/user/edit.do?rendezvousId=${requestForm.rendezvous.id}" code="request.cancel"/>
		</jstl:when>
	</jstl:choose>

</form:form>