<%@ page import="albybail.Local" %>



<div class="row form-row ${hasErrors(bean: localInstance, field: 'nom', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="local.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${localInstance?.nom}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: localInstance, field: 'surface', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="local.surface.label" default="Surface" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:field class="form-control" name="surface" type="number" min="0" value="${localInstance.surface}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: localInstance, field: 'batiment', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="local.batiment.label" default="Batiment" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:select class="form-control" id="batiment" name="batiment.id" from="${albybail.Batiment.list()}" optionKey="id" required="" value="${localInstance?.batiment?.id}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: localInstance, field: 'contrats', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="local.contrats.label" default="Contrats" />
		
	</div>
	<div class="col-sm-10 form-value">
		

	</div>
</div>

