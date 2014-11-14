<%@ page import="albybail.Revision" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'revision.label', default: 'Facturable')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="container">
			<div class="row">
	   			<div class="col-sm-7">
					<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
				</div>
				<div class="col-sm-5">
					<div class="h1-buttons">
						<g:link class="btn btn-default list" action="index">Retour à la liste</g:link>
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
							
								<g:sortableColumn property="dateFin" title="${message(code: 'facturable.dateFin.label', default: 'Date Fin')}" />
							
								<g:sortableColumn property="valeur" title="${message(code: 'facturable.valeur.label', default: 'Valeur')}" />
							
								<g:sortableColumn property="description" title="${message(code: 'facturable.description.label', default: 'Description')}" />
							
								<th><g:message code="facturable.contrat.label" default="Contrat" /></th>
							
								<g:sortableColumn property="dateDebut" title="${message(code: 'facturable.dateDebut.label', default: 'Date Debut')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${facturables}" status="i" var="facturable">
							<tr>
							
								<td><g:link action="show" id="${facturable.id}">${fieldValue(bean: facturable, field: "description")}</g:link></td>
							
								<td>${fieldValue(bean: facturable, field: "valeur")}</td>
							
								<td>${fieldValue(bean: facturable, field: "contrat")}</td>
							
								<td><g:formatDate date="${facturable.date}" /></td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
