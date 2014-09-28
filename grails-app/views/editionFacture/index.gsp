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
               <div class="col-lg-12"><h1>Edition des factures</h1>

					<p>Cette fonction vous permet de générer les factures pour un mois donné.</p>
					
					<g:form url="[action:'generate']" method="POST" role="form">
						<div class="row form-row ${hasErrors(bean: contratInstance, field: 'locataire', 'error')} required">
							<div class="col-sm-2 form-label">
								<g:message code="contrat.locataire.label" default="Locataire" />
								<span class="required-indicator">*</span>
							</div>
							<div class="col-sm-10 form-value">
								<g:select class="form-control" id="locataire" name="locataire.id" from="${albybail.Locataire.list()}" optionKey="id" required="" value="${contratInstance?.locataire?.id}"/>
						
							</div>
						</div>
					</g:form>
               </div>
               
		
				<div class="page-content">
					
				</div>
           </div>
       </div>
	</body>
</html>
