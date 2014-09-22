<%@ page import="albybail.Local" %>



<div class="form-group ${hasErrors(bean: localInstance, field: 'nom', 'error')} required">
	<label for="nom" class="col-sm-2 control-label">
		<g:message code="local.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${localInstance?.nom}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: localInstance, field: 'surface', 'error')} ">
	<label for="surface" class="col-sm-2 control-label">
		<g:message code="local.surface.label" default="Surface" />
		
	</label>
	<div class="col-sm-10">
		<g:field class="form-control" name="surface" type="number" min="0" value="${localInstance.surface}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: localInstance, field: 'batiment', 'error')} required">
	<label for="batiment" class="col-sm-2 control-label">
		<g:message code="local.batiment.label" default="Batiment" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:select class="form-control" id="batiment" name="batiment.id" from="${albybail.Batiment.list()}" optionKey="id" required="" value="${localInstance?.batiment?.id}"/>

	</div>
</div>

<div class="form-group ${hasErrors(bean: localInstance, field: 'contrats', 'error')} ">
	<label for="contrats" class="col-sm-2 control-label">
		<g:message code="local.contrats.label" default="Contrats" />
		
	</label>
	<div class="col-sm-10">
		

	</div>
</div>

