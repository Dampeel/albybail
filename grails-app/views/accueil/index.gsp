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
					<h1>Bienvenue sur AlbyBail !</h1>
					
					<p>Bonjour, vous êtes sur la page d'accueil de l'application. Vous y trouverez un menu vous permettant d'accèder aux opérations
						ou aux données. Les opérations permettent d'agir (réviser un contrat, facturer, éditer des factures). Les données permettent 
						de gérer de manipuler les objets (contrat, batiment, locataire, etc.) : création, consultation, modification ou suppression.</p>
					<p>Ci-contre l'ensemble des objets qui nécessitent votre attention.</p>
				</div>
				
				<div class="col-lg-7">
					<h3>Contrats à réviser :</h3>
				
					<g:each in="${contratAReviserList}" var="contratInstance">
						<g:link controller="contrat" action="show" id="${contratInstance.id}">
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
					
					<h3>Contrats bloqués chez le notaire :</h3>
				
					<g:each in="${contratChezNotaireList}" var="contratInstance">
						<g:link controller="contrat" action="show" id="${contratInstance.id}">
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
               </div>
           </div>
       </div>
	</body>
</html>
