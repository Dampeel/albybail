
<%@ page import="albybail.Batiment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batiment.label', default: 'Batiment')}" />
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
						<g:form url="[resource:batimentInstance, action:'delete']" method="DELETE">
							<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label" /></g:link>
							<g:link class="btn btn-default edit" action="edit" resource="${batimentInstance}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				
					<g:if test="${batimentInstance?.nom}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="batiment.nom.label" default="Nom" />
						</div>
						<div class="show-value col-sm-10">
							<g:fieldValue bean="${batimentInstance}" field="nom"/>
						</div>
					</div>
					</g:if>
				
					<g:if test="${batimentInstance?.adresse}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="batiment.adresse.label" default="Adresse" />
						</div>
						<div class="show-value col-sm-10">
							<g:fieldValue bean="${batimentInstance}" field="adresse"/>
						</div>
					</div>
					</g:if>
				
					<g:if test="${batimentInstance?.locaux}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="batiment.locaux.label" default="Locaux" />
						</div>
						<div class="show-value col-sm-10">
							<g:each in="${batimentInstance.locaux}" var="l">
							<g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link><br />
							</g:each>
						</div>
					</div>
					</g:if>
				
			</div>
		</div>
	</body>
</html>
