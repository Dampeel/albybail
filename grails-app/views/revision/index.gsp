
<%@ page import="albybail.Revision" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'revision.label', default: 'Revision')}" />
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
							
								<g:sortableColumn property="contrat" title="${message(code: 'revision.contrat.label', default: 'Contrat')}" />
							
								<g:sortableColumn property="dateDebut" title="${message(code: 'revision.dateDebut.label', default: 'Date Debut')}" />
							
								<g:sortableColumn property="dateFin" title="${message(code: 'revision.dateFin.label', default: 'Date Fin')}" />
							
								<g:sortableColumn property="indice" title="${message(code: 'revision.indice.label', default: 'Indice')}" />
							
								<g:sortableColumn property="montantLoyer" title="${message(code: 'revision.montantLoyer.label', default: 'Montant Loyer')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${revisionInstanceList}" status="i" var="revisionInstance">
							<tr>
				
								<td><g:link action="show" id="${revisionInstance.id}">${fieldValue(bean: revisionInstance, field: "contrat")}</g:link></td>
							
								<td><g:formatDate date="${revisionInstance.dateDebut}" type="date" style="MEDIUM" /></td>
								
								<td><g:formatDate date="${revisionInstance.dateFin}" type="date" style="MEDIUM" /></td>
							
								<td>${fieldValue(bean: revisionInstance, field: "indice")}</td>
							
								<td><g:formatNumber number="${revisionInstance.montantLoyer}" type="currency" currencyCode="EUR" /></td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="${revisionInstanceCount ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
