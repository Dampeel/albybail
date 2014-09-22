<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="\${createLink(uri: '/')}">AlbyBail</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
						<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
						<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			
			<g:if test="\${flash.message}">
				<div class="message" role="status">\${flash.message}</div>
			</g:if>
			
			<div class="panel panel-default">
  				<div class="panel-body">
					<ol class="property-list">
					<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
						allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
						props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && (domainClass.constrainedProperties[it.name] ? domainClass.constrainedProperties[it.name].display : true) }
						Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
						props.each { p -> %>
						<g:if test="\${${propertyName}?.${p.name}}">
						<li class="fieldcontain">
		    				<span class="property-label">
								<g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />
							</span>
							<span class="property-value">
							<%  if (p.isEnum()) { %>
								<g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>
							<%  } else if (p.oneToMany || p.manyToMany) { %>
								<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
								<g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link>
								</g:each>
							<%  } else if (p.manyToOne || p.oneToOne) { %>
								<g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link>
							<%  } else if (p.type == Boolean || p.type == boolean) { %>
								<g:formatBoolean boolean="\${${propertyName}?.${p.name}}" />
							<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
								<g:formatDate date="\${${propertyName}?.${p.name}}" />
							<%  } else if (!p.type.isArray()) { %>
								<g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>
							<%  } %>
							</span>
						</li>
						</g:if>
					<%  } %>
					</ol>
				</div>
			</div>
			
			<g:form class="form-horizontal" url="[resource:${propertyName}, action:'delete']" method="DELETE">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<g:link class="btn btn-default edit" action="edit" resource="\${${propertyName}}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit  class="btn btn-default delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
