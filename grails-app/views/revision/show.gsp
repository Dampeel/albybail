
<%@ page import="albybail.Revision" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'revision.label', default: 'Revision')}" />
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
						<g:form url="[resource:revisionInstance, action:'delete']" method="DELETE">
							<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label" /></g:link>
							<g:link class="btn btn-default edit" action="edit" resource="${revisionInstance}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				
					<g:if test="${revisionInstance?.dateFin}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.dateFin.label" default="Date Fin" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:formatDate date="${revisionInstance?.dateFin}" />
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.indice}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.indice.label" default="Indice" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${revisionInstance}" field="indice"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.montantLoyer}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.montantLoyer.label" default="Montant Loyer" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${revisionInstance}" field="montantLoyer"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.montantCharges}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.montantCharges.label" default="Montant Charges" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${revisionInstance}" field="montantCharges"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.remarques}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.remarques.label" default="Remarques" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:fieldValue bean="${revisionInstance}" field="remarques"/>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.contrat}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.contrat.label" default="Contrat" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:link controller="contrat" action="show" id="${revisionInstance?.contrat?.id}">${revisionInstance?.contrat?.encodeAsHTML()}</g:link>
						
						</div>
					</div>
					</g:if>
				
					<g:if test="${revisionInstance?.dateDebut}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="revision.dateDebut.label" default="Date Debut" />
						</div>
						<div class="show-value col-sm-10">
						
							<g:formatDate date="${revisionInstance?.dateDebut}" />
						
						</div>
					</div>
					</g:if>
				
			</div>
		</div>
	</body>
</html>
