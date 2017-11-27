<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


						  	<%--
								1 integer
								2 double
								3 string
								4 date
								5 boolean
						  	 --%>
	
						  	 
<div id="page-wrapper" ng-controller="${ appname }Report">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.report"/></h3>
		<input type="hidden" value="${ requestScope.response.data }" id="Hola" />
		<script>
			var nuevo = document.getElementById('Hola');
			console.log(nuevo);
		</script>
		<br />
		Productos
		<c:forEach var="a" items="${ requestScope.response.data.productos }">
			<br />
			${ a }
		</c:forEach>
		<br />
		Ordenes
		<c:forEach var="a" items="${ requestScope.response.data.ordenes }">
			<br />
			${ a }
		</c:forEach>
		<br />
		Inventario
		<c:forEach var="a" items="${ requestScope.response.data.inventario }">
		<br />
		${ a }
		</c:forEach>
		<br />
		talla Ventas
		<c:forEach var="a" items="${ requestScope.response.data.tallaVentas }">
		${ a }
		</c:forEach>
	</div>
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>