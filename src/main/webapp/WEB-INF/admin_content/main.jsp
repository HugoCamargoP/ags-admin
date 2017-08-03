<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

			<div id="page-wrapper">
				<div class="graphs">
					<div class="col_3">
						<div class="col-md-3 widget widget1">
							<div class="r3_counter_box">
								<i class="fa fa-mail-forward"></i>
								<div class="stats">
								  <h5>45 <span>%</span></h5>
								  <div class="grow">
									<p>Crecimiento</p>
								  </div>
								</div>
							</div>
						</div>
						<div class="col-md-3 widget widget1">
							<div class="r3_counter_box">
								<i class="fa fa-users"></i>
								<div class="stats">
								  <h5>50 <span>%</span></h5>
								  <div class="grow grow1">
									<p>Nuevos Clientes</p>
								  </div>
								</div>
							</div>
						</div>
						<div class="col-md-3 widget widget1">
							<div class="r3_counter_box">
								<i class="fa fa-eye"></i>
								<div class="stats">
								  <h5>70 <span>%</span></h5>
								  <div class="grow grow3">
									<p>Visitantes</p>
								  </div>
								</div>
							</div>
						 </div>
						 <div class="col-md-3 widget">
							<div class="r3_counter_box">
								<i class="fa fa-usd"></i>
								<div class="stats">
								  <h5>70 <span>%</span></h5>
								  <div class="grow grow2">
									<p>Profit</p>
								  </div>
								</div>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>

			<!-- switches -->
					<div class="switches">
						<div class="col-4">
							<div class="col-md-4 switch-right">
								<div class="switch-right-grid">
									<div class="switch-right-grid1">
										<h3>DATOS DE HOY</h3>
										<p>Estadísticas Generales de Actividad Diaria.</p>
										<ul>
											<li>Ganancias: $4,000.00 MXN</li>
											<li>Artículos Vendidos: 205 Artículos</li>
											<li>Ventas Ultima Hora: $347 MXN</li>
										</ul>
									</div>
								</div>
								<div class="sparkline">
									<canvas id="line" height="150" width="480" style="width: 480px; height: 150px;"></canvas>
								</div>
							</div>
							<div class="col-md-4 switch-right">
								<div class="switch-right-grid">
									<div class="switch-right-grid1">
										<h3>DATOS MENSUALES</h3>
										<p>Estadisticas Generales de Actividad Mensual</p>
										<ul>
											<li>Ganancias: $50,000.00 MXN</li>
											<li>Artículos Vendidos: 4003 Artículos</li>
											<li>Ventas de la Ultima Hora: $2,434 MXN</li>
										</ul>
									</div>
								</div>
								<div class="sparkline">
									<canvas id="bar" height="150" width="480" style="width: 480px; height: 150px;"></canvas>
			
								</div>
							</div>
							<div class="col-md-4 switch-right">
								<div class="switch-right-grid">
									<div class="switch-right-grid1">
										<h3>DATOS HISTÓRICOS</h3>
										<p>Estadisticas Generales de Actividad Histórica.</p>
										<ul>
											<li>Ganancias: $80,000 MXN</li>
											<li>Artículos Vendidos: 8,000 Artículos</li>
											<li>Ventas de la ültima hora: $75,434 MXN</li>
										</ul>
									</div>
								</div>
								<div class="sparkline">
										
										<div id="graph-wrapper">
											<div class="graph-container">
												<div id="graph-lines"> </div>
												<div id="graph-bars"> </div>
											</div>
										</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			<!--body wrapper start-->
			</div>
			 <!--body wrapper end-->