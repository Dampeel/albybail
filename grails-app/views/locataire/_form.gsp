<%@ page import="albybail.Locataire" %>



<div class="form-group ${hasErrors(bean: locataireInstance, field: 'nom', 'error')} required">
	<label for="nom" class="col-sm-2 control-label">
		<g:message code="locataire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${locataireInstance?.nom}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: locataireInstance, field: 'adresse', 'error')} ">
	<label for="adresse" class="col-sm-2 control-label">
		<g:message code="locataire.adresse.label" default="Adresse" />
		
	</label>
	<div class="col-sm-10">
		<g:textArea class="form-control" name="adresse" cols="40" rows="5" maxlength="400" value="${locataireInstance?.adresse}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: locataireInstance, field: 'remarques', 'error')} ">
	<label for="remarques" class="col-sm-2 control-label">
		<g:message code="locataire.remarques.label" default="Remarques" />
		
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="remarques" value="${locataireInstance?.remarques}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: locataireInstance, field: 'contrats', 'error')} ">
	<label for="contrats" class="col-sm-2 control-label">
		<g:message code="locataire.contrats.label" default="Contrats" />
		
	</label>
	<div class="col-sm-10">
		
<ul>
<g:each in="${locataireInstance?.contrats?}" var="c">
    <li><g:link controller="contrat" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li>
<g:link controller="contrat" action="create" params="['locataire.id': locataireInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contrat.label', default: 'Contrat')])}</g:link>
</li>
</ul>


	</div>
</div>

