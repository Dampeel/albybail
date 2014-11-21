<%@ page import="albybail.Batiment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batiment.label', default: 'Batiment')}" />
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
						<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label"/></g:link>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				<g:hasErrors bean="${batimentInstance}">
					<div class="alert alert-danger" role="status">
						<ul role="alert">
							<g:eachError bean="${batimentInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
					</div>
				</g:hasErrors>
				
				<g:form url="[resource:batimentInstance, action:'update']" method="PUT" role="form">
					<g:hiddenField name="version" value="${batimentInstance?.version}" />
						
					<g:render template="form"/>
					
					<div class="row form-row">
						<div class="col-sm-offset-2 col-sm-10">
					  		<g:actionSubmit class="btn btn-default btn-save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
