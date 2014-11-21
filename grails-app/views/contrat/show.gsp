
<%@ page import="albybail.Contrat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrat.label', default: 'Contrat')}" />
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
							<g:link class="btn btn-default edit" action="edit" resource="${contratInstance}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				<g:if test="${contratInstance?.nom}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.nom.label" default="Nom" />
					</div>
					<div class="show-value col-sm-10">
						<g:fieldValue bean="${contratInstance}" field="nom"/>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.dateDebut}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.dateDebut.label" default="Date Debut" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatDate date="${contratInstance?.dateDebut}" type="date" style="LONG" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.dateFin}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.dateFin.label" default="Date Fin" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatDate date="${contratInstance?.dateFin}" type="date" style="LONG" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.montantLoyer}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.montantLoyer.label" default="Montant Loyer" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatNumber number="${contratInstance.montantLoyer}" type="currency" currencyCode="EUR" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.montantCharges}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.montantCharges.label" default="Montant Charges" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatNumber number="${contratInstance.montantLoyer}" type="currency" currencyCode="EUR" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.revisionActive}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.revisionActive.label" default="Revision Active" />
					</div>
					<div class="show-value col-sm-10">
						<g:link controller="revision" action="show" id="${contratInstance?.revisionActive?.id}">${contratInstance?.revisionActive?.encodeAsHTML()}</g:link>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.dureeRevision}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.dureeRevision.label" default="Duree Revision" />
					</div>
					<div class="show-value col-sm-10">
						<g:fieldValue bean="${contratInstance}" field="dureeRevision"/>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.locataire}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.locataire.label" default="Locataire" />
					</div>
					<div class="show-value col-sm-10">
						<g:link controller="locataire" action="show" id="${contratInstance?.locataire?.id}">${contratInstance?.locataire?.encodeAsHTML()}</g:link>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.locaux}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.locaux.label" default="Locaux" />
					</div>
					<div class="show-value col-sm-10">
						<g:each in="${contratInstance.locaux}" var="l">
						<g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link><br />
						</g:each>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.profil}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.profil.label" default="Profil" />
					</div>
					<div class="show-value col-sm-10">
						<g:link controller="profil" action="show" id="${contratInstance?.profil?.id}">${contratInstance?.profil?.encodeAsHTML()}</g:link>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.revisions}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.revisions.label" default="Revisions" />
					</div>
					<div class="show-value col-sm-10">
						<g:each in="${contratInstance.revisions}" var="r">
						<g:link controller="revision" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link><br />
						</g:each>
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.chezNotaire}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.chezNotaire.label" default="Chez Notaire" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatBoolean boolean="${contratInstance?.chezNotaire}" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.estTermine}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.estTermine.label" default="Est Termine" />
					</div>
					<div class="show-value col-sm-10">
						<g:formatBoolean boolean="${contratInstance?.estTermine}" />
					</div>
				</div>
				</g:if>
			
				<g:if test="${contratInstance?.remarques}">
				<div class="row show-row">
    				<div class="show-label col-sm-2">
						<g:message code="contrat.remarques.label" default="Remarques" />
					</div>
					<div class="show-value col-sm-10">
					
						<g:fieldValue bean="${contratInstance}" field="remarques"/>
					
					</div>
				</div>
				</g:if>
			</div>
		</div>
	</body>
</html>