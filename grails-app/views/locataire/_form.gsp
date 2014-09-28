<%@ page import="albybail.Locataire" %>



<div class="row form-row ${hasErrors(bean: locataireInstance, field: 'nom', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="locataire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${locataireInstance?.nom}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: locataireInstance, field: 'adresse', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="locataire.adresse.label" default="Adresse" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:textArea class="form-control" name="adresse" cols="40" rows="5" maxlength="400" value="${locataireInstance?.adresse}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: locataireInstance, field: 'remarques', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="locataire.remarques.label" default="Remarques" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="remarques" value="${locataireInstance?.remarques}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: locataireInstance, field: 'contrats', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="locataire.contrats.label" default="Contrats" />
		
	</div>
	<div class="col-sm-10 form-value">
		
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

