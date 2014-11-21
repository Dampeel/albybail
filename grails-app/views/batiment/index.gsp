
<%@ page import="albybail.Batiment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batiment.label', default: 'Batiment')}" />
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
							
								<g:sortableColumn property="nom" title="${message(code: 'batiment.nom.label', default: 'Nom')}" />
							
								<g:sortableColumn property="adresse" title="${message(code: 'batiment.adresse.label', default: 'Adresse')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${batimentInstanceList}" status="i" var="batimentInstance">
							<tr>
							
								<td><g:link action="show" id="${batimentInstance.id}">${fieldValue(bean: batimentInstance, field: "nom")}</g:link></td>
							
								<td>${fieldValue(bean: batimentInstance, field: "adresse")}</td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="${batimentInstanceCount ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
