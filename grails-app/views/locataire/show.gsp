
<%@ page import="albybail.Locataire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'locataire.label', default: 'Locataire')}" />
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
						<g:form url="[resource:contratInstance, action:'delete']" method="DELETE">
							<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label" /></g:link>
							<g:link class="btn btn-default edit" action="edit" resource="${locataireInstance}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				
					<g:if test="${locataireInstance?.nom}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="locataire.nom.label" default="Nom" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${locataireInstance}" field="nom"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${locataireInstance?.adresse}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="locataire.adresse.label" default="Adresse" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${locataireInstance}" field="adresse"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${locataireInstance?.remarques}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="locataire.remarques.label" default="Remarques" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${locataireInstance}" field="remarques"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${locataireInstance?.contrats}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="locataire.contrats.label" default="Contrats" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:each in="${locataireInstance.contrats}" var="c">
							<g:link controller="contrat" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link>
							</g:each>
						
						</div>
					</div>
					</g:if>
				
			</div>
		</div>
	</body>
</html>
