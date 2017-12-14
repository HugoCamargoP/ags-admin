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
		<h3 class="blank1 center"><s:message code="admin.report"/> ${ param.report }</h3>
		
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
		<br />
		
		<%-- Ventas generales --%>
		<c:if test="${not empty param.idReport and param.idReport == 1 }">
			<c:forEach var="a" items="${ requestScope.response.data.productos }">
				<div class="table-responsive">
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td><s:message code="reports.title"/></td>
							<td><s:message code="reports.description"/></td>
							<td><s:message code="reports.department"/></td>
						</tr>
						<tr>
							<td>${ a.title }</td>
							<td>${ a.description }</td>
							<td>${ a.departmentText }</td>
						</tr>
						<tr>
							<td colspan="3">
								<table class="table table-bordered a">
									<tr class="btn-black">
										<td>SKU</td>
										<td>Cantidad</td>
										<td>Precio unitario</td>
										<td>Total</td>
									</tr>
									<c:forEach var="b" items="${ a.ordersDetails }">
										<tr class="center">
											<td>${ b.product.sku }</td>
											<td>${ b.quantity }</td>
											<td>${ b.individualPrice }</td>
											<td>${ b.amount }</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</c:if>
		
		<%-- Ordenes por cliente --%>
		
		<c:if test="${not empty param.idReport and param.idReport == 2 and empty requestScope.response.data.ordenes[0].userText }">
			<div class="alert alert-info">
			  <strong>Sin información de cliente</strong> 
			</div>
		</c:if>
		<c:if test="${not empty param.idReport and param.idReport == 2 and not empty requestScope.response.data.ordenes[0].userText }">
			<div class="pull-left">
				<ul class="list-inline">
					<li>
						Cliente :
					</li>
					<li>
						<h4>${ requestScope.response.data.ordenes[0].userText }</h4>
					</li>
				</ul> 
			</div>
			<div class="pull-right">
				<script>
				var urln = document.URL;
				urln = urln.replace("sales", "rest/sales");
				
				function getpdf(t)
				{
					window.location = urln;
				}
				</script>
				<a target="_self" href="javascript:getpdf(this);" class="btn-primary btn">PDF</a>
			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
				<table class="table table-bordered a">
					<tr class="btn-black">
						<td>
						</td>
						<td><s:message code="reports.order"/></td>
						<td><s:message code="reports.statusText"/></td>
						<td><s:message code="reports.statusCreation"/></td>
					</tr>
					<c:forEach var="a" items="${ requestScope.response.data.ordenes }">
						<tr class="text-center">
							<td>
								<button class="btn btn-black" data-toggle="collapse" data-target="#order_${ a.id }">open</button>
							</td>
							<td>
								${ a.id }
							</td>
							<td>
								${ a.statusText }
							</td>
							<td>
								${ a.creation }
							</td>
						</tr>
						<tr id="order_${ a.id }" class="collapse">
							<td colspan="4">
								
								<c:forEach var="q" items="${ a.orderDetail }">
									${ q }
								</c:forEach>
								
								<c:forEach var="w" items="${ a.orderRecord }">
									${ w.update } <br />
									${ w.stateText }
								</c:forEach>

								<c:forEach var="e" items="${ a.orderAmount }">
									${ e }
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		
		
		<%-- Todas las ordenes cliente --%>
		
		<c:if test="${not empty param.idReport and param.idReport == 3 }">
		
			<c:forEach var="a" items="${ requestScope.response.data.ordenes }">
			
				<div class="pull-left">
					<ul class="list-inline">
						<li>
							Cliente : 
						</li>
						<li>
							<h4>${ a.userText }</h4>
						</li>
					</ul> 
				</div>
				<%--
				<div class="pull-right hidden">
					<script>
					var urln = document.URL;
					urln = urln.replace("sales", "rest/sales");
					
					function getpdf(t)
					{
						window.location = urln;
					}
					</script>
					<a target="_self" href="javascript:getpdf(this);" class="btn-primary btn">PDF</a>
				</div>
				--%>
				<div class="clearfix"></div>
				<div class="table-responsive">
					<table class="table table-bordered a centerText">
						<tr class="btn-black">
							<td>
							</td>
							<td><s:message code="reports.order"/></td>
							<td><s:message code="reports.statusText"/></td>
							<td><s:message code="reports.statusCreation"/></td>
						</tr>
						
							<tr>
								<td>
									<a href="javascript:void(0);" class="btn btn-black" data-toggle="collapse" data-target="#order_${ a.id }">open</a>
								</td>
								<td>
									${ a.id }
								</td>
								<td>
									${ a.statusText }
								</td>
								<td>
									${ a.creation }
								</td>
							</tr>
							<tr id="order_${ a.id }" class="collapse">
								<td colspan="4">
									<c:if test="${not empty a.orderDetail }" >
										<div class="table-responsive">
											<table class="table table-bordered">
												<tr class="btn-black">
													<th>Id SKU</th>
													<th>Cantidad</th>
													<th>Precio Individual</th>
													<th>Numero de guia</th>
													<th>Compañia de envio</th>
												</tr>
												<c:forEach var="q" items="${ a.orderDetail }">
													<tr>
														<td>${ q.idProductSku }</td>
														<td>${ q.quantity }</td>
														<td>${ q.individualPrice }</td>
														<td>${ q.guideNumber }</td>
														<td>${ q.shippingCompany }</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</c:if>
									<c:if test="${not empty a.orderRecord }" >
										<br />
										<div class="table-responsive">
											<table class="table table-bordered">
												<tr class="btn-black">
													<th>Ultima actualizaion</th>
													<th>Estado</th>
												</tr>
												<c:forEach var="w" items="${ a.orderRecord }"  varStatus="loop">
													<c:if test="${ loop.last }" >
														<tr>
															<td>${ w.update } </td>
															<td>${ w.stateText }</td>
														</tr>
													</c:if>
												</c:forEach>
											</table>
										</div>
									</c:if>
									<c:if test="${not empty a.orderAmount }" >
										<br />
										<div class="table-responsive">
											<table class="table table-bordered">
												<tr class="btn-black">
													<th>Orden</th>
													<th>Detalles</th>
													<th>Pago</th>
													<th>Precio</th>
												</tr>
												<c:forEach var="e" items="${ a.orderAmount }">
													<tr>
														<td>${ e.order }</td>
														<td>${ e.detail }</td>
														<td>${ e.tenderTypeText }</td>
														<td>${ e.amount }</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</c:if>
								</td>
							</tr>
							
					</table>
				</div>
				
			</c:forEach>
		</c:if>
		<!-- 
		<c:if test="${not empty requestScope.response.data.productos }">
			Productos
			<div class="table-responsive">
				<table class="table table-bordered a">
					<tr class="info">
						<td><s:message code="reports.title"/></td>
						<td><s:message code="reports.description"/></td>
						<td><s:message code="reports.department"/></td>
					</tr>
					<c:forEach var="a" items="${ requestScope.response.data.productos }">
						<tr>
							<td>${ a.title }</td>
							<td>${ a.description }</td>
							<td>${ a.departmentText }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br />
		</c:if>
		<c:if test="${not empty requestScope.response.data.ordenes }">
			Ordenes
			<c:forEach var="a" items="${ requestScope.response.data.ordenes }">
				<br />
				${ a }
			</c:forEach>
			<br />
		</c:if>
		<c:if test="${not empty requestScope.response.data.inventario }">
			Inventario
			<c:forEach var="a" items="${ requestScope.response.data.inventario }">
			<br />
			${ a }
			</c:forEach>
			<br />
		</c:if>
		<c:if test="${not empty requestScope.response.data.tallaVentas }">
			talla
			<div class="table-responsive">
				<table class="table table-bordered a">
					<tr class="info">
						<td><s:message code="reports.size" /></td>
						<td><s:message code="reports.price" /></td>
						<td><s:message code="reports.piece" /></td>
						<td><s:message code="reports.totalSales" /></td>
					</tr>
					<c:forEach var="a" items="${ requestScope.response.data.tallaVentas }">
					<tr class="center">
						<td>
							${ a.name }
						</td>
						<td>
							${ a.doubleAttribute }
						</td>
						<td>
							${ a.num }
						</td>
						<td>
							${ a.doubleAttribute }
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>  
		-->
	</div>
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>