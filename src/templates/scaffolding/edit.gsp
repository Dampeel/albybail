<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
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
				<g:if test="\${flash.message}">
					<div class="alert alert-info" role="status">\${flash.message}</div>
				</g:if>
				
				<g:hasErrors bean="\${${propertyName}}">
					<div class="alert alert-danger" role="status">
						<ul role="alert">
							<g:eachError bean="\${${propertyName}}" var="error">
							<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
							</g:eachError>
						</ul>
					</div>
				</g:hasErrors>
				
				<g:form url="[resource:${propertyName}, action:'update']" method="PUT" <%= multiPart ? ' enctype="multipart/form-data" ' : '' %>role="form">
					<g:hiddenField name="version" value="\${${propertyName}?.version}" />
						
					<g:render template="form"/>
					
					<div class="row form-row">
						<div class="col-sm-offset-2 col-sm-10">
					  		<g:actionSubmit class="btn btn-default btn-save" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
