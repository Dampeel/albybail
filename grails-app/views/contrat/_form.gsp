<%@ page import="albybail.Contrat" %>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'nom', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="nom" maxlength="100" required="" value="${contratInstance?.nom}"/>
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'dateDebut', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.dateDebut.label" default="Date Debut" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control datepicker" name="dateDebut" precision="day" value="${formatDate(format:'dd/MM/yyyy',date:contratInstance?.dateDebut)}"  />
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'dateFin', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.dateFin.label" default="Date Fin" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control datepicker" name="dateFin" precision="day" value="${formatDate(format:'dd/MM/yyyy',date:contratInstance?.dateFin)}"  />
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'montantLoyer', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.montantLoyer.label" default="Montant Loyer" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="montantLoyer" value="${fieldValue(bean: contratInstance, field: 'montantLoyer')}" required=""/>
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'montantCharges', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.montantCharges.label" default="Montant Charges" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="montantCharges" value="${fieldValue(bean: contratInstance, field: 'montantCharges')}" required=""/>
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'dureeRevision', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.dureeRevision.label" default="Duree Revision" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:field class="form-control" name="dureeRevision" type="number" value="${contratInstance.dureeRevision}" required=""/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'locataire', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.locataire.label" default="Locataire" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:select class="form-control" id="locataire" name="locataire.id" from="${albybail.Locataire.list()}" optionKey="id" required="" value="${contratInstance?.locataire?.id}"/>
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'locaux', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.locaux.label" default="Locaux" />
	</div>
	<div class="col-sm-10 form-value">
		<g:select class="form-control" name="locaux" from="${albybail.Local.list()}" multiple="multiple" optionKey="id" size="5" value="${contratInstance?.locaux*.id}"/>
	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'profil', 'error')} required">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.profil.label" default="Profil" />
		<span class="required-indicator">*</span>
	</div>
	<div class="col-sm-10 form-value">
		<g:select class="form-control" id="profil" name="profil.id" from="${albybail.Profil.list()}" optionKey="id" required="" value="${contratInstance?.profil?.id}"/>

	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'chezNotaire', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.chezNotaire.label" default="Chez Notaire" />
		
	</div>
	<div class="col-sm-10 form-value">
		<g:checkBox name="chezNotaire" value="${contratInstance?.chezNotaire}" />

	</div>
</div>

<div class="row form-row ${hasErrors(bean: contratInstance, field: 'remarques', 'error')} ">
	<div class="col-sm-2 form-label">
		<g:message code="contrat.remarques.label" default="Remarques" />
	</div>
	<div class="col-sm-10 form-value">
		<g:textField class="form-control" name="remarques" value="${contratInstance?.remarques}"/>
	</div>
</div>

