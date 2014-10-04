
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
							
								<g:sortableColumn property="dateFin" title="${message(code: 'revision.dateFin.label', default: 'Date Fin')}" />
							
								<th><g:message code="revision.indice.label" default="Indice" /></th>
							
								<g:sortableColumn property="montantLoyer" title="${message(code: 'revision.montantLoyer.label', default: 'Montant Loyer')}" />
							
								<g:sortableColumn property="montantCharges" title="${message(code: 'revision.montantCharges.label', default: 'Montant Charges')}" />
							
								<g:sortableColumn property="remarques" title="${message(code: 'revision.remarques.label', default: 'Remarques')}" />
							
								<g:sortableColumn property="aReguler" title="${message(code: 'revision.aReguler.label', default: 'A Reguler')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${revisionInstanceList}" status="i" var="revisionInstance">
							<tr>
							
								<td><g:link action="show" id="${revisionInstance.id}">${fieldValue(bean: revisionInstance, field: "dateFin")}</g:link></td>
							
								<td>${fieldValue(bean: revisionInstance, field: "indice")}</td>
							
								<td>${fieldValue(bean: revisionInstance, field: "montantLoyer")}</td>
							
								<td>${fieldValue(bean: revisionInstance, field: "montantCharges")}</td>
							
								<td>${fieldValue(bean: revisionInstance, field: "remarques")}</td>
							
								<td><g:formatBoolean boolean="${revisionInstance.aReguler}" /></td>
							
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
