<%@ page import="albybail.Contrat" %>



<div class="form-group ${hasErrors(bean: contratInstance, field: 'nom', 'error')} required">
	<label for="nom" class="col-sm-2 control-label">
		<g:message code="contrat.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${contratInstance?.nom}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'dateFin', 'error')} required">
	<label for="dateFin" class="col-sm-2 control-label">
		<g:message code="contrat.dateFin.label" default="Date Fin" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control datepicker" name="dateFin" precision="day"  value="${contratInstance?.dateFin}"  />

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'remarques', 'error')} ">
	<label for="remarques" class="col-sm-2 control-label">
		<g:message code="contrat.remarques.label" default="Remarques" />
		
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="remarques" value="${contratInstance?.remarques}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'chezNotaire', 'error')} ">
	<label for="chezNotaire" class="col-sm-2 control-label">
		<g:message code="contrat.chezNotaire.label" default="Chez Notaire" />
		
	</label>
	<div class="col-sm-10">
		<g:checkBox name="chezNotaire" value="${contratInstance?.chezNotaire}" />

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'dateDebut', 'error')} required">
	<label for="dateDebut" class="col-sm-2 control-label">
		<g:message code="contrat.dateDebut.label" default="Date Debut" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control datepicker" name="dateDebut" precision="day"  value="${contratInstance?.dateDebut}"  />

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'dureeRevision', 'error')} required">
	<label for="dureeRevision" class="col-sm-2 control-label">
		<g:message code="contrat.dureeRevision.label" default="Duree Revision" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:field class="form-control" name="dureeRevision" type="number" value="${contratInstance.dureeRevision}" required=""/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'editions', 'error')} ">
	<label for="editions" class="col-sm-2 control-label">
		<g:message code="contrat.editions.label" default="Editions" />
		
	</label>
	<div class="col-sm-10">
		
<ul>
<g:each in="${contratInstance?.editions?}" var="e">
    <li><g:link controller="edition" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li>
<g:link controller="edition" action="create" params="['contrat.id': contratInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'edition.label', default: 'Edition')])}</g:link>
</li>
</ul>


	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'estTermine', 'error')} ">
	<label for="estTermine" class="col-sm-2 control-label">
		<g:message code="contrat.estTermine.label" default="Est Termine" />
		
	</label>
	<div class="col-sm-10">
		<g:checkBox name="estTermine" value="${contratInstance?.estTermine}" />

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'facturables', 'error')} ">
	<label for="facturables" class="col-sm-2 control-label">
		<g:message code="contrat.facturables.label" default="Facturables" />
		
	</label>
	<div class="col-sm-10">
		
<ul>
<g:each in="${contratInstance?.facturables?}" var="f">
    <li><g:link controller="facturable" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li>
<g:link controller="facturable" action="create" params="['contrat.id': contratInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'facturable.label', default: 'Facturable')])}</g:link>
</li>
</ul>


	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'locataire', 'error')} required">
	<label for="locataire" class="col-sm-2 control-label">
		<g:message code="contrat.locataire.label" default="Locataire" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:select class="form-control" id="locataire" name="locataire.id" from="${albybail.Locataire.list()}" optionKey="id" required="" value="${contratInstance?.locataire?.id}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'locaux', 'error')} ">
	<label for="locaux" class="col-sm-2 control-label">
		<g:message code="contrat.locaux.label" default="Locaux" />
		
	</label>
	<div class="col-sm-10">
		<g:select class="form-control" name="locaux" from="${albybail.Local.list()}" multiple="multiple" optionKey="id" size="5" value="${contratInstance?.locaux*.id}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: contratInstance, field: 'revisions', 'error')} ">
	<label for="revisions" class="col-sm-2 control-label">
		<g:message code="contrat.revisions.label" default="Revisions" />
		
	</label>
	<div class="col-sm-10">
		
<ul>
<g:each in="${contratInstance?.revisions?}" var="r">
    <li><g:link controller="revision" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li>
<g:link controller="revision" action="create" params="['contrat.id': contratInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'revision.label', default: 'Revision')])}</g:link>
</li>
</ul>


	</div>
</div>

