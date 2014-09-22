<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenue sur AlbyBail</title>
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
					<a class="navbar-brand" href="\${createLink(uri: '/')}">AlbyBail</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="controller"><g:link class="home" href="\${createLink(uri: '/')}">Opérations</g:link></li>
						<li class="controller"><g:link class="home" href="\${createLink(uri: '/')}">Données</g:link></li>
						<li><g:link class="list" controller="batiment">Batiment</g:link></li>
						<li><g:link class="list" controller="local">Local</g:link></li>
						<li><g:link class="list" controller="locataire">Locataire</g:link></li>
						<li><g:link class="list" controller="contrat">Contrat</g:link></li>
						<li><g:link class="list" controller="indice">Indice</g:link></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid" role="main">
			<h1>Bienvenue sur AlbyBail !</h1>
			<p>Vous êtes ici sur la page d'accueil de l'application. Vous y trouverez un menu vous permettant d'accèder aux opérations
				ou aux données. Les opérations regroupent les fonctionnalités qui agissent sur les objets. Les données permettent 
				de gérer ces même objets : création, consultation, modification ou suppression.</p>
		</div>
	</body>
</html>
