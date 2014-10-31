<%@ page import="albybail.Revision" %>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'dateDebut', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.dateDebut.label" default="Date Debut" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control datepicker" name="dateDebut" precision="day"  value="${formatDate(format:'dd/MM/yyyy',date:revisionInstance?.dateDebut)}"  />

	</div>
</div>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'dateFin', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.dateFin.label" default="Date Fin" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control datepicker" name="dateFin" precision="day"  value="${formatDate(format:'dd/MM/yyyy',date:revisionInstance?.dateFin)}"  />

	</div>
</div>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'indice', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.indice.label" default="Indice" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:field class="form-control" name="indice" value="${fieldValue(bean: revisionInstance, field: 'indice')}" required=""/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'montantLoyer', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.montantLoyer.label" default="Montant Loyer" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:field class="form-control" name="montantLoyer" value="${fieldValue(bean: revisionInstance, field: 'montantLoyer')}" required=""/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'montantCharges', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.montantCharges.label" default="Montant Charges" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:field class="form-control" name="montantCharges" value="${fieldValue(bean: revisionInstance, field: 'montantCharges')}" required=""/>

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

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'aReguler', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="revision.aReguler.label" default="A Reguler" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:checkBox name="aReguler" value="${revisionInstance?.aReguler}" />

	</div>
</div>

<div class="row form-row ${hasErrors(bean: revisionInstance, field: 'contrat', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="revision.contrat.label" default="Contrat" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:select class="form-control" id="contrat" name="contrat.id" from="${albybail.Contrat.list()}" optionKey="id" required="" value="${revisionInstance?.contrat?.id}"/>

	</div>
</div>

