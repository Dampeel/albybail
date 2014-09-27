<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="container">
			<div class="row">
	   			<div class="col-sm-7">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>
				<div class="col-sm-5">
					<div class="h1-buttons">
						<g:link class="btn btn-default create" action="create"><g:message code="default.create.label" args="[entityName]" /></g:link>
					</div>
				</div>
			</div>
		
			<div class="page-content">
				<g:if test="\${flash.message}">
					<div class="alert alert-info" role="status">\${flash.message}</div>
				</g:if>
				
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
							<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
								allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
								props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) && (domainClass.constrainedProperties[it.name] ? domainClass.constrainedProperties[it.name].display : true) }
								Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
								props.eachWithIndex { p, i ->
									if (i < 6) {
										if (p.isAssociation()) { %>
								<th><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
							<%      } else { %>
								<g:sortableColumn property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" />
							<%  }   }   } %>
							</tr>
						</thead>
						
						<tbody>
						<g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
							<tr>
							<%  props.eachWithIndex { p, i ->
									if (i == 0) { %>
								<td><g:link action="show" id="\${${propertyName}.id}">\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</g:link></td>
							<%      } else if (i < 6) {
										if (p.type == Boolean || p.type == boolean) { %>
								<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
							<%          } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
								<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
							<%          } else { %>
								<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
							<%  }   }   } %>
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
		
				<ul class="pagination">
					<g:paginate total="\${${propertyName}Count ?: 0}" />
				</ul>
			</div>
		</div>
	</body>
</html>
