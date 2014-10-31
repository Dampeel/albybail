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
		<div class="container">
			<div class="row">
	   			<div class="col-sm-7">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>
				<div class="col-sm-5">
					<div class="h1-buttons">
						<g:form url="[resource:${propertyName}, action:'delete']" method="DELETE">
							<g:link class="btn btn-default list" action="index"><g:message code="default.button.list.label" /></g:link>
							<g:link class="btn btn-default edit" action="edit" resource="\${${propertyName}}"><g:message code="default.button.edit.label" /></g:link>
							<g:actionSubmit  class="btn btn-default delete" action="delete" value="\${message(code: 'default.button.delete.label')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</g:form>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="\${flash.message}">
					<div class="alert alert-info" role="status">\${flash.message}</div>
				</g:if>
				
				<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
					allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
					props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && (domainClass.constrainedProperties[it.name] ? domainClass.constrainedProperties[it.name].display : true) }
					Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
					props.each { p -> %>
					<g:if test="\${${propertyName}?.${p.name}}">
					<div class="row show-row">
	    				<div class="show-label col-sm-2">
							<g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />
						</div>
						<div class="show-value col-sm-10">
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
						</div>
					</div>
					</g:if>
				<%  } %>
			</div>
		</div>
	</body>
</html>
