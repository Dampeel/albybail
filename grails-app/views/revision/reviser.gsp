<%@ page import="albybail.Contrat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenue sur AlbyBail</title>
	</head>

	<body>

		<div class="container">
			<div class="row">
				<div class="col-lg-5">
					<h3>Révision courante</h3>
				
					<div class="well">
						Révision du <strong><g:formatDate type="date" style="LONG" date="${revisionCourante?.dateDebut}" /></strong>
						au <strong><g:formatDate type="date" style="LONG" date="${revisionCourante?.dateFin}" /></strong>
						Suivant l'indice <g:link controller="indice" action="show" id="${revisionCourante?.indice?.id}">
							${revisionCourante?.indice?.trimestre.encodeAsHTML()}-${revisionCourante?.indice?.annee.encodeAsHTML()}
						</g:link>
						Montant du loyer : <g:fieldValue bean="${revisionCourante}" field="montantLoyer"/> €
						Montant des charges : <g:fieldValue bean="${revisionCourante}" field="montantCharges"/> €
						<g:if test="${revisionCourante?.remarques}">
							<g:fieldValue bean="${revisionCourante}" field="remarques"/>
						</g:if>
					</div>
				</div>
				
				<div class="col-lg-7">
					<h3>Nouvelle révision</h3>
					
					<g:if test="${flash.message}">
						<div class="alert alert-danger" role="status">${flash.message}</div>
					</g:if>
					
					<g:hasErrors bean="${revisionInstance}">
					<div class="alert alert-info" role="status">
						<ul role="alert">
							<g:eachError bean="${revisionInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
					</div>
					</g:hasErrors>
					
					<g:form url="[resource:revisionInstance, action:'update']" method="PUT" role="form">
					<g:hiddenField name="version" value="${revisionInstance?.version}" />
						
					<g:render template="form"/>
					
					<div class="row form-row">
						<div class="col-sm-offset-2 col-sm-10">
					  		<g:actionSubmit class="btn btn-default btn-save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
