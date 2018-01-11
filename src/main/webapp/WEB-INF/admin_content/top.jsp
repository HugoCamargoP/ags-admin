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
	      <c:set var = "string1" value = "${ requestScope['javax.servlet.forward.query_string'] }"/>
		<br />
		
		<a href="${ linkFive }?${ string1 }" class="btn btn btn-danger a pull-right">PDF <i class="fa fa-file-pdf-o"></i></a>
		<div class="clearfix"></div>
		<br />
		<%-- Ventas generales --%>
		<c:if test="${not empty param.idReport and param.idReport == 1 }">
			<c:forEach var="a" items="${ requestScope.response.data.productos }" varStatus="loop">
				<h3><s:message code="product.title" />: ${ a.title }</h3>  
				<div class="table-responsive">
					<table class="table table-bordered hidden a">
						<tr class="btn-black">
							<%--
							<td><s:message code="reports.title"/></td>
							--%>
							<td><s:message code="reports.description"/></td>
							<%-- 
							<td><s:message code="reports.department"/></td>
							--%>
						</tr>
						<tr class="center">
							<td>${ a.descriptionEs }</td>
						</tr>
						<tr class="center">
							<td>${ a.descriptionEn }</td>
						</tr>
						<tr class="center">
							<td>${ a.descriptionFr }</td>
						</tr>
					</table>
					<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
						<tr class="btn-black">
							<td>SKU</td>
							<td><s:message code="reports.pieceSales" /></td>
							<td><s:message code="reports.unitPrice" /></td>
							<td><s:message code="buy.total" /></td>
						</tr>
						<c:forEach var="b" items="${ a.ordersDetails }" varStatus="loop1">
							<tr class="center">
								<td>${ b.product.sku }</td>
								<td>${ b.quantity }</td>
								<td ng-init="arr2[${ loop.index }${ loop1.index }]=${ b.individualPrice }">{{ arr2[${ loop.index }${ loop1.index }] | currency }}</td>
								<td ng-init="arr[${ loop.index }] = arr[${ loop.index }] + ${ b.amount };arr1[${ loop.index }${ loop1.index }]=${ b.amount }">{{ arr1[${ loop.index }${ loop1.index }] | currency }}</td>
							</tr>
						</c:forEach>
							<tr class="center success">
								<td><s:message code="reports.totalSales" /></td>
								<td></td>
								<td></td>
								<td>{{arr[${ loop.index }] | currency}}</td>
							</tr>
					</table>
					<div class="clearfix"></div>
					<br />
					<br />
					<br />
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
					<c:forEach var="a" items="${ requestScope.response.data.ordenes }" varStatus="loop">
						<tr class="text-center">
							<td ng-init="arrdropdown[${ a.id }] = true;">
								<button ng-show="arrdropdown[${ a.id }]" ng-click="arrdropdown[${ a.id }] = false;" class="btn btn-success" data-toggle="collapse" data-target="#order_${ a.id }"><s:message code="reports.open" /></button>
								<button ng-hide="arrdropdown[${ a.id }]" ng-click="arrdropdown[${ a.id }] = true;" class="btn btn-success" data-toggle="collapse" data-target="#order_${ a.id }"><s:message code="reports.close" /></button>
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
								
								<c:if test="${  empty a.orderAmount and  empty a.orderRecord  and  empty a.orderDetail }">
									<div class="alert alert-info">
										<h1 class="center"><s:message code="reports.withoutInfo" />: <s:message code="product.order" /> ${ a.id }</h1>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount or not empty a.orderRecord  or not empty a.orderDetail }">
									<h1 class="center"><s:message code="product.order" />: ${ a.id }</h1>
								</c:if>
								
								<c:if test="${ not empty a.orderDetail }">
									<h3><s:message code="buy.details" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td>SKU</td>
												<td><s:message code="reports.pieceSales" /></td>
												<td><s:message code="reports.unitPrice" /></td>
												<td><s:message code="buy.total" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderDetail }" varStatus="loop1">
												<tr class="center">
													<td>${ b.product.sku }</td>
													<td>${ b.quantity }</td>
													<td ng-init="arr2[${ loop.index }${ loop1.index }]=${ b.individualPrice }">{{ arr2[${ loop.index }${ loop1.index }] | currency }}</td>
													<td ng-init="arr[${ loop.index }] = arr[${ loop.index }] + ${ b.amount };arr1[${ loop.index }${ loop1.index }]=${ b.amount }">{{ arr1[${ loop.index }${ loop1.index }] | currency }}</td>
												</tr>
											</c:forEach>
												<tr class="center success hidden">
													<td><s:message code="reports.totalSales" /></td>
													<td></td>
													<td></td>
													<td>{{arr[${ loop.index }] | currency}}</td>
												</tr>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderRecord }">
									<h3><s:message code="app.history" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td><s:message code="reports.statusText" /></td>
												<td><s:message code="reports.date" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderRecord }" varStatus="loop1">
												<tr class="center">
													<td>${ b.stateText }</td>
													<td>${ b.update }</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount }">
									<h3><s:message code="reports.paymentType" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td><s:message code="reports.type" /></td>
												<td><s:message code="product.details" /></td>
												<td><s:message code="reports.variety" /></td>
												<td><s:message code="buy.total" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderAmount }" varStatus="loop1">
												<tr class="center">
													<td>${ b.tenderTypeText }</td>
													<td>${ b.detail }</td>
													<td>${ b.variety }</td>
													<td ng-init="arr3[${ loop1.index }] = ${ b.amount };">{{ arr3[${ loop1.index }] | currency}}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount or not empty a.orderRecord  or not empty a.orderDetail }">
									<div class="clearfix"></div>
									<br />
									<br />
									<br />
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		
		
		<%-- Todas las ordenes cliente --%>
		
		<c:if test="${not empty param.idReport and param.idReport == 3 }">
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
					<c:forEach var="a" items="${ requestScope.response.data.ordenes }" varStatus="loop">
						<tr class="text-center">
							<td ng-init="arrdropdown[${ a.id }] = true;">
								<button ng-show="arrdropdown[${ a.id }]" ng-click="arrdropdown[${ a.id }] = false;" class="btn btn-success" data-toggle="collapse" data-target="#order_${ a.id }"><s:message code="reports.open" /></button>
								<button ng-hide="arrdropdown[${ a.id }]" ng-click="arrdropdown[${ a.id }] = true;" class="btn btn-success" data-toggle="collapse" data-target="#order_${ a.id }"><s:message code="reports.close" /></button>
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
								<c:if test="${  empty a.orderAmount and  empty a.orderRecord  and  empty a.orderDetail }">
									<div class="alert alert-info">
										<h1 class="center"><s:message code="reports.withoutInfo" />: <s:message code="product.order" /> ${ a.id }</h1>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount or not empty a.orderRecord  or not empty a.orderDetail }">
									<h1 class="center"><s:message code="product.order" />: ${ a.id }</h1>
								</c:if>
								
								<div class="">
									<h4><s:message code="reports.client" />: ${ requestScope.response.data.ordenes[0].userText }</h4>
								</div>
								
								<c:if test="${ not empty a.orderDetail }">
									<h3><s:message code="buy.details" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td>SKU</td>
												<td><s:message code="reports.pieceSales" /></td>
												<td><s:message code="reports.unitPrice" /></td>
												<td><s:message code="buy.total" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderDetail }" varStatus="loop1">
												<tr class="center">
													<td>${ b.product.sku }</td>
													<td>${ b.quantity }</td>
													<td ng-init="arr2[${ loop.index }${ loop1.index }]=${ b.individualPrice }">{{ arr2[${ loop.index }${ loop1.index }] | currency }}</td>
													<td ng-init="arr[${ loop.index }] = arr[${ loop.index }] + ${ b.amount };arr1[${ loop.index }${ loop1.index }]=${ b.amount }">{{ arr1[${ loop.index }${ loop1.index }] | currency }}</td>
												</tr>
											</c:forEach>
												<tr class="center success hidden">
													<td><s:message code="reports.totalSales" /></td>
													<td></td>
													<td></td>
													<td>{{arr[${ loop.index }] | currency}}</td>
												</tr>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderRecord }">
									<h3><s:message code="app.history" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td><s:message code="reports.statusText" /></td>
												<td><s:message code="reports.date" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderRecord }" varStatus="loop1">
												<tr class="center">
													<td>${ b.stateText }</td>
													<td>${ b.update }</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount }">
									<h3><s:message code="reports.paymentType" /></h3>
									<div class="table-responsive">
										<table class="table table-bordered a" ng-init="arr[${ loop.index }] = 0;">
											<tr class="btn-black">
												<td><s:message code="reports.type" /></td>
												<td><s:message code="product.details" /></td>
												<td><s:message code="reports.variety" /></td>
												<td><s:message code="buy.total" /></td>
											</tr>
											<c:forEach var="b" items="${ a.orderAmount }" varStatus="loop1">
												<tr class="center">
													<td>${ b.tenderTypeText }</td>
													<td>${ b.detail }</td>
													<td>${ b.variety }</td>
													<td ng-init="arr3[${ loop1.index }] = ${ b.amount };">{{ arr3[${ loop1.index }] | currency}}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</c:if>
								
								<c:if test="${ not empty a.orderAmount or not empty a.orderRecord  or not empty a.orderDetail }">
									<div class="clearfix"></div>
									<br />
									<br />
									<br />
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 
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
													<th><s:message code="buy.quantity" /></th>
													<th><s:message code="reports.unitPrice" /></th>
													<th><s:message code="reports.guideNumber" /></th>
													<th><s:message code="reports.deliveryCompany" /></th>
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
													<th><s:message code="reports.lastUpdate" /></th>
													<th><s:message code="reports.statusText" /></th>
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
													<th><s:message code="product.order" /></th>
													<th><s:message code="product.details" /></th>
													<th><s:message code="buy.pay" /></th>
													<th><s:message code="buy.price" /></th>
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
			-->
		</c:if>
		
		<%-- Todas Inventario  --%>
		
		<c:if test="${not empty param.idReport and param.idReport == 4 }">
			<div class="table-responsive">
				<c:forEach var="a" items="${ requestScope.response.data.inventario }">
					<h2>
						${ a.title }
					</h2> 
					<%--
					<table class="table table-bordered a">					
						<tr class="btn-black">
							<td><s:message code="reports.title" /></td>
							<td><s:message code="reports.department" /></td>
						</tr>
						<tr class="center">
							<td>
								${ a.title }
							</td>
							<td>
								${ a.departmentText }
							</td>
						</tr>
					</table>
					--%>
					<table class="table table-bordered a">
						<tr class="btn-black center">
							<td colspan="2"><s:message code="reports.description" /></td>
						</tr>
						<tr class="center">
							<td>ES</td>
							<td colspan="">${ a.descriptionEs }</td>
						</tr>
						<tr class="center">
							<td>EN</td>
							<td colspan="">${ a.descriptionEn }</td>
						</tr>
						<tr class="center">
							<td>FR</td>
							<td colspan="">${ a.descriptionFr }</td>
						</tr>
					</table>
					<table class="table table-bordered a">
						<tr class="btn-black">
							<td>SKU</td>
							<td><s:message code="admin.size" /></td>
							<td><s:message code="reports.piece" /></td>
							<td><s:message code="reports.price" /></td>
						</tr>
						<c:forEach var="b" items="${ a.skuProduct }">
							<tr class="center">
								<td>${ b.sku }</td>
								<td>${ b.sizeText }</td>
								<td>${ b.stock }</td>
								<td>${ b.price }</td>
							</tr>
						</c:forEach>
					</table>
					<table class="table table-bordered a">
						<tr class="">
							<td>
								<div class="container-img-muestras">
									<ul class="list-inline galeriaq">
										<c:forEach var="b" items="${ a.productDetails }">
											<li class="col-xs-6 col-sm-4 col-md-3 col-lg-2 center">
												<img class="img-thumbnail" src="${ b.url }" alt="" />
											</li>
										</c:forEach>
									</ul>
								</div>
							</td>
						</tr>
					</table>	
					<div class="clearfix"></div>
					<br />
					<br />
					<br />
				</c:forEach>
			</div>
			<div class="table-responsive">
				<table class="table table-bordered a">
					<tr class="btn-black">
						<td><s:message code="reports.size" /></td>
						<td><s:message code="reports.price" /></td>
					</tr>
					<c:forEach var="a" items="${ requestScope.response.data.inventario }">
					<tr>
						<td>${ a.title }</td>
						<td>${ a.title }</td>
						<td>${ a.title }</td>
						<td>${ a.title }</td>
					</tr>
					<%--
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
					--%>
					</c:forEach>
				</table>
			</div>
		</c:if>
		<%-- Todas Inventario  --%>
		
		<%-- Todas TallasGeneral  --%>
		
		<c:if test="${not empty param.idReport and param.idReport == 5 }">
		
			<div class="table-responsive">
				<table class="table table-bordered a">
					<tr class="btn-black" ng-init="arrventastotal = 0">
						<td><s:message code="reports.size" /></td>
						<td><s:message code="reports.price" /></td>
						<td><s:message code="reports.pieceSales" /></td>
						<td><s:message code="reports.totalSales" /></td>
					</tr>
					<c:forEach var="a" items="${ requestScope.response.data.tallaVentas }" varStatus="loop">
					<tr class="center">
						<td>
							${ a.name }
						</td>
						<td ng-init="arrventas[${ loop.index }] = ${ a.doubleAttribute };">
							{{ arrventas[${ loop.index }] | currency }}
						</td>
						<td>
							${ a.num }
						</td>
						<td ng-init="arrventastotal = arrventastotal + (arrventas[${ loop.index }]*${ a.num });
									 arrventasCuantos[${ loop.index }] =  arrventas[${ loop.index }]*${ a.num };
									 arrventastotal[${ loop.index }];">
							{{ arrventasCuantos[${ loop.index }] | currency }}
						</td>
					</tr>
					</c:forEach>
					<tr class="center success">
						<td>
							<s:message code="reports.totalSales" />
						</td>
						<td >
							
						</td>
						<td>
							
						</td>
						<td>
							{{ arrventastotal | currency }}
						</td>
					</tr>
				</table>
			</div>
		</c:if>
		
		<%-- TallasGeneral --%>
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
		
	</div>


<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>