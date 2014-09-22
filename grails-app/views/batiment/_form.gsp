<%@ page import="albybail.Batiment" %>



<div class="form-group ${hasErrors(bean: batimentInstance, field: 'nom', 'error')} required">
	<label for="nom" class="col-sm-2 control-label">
		<g:message code="batiment.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${batimentInstance?.nom}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: batimentInstance, field: 'adresse', 'error')} ">
	<label for="adresse" class="col-sm-2 control-label">
		<g:message code="batiment.adresse.label" default="Adresse" />
		
	</label>
	<div class="col-sm-10">
		<g:textArea class="form-control" name="adresse" cols="40" rows="5" maxlength="400" value="${batimentInstance?.adresse}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: batimentInstance, field: 'locaux', 'error')} ">
	<label for="locaux" class="col-sm-2 control-label">
		<g:message code="batiment.locaux.label" default="Locaux" />
		
	</label>
	<div class="col-sm-10">
		
<ul>
<g:each in="${batimentInstance?.locaux?}" var="l">
    <li><g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li>
<g:link controller="local" action="create" params="['batiment.id': batimentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'local.label', default: 'Local')])}</g:link>
</li>
</ul>


	</div>
</div>

