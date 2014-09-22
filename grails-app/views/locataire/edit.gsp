<%@ page import="albybail.Locataire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'locataire.label', default: 'Locataire')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:hasErrors bean="${locataireInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${locataireInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form class="form-horizontal" url="[resource:locataireInstance, action:'update']" method="PUT"  role="form">
				<fieldset>
					<g:hiddenField name="version" value="${locataireInstance?.version}" />
					
					<g:render template="form"/>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
					  		<g:actionSubmit class="btn btn-default save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
