
<%@ page import="albybail.Contrat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrat.label', default: 'Contrat')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${createLink(uri: '/')}">AlbyBail</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
						<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
						<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<div class="panel panel-default">
  				<div class="panel-body">
					<ol class="property-list">
					
						<g:if test="${contratInstance?.nom}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.nom.label" default="Nom" />
							</span>
							<span class="property-value">
							
								<g:fieldValue bean="${contratInstance}" field="nom"/>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.dateFin}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.dateFin.label" default="Date Fin" />
							</span>
							<span class="property-value">
							
								<g:formatDate date="${contratInstance?.dateFin}" />
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.remarques}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.remarques.label" default="Remarques" />
							</span>
							<span class="property-value">
							
								<g:fieldValue bean="${contratInstance}" field="remarques"/>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.chezNotaire}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.chezNotaire.label" default="Chez Notaire" />
							</span>
							<span class="property-value">
							
								<g:formatBoolean boolean="${contratInstance?.chezNotaire}" />
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.dateDebut}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.dateDebut.label" default="Date Debut" />
							</span>
							<span class="property-value">
							
								<g:formatDate date="${contratInstance?.dateDebut}" />
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.dureeRevision}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.dureeRevision.label" default="Duree Revision" />
							</span>
							<span class="property-value">
							
								<g:fieldValue bean="${contratInstance}" field="dureeRevision"/>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.editions}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.editions.label" default="Editions" />
							</span>
							<span class="property-value">
							
								<g:each in="${contratInstance.editions}" var="e">
								<g:link controller="edition" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link>
								</g:each>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.estTermine}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.estTermine.label" default="Est Termine" />
							</span>
							<span class="property-value">
							
								<g:formatBoolean boolean="${contratInstance?.estTermine}" />
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.facturables}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.facturables.label" default="Facturables" />
							</span>
							<span class="property-value">
							
								<g:each in="${contratInstance.facturables}" var="f">
								<g:link controller="facturable" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link>
								</g:each>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.locataire}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.locataire.label" default="Locataire" />
							</span>
							<span class="property-value">
							
								<g:link controller="locataire" action="show" id="${contratInstance?.locataire?.id}">${contratInstance?.locataire?.encodeAsHTML()}</g:link>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.locaux}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.locaux.label" default="Locaux" />
							</span>
							<span class="property-value">
							
								<g:each in="${contratInstance.locaux}" var="l">
								<g:link controller="local" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link>
								</g:each>
							
							</span>
						</li>
						</g:if>
					
						<g:if test="${contratInstance?.revisions}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="contrat.revisions.label" default="Revisions" />
							</span>
							<span class="property-value">
							
								<g:each in="${contratInstance.revisions}" var="r">
								<g:link controller="revision" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link>
								</g:each>
							
							</span>
						</li>
						</g:if>
					
					</ol>
				</div>
			</div>
			
			<g:form class="form-horizontal" url="[resource:contratInstance, action:'delete']" method="DELETE">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<g:link class="btn btn-default edit" action="edit" resource="${contratInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit  class="btn btn-default delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
