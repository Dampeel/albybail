
<%@ page import="albybail.Contrat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrat.label', default: 'Contrat')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${createLink(uri: '/')}">AlbyBail</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
						<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
		
		<div class="container-fluid" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
						
							<g:sortableColumn property="nom" title="${message(code: 'contrat.nom.label', default: 'Nom')}" />
						
							<g:sortableColumn property="dateFin" title="${message(code: 'contrat.dateFin.label', default: 'Date Fin')}" />
						
							<g:sortableColumn property="remarques" title="${message(code: 'contrat.remarques.label', default: 'Remarques')}" />
						
							<g:sortableColumn property="chezNotaire" title="${message(code: 'contrat.chezNotaire.label', default: 'Chez Notaire')}" />
						
							<g:sortableColumn property="dateDebut" title="${message(code: 'contrat.dateDebut.label', default: 'Date Debut')}" />
						
							<g:sortableColumn property="dureeRevision" title="${message(code: 'contrat.dureeRevision.label', default: 'Duree Revision')}" />
						
						</tr>
					</thead>
					
					<tbody>
					<g:each in="${contratInstanceList}" status="i" var="contratInstance">
						<tr>
						
							<td><g:link action="show" id="${contratInstance.id}">${fieldValue(bean: contratInstance, field: "nom")}</g:link></td>
						
							<td><g:formatDate date="${contratInstance.dateFin}" /></td>
						
							<td>${fieldValue(bean: contratInstance, field: "remarques")}</td>
						
							<td><g:formatBoolean boolean="${contratInstance.chezNotaire}" /></td>
						
							<td><g:formatDate date="${contratInstance.dateDebut}" /></td>
						
							<td>${fieldValue(bean: contratInstance, field: "dureeRevision")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			
			<ul class="pagination">
				<g:paginate total="${contratInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
