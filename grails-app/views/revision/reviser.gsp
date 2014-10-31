<%@ page import="albybail.Revision" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'revision.label', default: 'Revision')}" />
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
						<g:link class="btn btn-default list" action="listeAReviser">Retour à la liste des révisions</g:link>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				<g:hasErrors bean="${revisionInstance}">
					<div class="alert alert-danger" role="status">
						<ul role="alert">
							<g:eachError bean="${revisionInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
					</div>
				</g:hasErrors>
				
				<g:form url="[resource:revisionInstance, action:'sauver']" role="form">
	  				<g:hiddenField name="contrat" value="${revisionInstance?.contrat?.id}" />
	  				<g:hiddenField name="montantLoyer" value="${formatNumber(number: revisionInstance?.montantLoyer)}" />
	  				<g:hiddenField name="montantCharges" value="${formatNumber(number: revisionInstance?.montantCharges)}" />
	  				
					<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'dateDebut', 'error')} required">
						<div class="col-sm-2 form-label">
							<g:message code="revision.dateDebut.label" default="Date Debut" />
							<span class="required-indicator">*</span>
						</div>
						<div class="col-sm-10 form-value">
							<g:textField class="form-control datepicker" name="dateDebut" precision="day" value="${formatDate(format:'dd/MM/yyyy',date:revisionInstance?.dateDebut)}"  />
						</div>
					</div>
					
					<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'dateFin', 'error')} required">
						<div class="col-sm-2 form-label">
							<g:message code="revision.dateFin.label" default="Date Fin" />
							<span class="required-indicator">*</span>
						</div>
						<div class="col-sm-10 form-value">
							<g:textField class="form-control datepicker" name="dateFin" precision="day" value="${formatDate(format:'dd/MM/yyyy',date:revisionInstance?.dateFin)}"  />
						</div>
					</div>
	
					<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'indice', 'error')} required">
						<div class="col-sm-2 form-label">
							<g:message code="revision.indice.label" default="Indice" />
							<span class="required-indicator">*</span>
						</div>
						<div class="col-sm-10 form-value">
							<g:textField class="form-control" name="indice" value="${fieldValue(bean: revisionInstance, field: 'indice')}" required=""/>
						</div>
					</div>

					<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'remarques', 'error')} ">
						<div class="col-sm-2 form-label">
							<g:message code="revision.remarques.label" default="Remarques" />
							
						</div>
						<div class="col-sm-10 form-value">
							<g:textField class="form-control" name="remarques" value="${revisionInstance?.remarques}"/>
						</div>
					</div>
						
					<div class="row form-row">
						<div class="col-sm-offset-2 col-sm-10">
							<g:submitButton name="create" class="btn btn-default btn-save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
