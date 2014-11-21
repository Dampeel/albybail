
<%@ page import="albybail.Contrat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrat.label', default: 'Contrat')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="container">
			<div class="row">
	   			<div class="col-sm-7">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>
				<div class="col-sm-5">
					<div class="h1-buttons">
						<g:link class="btn btn-default create" action="create"><g:message code="default.create.label" args="[entityName]" /></g:link>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
							
								<g:sortableColumn property="nom" title="${message(code: 'contrat.nom.label', default: 'Nom')}" />
							
								<g:sortableColumn property="locataire" title="${message(code: 'contrat.locataire.label', default: 'Locataire')}" />
							
								<th><g:message code="contrat.locaux.label" default="Locaux" /></th>
								
								<g:sortableColumn property="dateDebut" title="${message(code: 'contrat.dateDebut.label', default: 'Date Debut')}" />
							
								<g:sortableColumn property="dateFin" title="${message(code: 'contrat.dateFin.label', default: 'Date Fin')}" />
							
								<g:sortableColumn property="montantLoyer" title="${message(code: 'contrat.montantLoyer.label', default: 'Montant Loyer')}" />
							
								<g:sortableColumn property="montantCharges" title="${message(code: 'contrat.montantCharges.label', default: 'Montant Charges')}" />
							
								<g:sortableColumn property="remarques" title="${message(code: 'contrat.remarques.label', default: 'Remarques')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${contratInstanceList}" var="contratInstance">
							<tr>
							
								<td><g:link action="show" id="${contratInstance.id}">${fieldValue(bean: contratInstance, field: "nom")}</g:link></td>
							
								<td>${fieldValue(bean: contratInstance, field: "locataire")}</td>
							
								<td>
									<g:each in="${contratInstance.locaux}" var="local">
										${fieldValue(bean: local, field: "nom")}<br />
									</g:each>
								</td>
							
								<td><g:formatDate date="${contratInstance.dateDebut}" type="date" style="MEDIUM" /></td>
							
								<td><g:formatDate date="${contratInstance.dateFin}" type="date" style="MEDIUM" /></td>
							
								<td><g:formatNumber number="${contratInstance.montantLoyer}" type="currency" currencyCode="EUR" /></td>
							
								<td><g:formatNumber number="${contratInstance.montantCharges}" type="currency" currencyCode="EUR" /></td>
							
								<td>${fieldValue(bean: contratInstance, field: "remarques")}</td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="${contratInstanceCount ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
