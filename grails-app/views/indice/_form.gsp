<%@ page import="albybail.Indice" %>



<div class="form-group ${hasErrors(bean: indiceInstance, field: 'date', 'error')} required">
	<label for="date" class="col-sm-2 control-label">
		<g:message code="indice.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control datepicker" name="date" precision="day"  value="${indiceInstance?.date}"  />

	</div>
</div>

<div class="form-group ${hasErrors(bean: indiceInstance, field: 'valeur', 'error')} ">
	<label for="valeur" class="col-sm-2 control-label">
		<g:message code="indice.valeur.label" default="Valeur" />
		
	</label>
	<div class="col-sm-10">
		<g:field class="form-control" name="valeur" value="${fieldValue(bean: indiceInstance, field: 'valeur')}"/>

	</div>
</div>

