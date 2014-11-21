<%@ page import="albybail.Batiment" %>

<div class="row form-row ${hasErrors(bean: batimentInstance, field: 'nom', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="batiment.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${batimentInstance?.nom}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: batimentInstance, field: 'adresse', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="batiment.adresse.label" default="Adresse" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:textArea class="form-control" name="adresse" cols="40" rows="5" maxlength="400" value="${batimentInstance?.adresse}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: batimentInstance, field: 'locaux', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="batiment.locaux.label" default="Locaux" />
	</div>
	<div class="col-sm-10 form-value">
		<ul class="list-group">
			<g:each in="${batimentInstance?.locaux?}" var="l">
			    <li class="list-group-item"><g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
			</g:each>
			<li class="list-group-item">
				<g:link controller="local" action="create" params="['batiment.id': batimentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'local.label', default: 'Local')])}</g:link>
			</li>
		</ul>
	</div>
</div>

