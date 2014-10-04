<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="AlbyBail"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
		<asset:stylesheet src="application.css"/>
		<g:layoutHead/>
	</head>
	
	<body>
	        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	            <div class="container">
		            <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
		                    <span class="sr-only">Menu</span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </button>
		                <g:link class="navbar-brand" controller="accueil">AlbyBail</g:link>
		            </div>
		            
		            <div class="collapse navbar-collapse navbar-ex1-collapse">
		                <ul class="nav navbar-nav side-nav">
		                    <li>
		                        <g:link class="list" controller="contrat">Contrats</g:link>
		                    </li>
		                    <li>
		                        <g:link class="list" controller="revision" action="listAReviser">Revisions</g:link>
		                    </li>
		                    <li>
		                        <g:link class="list" controller="editionFacture">Edition des factures</g:link>
		                    </li>
		                    <li>
	                            <g:link class="list" controller="batiment">Bâtiments</g:link>
	                        </li>
	                        <li>
	                            <g:link class="list" controller="local">Locaux</g:link>
	                        </li>
	                        <li>
	                            <g:link class="list" controller="locataire">Locataires</g:link>
	                        </li>
	                        <li>
	                            <g:link class="list" controller="indice">Indices INSEE</g:link>
	                        </li>
		                </ul>
		            </div>
		        </div>
	        </nav>
	        
			<g:layoutBody/>

		<asset:javascript src="application.js"/>
	</body>
</html>
