<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<!-- left side start-->
			<div class="left-side sticky-left-side sticky-header">
	
				<!--logo and iconic logo start-->
				<div class="logo text-center">
					<!-- h1><a href="#">100% <span>Admin</span></a></h1>
                    <img class="img-responsive" src="${ContextPath}/r/images/text4070-1-0.png" alt="" /
					<a href="${ linkMain }">WARD <i class="fa fa-home"></i> </a>-->
					<h1><a href="${ linkAdminHome }"><img src="${ContextPath}/r/img/logos/yclara.png" class="" style="max-height: 40px;"><span></span></a></h1>
				</div>
				<div class="logo-icon text-center">
					<a href="${ linkAdminHome }"><i class="fa fa-home"></i> </a>
				</div>
	
				<!--logo and iconic logo end-->
				<div class="left-side-inner">

					<!--sidebar nav start-->
						<ul class="nav nav-pills nav-stacked custom-nav"> 
	                        <li class="menu-list hidden"><a href="#"><i class="fa fa-cogs"></i><span>Configuración</span></a>
	                            <ul class="sub-menu-list">
									<li><a href="${ linkEmpty }">Sistema</a></li>
									<li><a href="${ linkEmpty }">Pagos</a></li>
								</ul>
	                        </li> 
	                        <li class=""><a href="${linkConfig}"><i class="fa fa-cogs"></i> <span><s:message code="admin.config"/></span></a>
							</li>    
	                        <li class=""><a href="${linkAdminUser}"><i class="fa fa-user"></i> <span><s:message code="admin.users"/></span></a>
							</li>    
	                        <li class=""><a href="${ linkAdminItems }"><i class="fa fa-barcode"></i> <span><s:message code="admin.items"/></span></a>
							</li>  
	                        <li class=""><a href="${ linkAdminReports }"><i class="fa fa-file-pdf-o"></i> <span><s:message code="admin.report"/></span></a>
							</li>  
	                        <li class="menu-list"><a href="${ linkAdminOrders }"><i class="fa fa-truck"></i> <span>Pedidos</span></a>
								<ul class="sub-menu-list">
									<li><a href="${ linkAdminOrders }">Historial</a> </li>
								</ul>
							</li>   
							<li>
								<a href="">
									<form action="${linkLogout}" method="POST" onclick="$(this).closest('form').submit()">
										<i class="fa fa-power-off"></i><span>Salir</span>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									</form>
								</a>
							</li>
						</ul>
					<!--sidebar nav end-->
				</div>
			</div>
			<!-- left side end-->