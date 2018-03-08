<%-- list.jsp de Question --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="questions" id="row" requestURI="question/user/list.do" pagesize="5" class="displaytag">
		
	<display:column sortable="false" title="${edit}">
		<a href="question/user/edit.do?questionId=${row.id}"><spring:message code="question.edit" /></a>
	</display:column>
	
	<spring:message code="question.text" var="textHeader"/>
	<display:column property="content" title="${textHeader}" sortable="false"/>

</display:table>

<a href="question/user/create.do?rendezvousId=${rendezvousId}"><spring:message code="question.create"/></a>
<br/>

<acme:cancel code="question.cancel" url="rendezvous/user/edit.do?rendezvousId=${rendezvousId}"/>
