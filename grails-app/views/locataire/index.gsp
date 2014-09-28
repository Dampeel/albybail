
<%@ page import="albybail.Locataire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'locataire.label', default: 'Locataire')}" />
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
							
								<g:sortableColumn property="nom" title="${message(code: 'locataire.nom.label', default: 'Nom')}" />
							
								<g:sortableColumn property="adresse" title="${message(code: 'locataire.adresse.label', default: 'Adresse')}" />
							
								<g:sortableColumn property="remarques" title="${message(code: 'locataire.remarques.label', default: 'Remarques')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${locataireInstanceList}" status="i" var="locataireInstance">
							<tr>
							
								<td><g:link action="show" id="${locataireInstance.id}">${fieldValue(bean: locataireInstance, field: "nom")}</g:link></td>
							
								<td>${fieldValue(bean: locataireInstance, field: "adresse")}</td>
							
								<td>${fieldValue(bean: locataireInstance, field: "remarques")}</td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="${locataireInstanceCount ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
