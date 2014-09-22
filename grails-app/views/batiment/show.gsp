
<%@ page import="albybail.Batiment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batiment.label', default: 'Batiment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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
						<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
						<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<div class="panel panel-default">
  				<div class="panel-body">
					<ol class="property-list">
					
						<g:if test="${batimentInstance?.nom}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="batiment.nom.label" default="Nom" />
							</span>
							<span class="property-value">
							
								<g:fieldValue bean="${batimentInstance}" field="nom"/>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${batimentInstance?.adresse}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="batiment.adresse.label" default="Adresse" />
							</span>
							<span class="property-value">
							
								<g:fieldValue bean="${batimentInstance}" field="adresse"/>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${batimentInstance?.locaux}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="batiment.locaux.label" default="Locaux" />
							</span>
							<span class="property-value">
							
								<g:each in="${batimentInstance.locaux}" var="l">
								<g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link>
								</g:each>
							
							</span>
						</li>
						</g:if>
					
					</ol>
				</div>
			</div>
			
			<g:form class="form-horizontal" url="[resource:batimentInstance, action:'delete']" method="DELETE">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<g:link class="btn btn-default edit" action="edit" resource="${batimentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
