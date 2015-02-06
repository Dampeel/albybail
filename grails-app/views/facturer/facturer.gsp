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
							
								<g:sortableColumn property="date" title="${message(code: 'facturable.date.label', default: 'Date')}" />
							
								<g:sortableColumn property="description" title="${message(code: 'facturable.description.label', default: 'Description')}" />
							
								<g:sortableColumn property="valeur" title="${message(code: 'facturable.valeur.label', default: 'Valeur')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${facturables}" status="i" var="facturable">
							<tr>
							
								<td><g:link action="show" id="${facturable.id}"><g:formatDate date="${facturable.date}" type="date" style="MEDIUM" /></g:link></td>
							
								<td>${fieldValue(bean: facturable, field: "description")}</td>
							
								<td><g:formatNumber number="${facturable.valeur}" type="currency" currencyCode="EUR" /></td>
								
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
