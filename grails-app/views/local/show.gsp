
<%@ page import="albybail.Local" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'local.label', default: 'Local')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="container">
			<div class="row">
	   			<div class="col-sm-7">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>
				<div class="col-sm-5">
					<div class="h1-buttons">
						<g:form url="[resource:localInstance, action:'delete']" method="DELETE">
							<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label" /></g:link>
							<g:link class="btn btn-default edit" action="edit" resource="${localInstance}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				<g:if test="${localInstance?.nom}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="local.nom.label" default="Nom" />
					</div>
					<div class="show-value col-sm-10">
						<g:fieldValue bean="${localInstance}" field="nom"/>
					</div>
				</div>
				</g:if>
			
				<g:if test="${localInstance?.surface}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="local.surface.label" default="Surface" />
					</div>
					<div class="show-value col-sm-10">
						<g:fieldValue bean="${localInstance}" field="surface"/>
					</div>
				</div>
				</g:if>
			
				<g:if test="${localInstance?.batiment}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="local.batiment.label" default="Batiment" />
					</div>
					<div class="show-value col-sm-10">
						<g:link controller="batiment" action="show" id="${localInstance?.batiment?.id}">${localInstance?.batiment?.encodeAsHTML()}</g:link>
					</div>
				</div>
				</g:if>
			
				<g:if test="${localInstance?.contrats}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="local.contrats.label" default="Contrats" />
					</div>
					<div class="show-value col-sm-10">
						<g:each in="${localInstance.contrats}" var="c">
						<g:link controller="contrat" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link>
						</g:each>
					</div>
				</div>
				</g:if>
			</div>
		</div>
	</body>
</html>
