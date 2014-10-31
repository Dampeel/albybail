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
				<div class="col-lg-6">
					<h1>Contrats à réviser</h1>
					
					<p>Voici la liste des contrats à réviser. 
					Vous devez cliquer sur chacun d'entre eux pour visualiser les conditions de révision.<br/>
					Si un calcul ne vous semble pas correct, merci de contacter un administrateur. <br/>
					Si tout vous semble correct, vous pouvez valider en cliquant sur "Enregister".</p>
				</div>
				
				<div class="col-lg-6">
					<h3>Contrats à réviser :</h3>
				
					<g:if test="${contratAReviserList}">
						<g:each in="${contratAReviserList}" var="contratInstance">
							<g:link controller="revision" action="reviser" params="[contratId: contratInstance.id]">
							<div class="well">
								${contratInstance.nom} avec <strong>${contratInstance.locataire?.nom}</strong>
								<g:if test="${contratInstance.locaux.size()==1}">
									pour le local <strong>${contratInstance.locaux[0].nom}</strong> [${contratInstance.locaux[0].batiment?.nom}]<br/>
								</g:if>
								<g:else>
									pour les locaux
									<g:each in="${contratInstance.locaux}" var="local">
										<strong>${local.nom}</strong> [${local.batiment?.nom}] 
									</g:each><br/>
								</g:else>
								
								Contrat signé le <strong><g:formatDate type="date" style="LONG" date="${contratInstance.dateDebut}" /></strong>. 
								Fin du contrat le <strong><g:formatDate type="date" style="LONG" date="${contratInstance.dateFin}" /></strong>.<br/>
								Révision du prix tous les <strong>${contratInstance.dureeRevision}</strong> ans.<br/>
								<g:if test="${contratInstance.remarques}">
									Remarques : <strong>${contratInstance.remarques}</strong>.
								</g:if>
							</div>
							</g:link>
						</g:each>
					</g:if>
					<g:else>
						<div class="well">
							Pas de contrats à réviser.
						</div>
					</g:else>
               </div>
           </div>
       </div>
	</body>
</html>
