<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="rendezvous/list.do"><spring:message code="master.page.avalibleRendezvouses" /></a></li>
			<li><a class="fNiv" href="user/list.do"><spring:message code="master.page.listUsers" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="user/register.do"><spring:message code="master.page.register" /></a>
			<li><a class="fNiv" href="terms/list.do"><spring:message code="master.page.termsAndConditions" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="rendezvous/list.do"><spring:message code="master.page.avalibleRendezvouses" /></a></li>
			<li><a class="fNiv" href="user/list.do"><spring:message code="master.page.listUsers" /></a></li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<!-- 
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>
					-->					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
					 
				</ul>
			</li>
			<li><a class="fNiv" href="terms/list.do"><spring:message code="master.page.termsAndConditions" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.admin" /></a>
				<ul>
					<li class="arrow"></li>
					<!-- 
					<li><a href="admin/action-1.do"><spring:message code="master.page.admin.action.1" /></a></li>
					<li><a href="admin/action-2.do"><spring:message code="master.page.admin.action.2" /></a></li>
					 -->				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message	code="master.page.user" /></a>
				<ul>
					<li class="arrow"></li> 
					<li><a href="rendezvous/user/list-organised.do"><spring:message code="master.page.user.listRendezvousOrganised" /></a></li>
					<li><a href="rendezvous/user/list-rspv.do"><spring:message code="master.page.user.listRendezvousRSPVd" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

