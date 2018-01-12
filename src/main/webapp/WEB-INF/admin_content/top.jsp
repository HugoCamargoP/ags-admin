<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


						  	<%--
								1 integer
								2 double
								3 string
								4 date
								5 boolean
						  	 --%>
	
<div id="page-wrapper" ng-controller="${ appname }Report">
	<div class="graphs">
		<h3 class="blank1 center">
		<c:if test="${ param.top == 1 }">
			<s:message code="reports.topFiveProducts" />
		</c:if>
		<c:if test="${ param.top == 3 }">
			<s:message code="reports.topFiveOrders" />
		</c:if>
		<c:if test="${ param.top == 4 }">
			<s:message code="reports.topFiveCountries" />
		</c:if>
		<c:if test="${ param.top == 2 }">
			<s:message code="reports.topFiveOthers" />
		</c:if>
		</h3>
		
		<br />
		<div class="col-xs-12">
		  	<a href="" class=" center center1 visible-xs visile-sm">
		  		<img class="" id="" src="http://hugoe.tk:8081/yetibera/r/img/logos/lnegro.png" alt="">
		  	</a>
		  	<a href="" class="hidden-xs hidden-sm">
		  		<img class="" id="" src="http://hugoe.tk:8081/yetibera/r/img/logos/lnegro.png" alt="">
		  	</a>
		  	<div class="" style="float: right;">
				<h3></h3>
		  	</div>
		</div>
		<div class="clearfix"></div>
	      <c:set var = "string1" value = "${ requestScope['javax.servlet.forward.query_string'] }"/>
		<br />
		<a href="${ linkFivePDF }?${ string1 }" class="btn btn btn-danger a pull-right">PDF <i class="fa fa-file-pdf-o"></i></a>
		<div class="clearfix"></div>
		<br />
		
		<%-- --%>
			<c:if test="${ not empty requestScope.response.data.productos }">
				<div class="table-responsive">
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td><s:message code="reports.ranking" /></td>
							<td><s:message code="reports.title"/></td>
							<td><s:message code="reports.sales"/></td>
						</tr>
						<c:forEach var="a" items="${ requestScope.response.data.productos }" varStatus="loop">
							<tr class="center">
								<td>${ loop.index + 1 }</td>
								<td>${ a.title }</td>
								<td>${ a.sales }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
			</c:if>
			
			<c:if test="${ not empty requestScope.response.data.ordenes }">
				<div class="table-responsive">
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td><s:message code="reports.ranking" /></td>
							<td><s:message code="reports.order"/></td>
						</tr>
						<c:forEach var="a" items="${ requestScope.response.data.ordenes }" varStatus="loop">
							<tr class="center">
								<td>${ loop.index + 1 }</td>
								<td>${ a.id }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
			</c:if>
			
			<c:if test="${ not empty requestScope.response.data.paises }">
				<div class="table-responsive">
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td><s:message code="reports.ranking" /></td>
							<td><s:message code="reports.country"/></td>
						</tr>
						<c:forEach var="a" items="${ requestScope.response.data.paises }" varStatus="loop">
							<tr class="center">
								<td>${ loop.index + 1 }</td>
								<td>${ a.name }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
			</c:if>
			
			<c:if test="${ not empty requestScope.response.data.clientes }">
				<div class="table-responsive">
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td><s:message code="reports.ranking" /></td>
							<td><s:message code="register.name"/></td>
						</tr>
						<c:forEach var="a" items="${ requestScope.response.data.clientes }" varStatus="loop">
							<tr class="center">
								<td>${ loop.index + 1 }</td>
								<td>${ a.name }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
			</c:if>			
		<%-- --%>
	</div>
</div>


<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>