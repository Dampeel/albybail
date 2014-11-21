
<%@ page import="albybail.Local" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'local.label', default: 'Local')}" />
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
							
								<g:sortableColumn property="nom" title="${message(code: 'local.nom.label', default: 'Nom')}" />
							
								<g:sortableColumn property="surface" title="${message(code: 'local.surface.label', default: 'Surface')}" />
								
								<g:sortableColumn property="batiment" title="${message(code: 'local.batiment.label', default: 'Batiment')}" />
							
							</tr>
						</thead>
						
						<tbody>
						<g:each in="${localInstanceList}" status="i" var="localInstance">
							<tr>
							
								<td><g:link action="show" id="${localInstance.id}">${fieldValue(bean: localInstance, field: "nom")}</g:link></td>
							
								<td>${fieldValue(bean: localInstance, field: "surface")}</td>
							
								<td>${fieldValue(bean: localInstance, field: "batiment")}</td>
							
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="${localInstanceCount ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
