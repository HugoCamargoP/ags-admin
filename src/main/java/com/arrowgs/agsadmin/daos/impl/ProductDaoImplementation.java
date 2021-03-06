package com.arrowgs.agsadmin.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.arrowgs.agsadmin.daos.ProductDao;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.MessageStock;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;
import com.arrowgs.agsadmin.service.OrderService;



@Repository
public class ProductDaoImplementation implements ProductDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert productoInsertActor;
	private SimpleJdbcInsert productDetailInsertActor;
	private SimpleJdbcInsert skuProductInsertActor;
	private SimpleJdbcInsert sizeInsertActor;
	private SimpleJdbcInsert sizeDescriptionInsertActor;
	
	private PlatformTransactionManager transactionManager;
	
	/* Product */
	class ProductRowMapper implements RowMapper<Product>{

		private boolean expandible;
		
		public ProductRowMapper(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public Product mapRow(ResultSet rs, int row) throws SQLException {
			Product producto = new Product();
			producto.setId(rs.getInt(1));
			producto.setDescriptionEs(rs.getString(2));
			producto.setTitle(rs.getString(4));
			producto.setDepartment(rs.getInt(5));
			producto.setDescriptionEn(rs.getString(6));
			producto.setDescriptionFr(rs.getString(7));
			producto.setReleaseDate(rs.getDate(8));
			if(expandible){
				producto.setDepartmentText(rs.getString(9));
			}
			return producto;
		}
		
	}
	
	class ProductRowExtractor implements ResultSetExtractor<Product>{

		private boolean expandible;
		
		public ProductRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			return rs.next() ? (new ProductRowMapper(expandible)).mapRow(rs, 0) : null;
		}
		
	}
	
	/* Product Sku */
	
	class SkuProductRowMapper implements RowMapper<SkuProduct>{
		
		private boolean expandible;
		
		public SkuProductRowMapper(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public SkuProduct mapRow(ResultSet rs, int col) throws SQLException {
			SkuProduct sku = new SkuProduct();
			sku.setId(rs.getInt(1));
			sku.setProduct(rs.getInt(2));
			sku.setSku(rs.getString(3));
			sku.setSize(rs.getInt(4));
			sku.setPrice(rs.getDouble(5));
			sku.setStock(rs.getInt(6));
			if(expandible){
				sku.setSizeText(rs.getString(8));
				sku.setProductDescrEs(rs.getString(9));
				sku.setProductTitle(rs.getString(10));
				sku.setProductDescrEn(rs.getString(11));
				sku.setProductDescrFr(rs.getString(12));
			}
			return sku;
		}
		
	}
	
	class SkuProductRowExtractor implements ResultSetExtractor<SkuProduct>{

		boolean expandible;
		
		public SkuProductRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public SkuProduct extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new SkuProductRowMapper(expandible)).mapRow(rs, 0) : null;
		}
		
	}
	
	//SkuTop
	class SkuProductTopRowMapper implements RowMapper<SkuProduct>{
				
		
		@Override
		public SkuProduct mapRow(ResultSet rs, int col) throws SQLException {
			SkuProduct sku = new SkuProduct();
			sku.setId(rs.getInt(1));
			sku.setProduct(rs.getInt(2));
			sku.setSku(rs.getString(3));
			sku.setSize(rs.getInt(4));
			sku.setPrice(rs.getDouble(5));
			sku.setStock(rs.getInt(6));
			sku.setIndividualSales(rs.getInt(8));
			return sku;
		}
		
	}
	
	/* ProductDetail */
	class ProductDetailRowMapper implements RowMapper<ProductDetail>{

		@Override
		public ProductDetail mapRow(ResultSet rs, int col) throws SQLException {
			ProductDetail detail = new ProductDetail();
			detail.setId(rs.getInt(1));
			detail.setProduct(rs.getInt(2));
			detail.setUrl(rs.getString(3));
			detail.setSide(rs.getInt(4));
			return detail;
		}
		
	}
	
	class ProductDetailRowExtractor implements ResultSetExtractor<ProductDetail>{

		@Override
		public ProductDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new ProductDetailRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	/* Size */
	
	class SizeRowMapper	implements RowMapper<IdNameTable>{

		@Override
		public IdNameTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			IdNameTable theSize = new IdNameTable();
			
			theSize.setId(rs.getInt(1));
			theSize.setName(rs.getString(2));
			
			return theSize;
		}
		
	}
	
	class SizeRowExtractor implements ResultSetExtractor<IdNameTable>{

		@Override
		public IdNameTable extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new SizeRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	class SizeDescriptionRowMapper implements RowMapper<SizeDescription>{

		boolean expandible;
		
		public SizeDescriptionRowMapper(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public SizeDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
			SizeDescription sd = new SizeDescription();
			
			sd.setId(rs.getInt(1));
			sd.setPechoAlto(rs.getDouble(2));
			sd.setPechoBajo(rs.getDouble(3));
			sd.setAbdomen(rs.getDouble(4));
			sd.setCadera(rs.getDouble(5));
			sd.setHombros(rs.getDouble(6));
			sd.setEspalda(rs.getDouble(7));
			sd.setHombroSolo(rs.getDouble(8));
			sd.setSiza(rs.getDouble(9));
			sd.setManga(rs.getDouble(10));
			sd.setPuno(rs.getDouble(11));
			sd.setCuello(rs.getDouble(12));
			sd.setLargoFrente(rs.getDouble(13));
			sd.setLargoEspalda(rs.getDouble(14));
			sd.setIdTalla(rs.getInt(15));
			
			if(expandible){
				sd.setTallaText(rs.getString(16));
			}
			
			return sd;
		}
		
	}
	
	class SizeDescriptionRowExtractor implements ResultSetExtractor<SizeDescription>{

		boolean expandible;
		
		public SizeDescriptionRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public SizeDescription extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new SizeDescriptionRowMapper(expandible)).mapRow(rs, 0) : null;
		}
		
	}
	
	/* Product */
	class TopProductRowMapper implements RowMapper<Product>{

		@Override
		public Product mapRow(ResultSet rs, int row) throws SQLException {
			Product producto = new Product();
			producto.setId(rs.getInt(1));
			producto.setDescriptionEs(rs.getString(2));
			producto.setTitle(rs.getString(4));
			producto.setDepartment(rs.getInt(5));
			producto.setDescriptionEn(rs.getString(6));
			producto.setDescriptionFr(rs.getString(7));
			producto.setReleaseDate(rs.getDate(8));
			producto.setSales(rs.getInt(9));
			return producto;
		}
		
	}
	
	class TopProductRowExtractor implements ResultSetExtractor<Product>{

		@Override
		public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			return rs.next() ? (new ProductRowMapper(false)).mapRow(rs, 0) : null;
		}
		
	}
	
	class MessageStockRowMapper implements RowMapper<MessageStock>{

		@Override
		public MessageStock mapRow(ResultSet rs, int rowNum) throws SQLException {
			MessageStock message = new MessageStock();
			
			message.setId(rs.getInt(1));
			message.setIdSkuProduct(rs.getInt(2));
			message.setIdUser(rs.getInt(3));
			
			return message;
		}
		
	}
	
	
	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager){
		this.transactionManager = transactionManager;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		productoInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(ProductTable)
				.usingGeneratedKeyColumns("id");
		
		productDetailInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(ProducDetailTable)
				.usingGeneratedKeyColumns("id");
		
		skuProductInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(SkuProductTable)
				.usingGeneratedKeyColumns("id");
		
		sizeInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(SizeTable)
				.usingGeneratedKeyColumns("id");
		
		sizeDescriptionInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(SizeDescriptionTable)
				.usingGeneratedKeyColumns("id_medidas_descripcion");
	}
	
	
	@Override
	public List<Product> getProducts() {		
		String query = "select * from productos where activo = :enable order by id desc";
		SqlParameterSource paramMap = new MapSqlParameterSource("enable",Enable);
		return jdbcTemplate.query(query,paramMap,new ProductRowMapper(false));
	}

	@Override
	public Product getProductById(Integer id) {
		String query = "select p.*, d.descripcion from productos p left join departamentos d on p.departamento=d.id where p.id = :id and p.activo = :enable";
		MapSqlParameterSource productMap = new MapSqlParameterSource("id",id);
		productMap.addValue("enable", Enable);
		return jdbcTemplate.query(query, productMap, new ProductRowExtractor(true));
	}

	@Override
	public void modifyProduct(Product product) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{				
			String sql = "UPDATE productos set descripcion_es = :descripcionEs, descripcion_en = :descripcionEn, descripcion_fr = :descripcionFr, titulo = :title, fecha_lanzamiento = :fecha_lanzamiento WHERE id = :id";			
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("descripcionEs", product.getDescriptionEs());
			paramMap.put("descripcionEn", product.getDescriptionEn());
			paramMap.put("descripcionFr", product.getDescriptionFr());
			paramMap.put("title", product.getTitle());
			paramMap.put("fecha_lanzamiento", product.getReleaseDate());
			paramMap.put("id", product.getId());			
			jdbcTemplate.update(sql, paramMap);
			if(product.getSkuProduct()!=null)
			{
				modifyListSkuProduct(product.getSkuProduct());
			}
			if(product.getProductDetails()!=null && !product.getProductDetails().isEmpty()){
				Iterator<ProductDetail> iterator = product.getProductDetails().iterator();
				while(iterator.hasNext()){
					ProductDetail actual = iterator.next();
					modifyProductDetail(actual);
				}
			}
	
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
			throw e;
		}
	}
	
	@Override
	public void modifyListSkuProduct(List<SkuProduct> skuProducts) {
		TransactionStatus transactionStatus = 
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			Iterator<SkuProduct> list = skuProducts.iterator();
			while(list.hasNext()){				
				updateSkuProducts(list.next());
			}
			
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
			throw e;
		}
	}

	@Override
	public void modifyProduct(Product product, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProductById(Integer id) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			
			removeProductDetailByProduct(id);
			//Aqui el delete del productsku
			
			removeSkuProductByProduct(id);
			
			String sql = "UPDATE productos SET activo = :enable WHERE id = :id";
			MapSqlParameterSource paramMap = new MapSqlParameterSource("enable",Disable);
			paramMap.addValue("id", id);
			jdbcTemplate.update(sql, paramMap);
			
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
			throw e;
		}
	}
	
	@Override
	public void removeProductOnShoppingAndWish(Integer product) {
		String sql = "DELETE od FROM orden_detalles od LEFT JOIN productos_sku ps ON od.id_producto_sku = ps.id LEFT JOIN ordenes o ON od.orden = o.id WHERE ps.producto = :product and (o.estado= :wish or o.estado = :shopping)";
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("wish", OrderService.statusWishList);
		paramMap.put("shopping", OrderService.statusShoppingCar);
		paramMap.put("product", product);
		
		jdbcTemplate.update(sql, paramMap);
	}


	@Override
	public void addProduct(Product product) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
					
			Map<String,Object> producto = new HashMap<String,Object>();		
			producto.put("descripcion_es", product.getDescriptionEs());
			producto.put("descripcion_en", product.getDescriptionEn());
			producto.put("descripcion_fr", product.getDescriptionFr());
			producto.put("titulo", product.getTitle());
			producto.put("fecha_lanzamiento", product.getReleaseDate());
			producto.put("activo", Enable);
			producto.put("departamento", product.getDepartment());
			Number idProduct = productoInsertActor.executeAndReturnKey(producto);
			product.setId(idProduct.intValue());
			if(product.getSkuProduct()!=null || product.getSkuProduct().size()>0){
				Iterator<SkuProduct> iterator = product.getSkuProduct().iterator();
				while(iterator.hasNext()){
					SkuProduct actual = iterator.next();
					actual.setProduct(idProduct.intValue());
					createSkuProduct(actual);
				}
			}		
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
		}
	}


	
	/****** ProductDetail ******/
	@Override
	public List<ProductDetail> getAllProductDetails() {
		String sql = "SELECT * FROM producto_detalles";
		return jdbcTemplate.query(sql, new ProductDetailRowMapper());
	}


	@Override
	public List<ProductDetail> getProductDetails(Integer idProduct) {
		String sql = "SELECT * FROM producto_detalles WHERE producto = :id ORDER BY side";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idProduct);		
		return jdbcTemplate.query(sql, paramMap, new ProductDetailRowMapper());
	}

	@Override
	public ProductDetail oneProductDetail(Integer idProduct) {
		String sql = "SELECT * FROM producto_detalles WHERE producto = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idProduct);		
		return jdbcTemplate.query(sql, paramMap, new ProductDetailRowExtractor());
	}
	
	@Override
	public ProductDetail getLastProductDetail() {
		String sql = "SELECT * FROM producto_detalles ORDER BY id DESC LIMIT 1";	
		return jdbcTemplate.query(sql, new ProductDetailRowExtractor());
	}
	
	
	
	@Override
	public void addProductDetail(ProductDetail productDetail) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("producto", productDetail.getProduct());
		paramMap.put("url_imagen", productDetail.getUrl());
		paramMap.put("side", 3);
		Number id = productDetailInsertActor.executeAndReturnKey(paramMap);
		productDetail.setId(id.intValue());
	}


	@Override
	public void modifyProductDetail(ProductDetail productDetail) {
		String sql = "UPDATE producto_detalles SET producto = :producto , url_imagen = :url, side = :side where id = :id";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("producto", productDetail.getProduct());
		paramMap.put("url", productDetail.getUrl());
		paramMap.put("side", productDetail.getSide());
		paramMap.put("id", productDetail.getId());
		jdbcTemplate.update(sql, paramMap);
	}


	@Override
	public void removeProductDetail(Integer idProductDetail) {
		String sql = "DELETE FROM producto_detalles WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idProductDetail);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void removeProductDetailByProduct(Integer idProduct) {
		String sql = "DELETE FROM producto_detalles WHERE producto = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idProduct);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<Product> getProductsByFilter(Product product, Integer page, Integer inPage) {
		 page = (page -1)*inPage;
		 boolean where = false,join=false;
		 StringBuilder sql = new StringBuilder("SELECT distinct(p.id), p.descripcion_es, p.activo, p.titulo, p.departamento, p.descripcion_en, p.descripcion_fr, p.fecha_lanzamiento, d.descripcion FROM productos p JOIN departamentos d on p.departamento = d.id");
		 if((product.getSku()!=null && ! product.getSku().equals(""))||(product.getTalla()!=null && product.getTalla().intValue()>0) ||(product.getLessThan()!=null && product.getLessThan().doubleValue() > 0.0) || (product.getGreaterThan()!=null && product.getGreaterThan().doubleValue() > 0.0)){
			 sql.append(" LEFT JOIN productos_sku ps ON ps.producto = p.id ");
			 join=true;
		 }
		 StringBuilder aux = new StringBuilder("");
		 Map<String,Object> paramMap = new HashMap<>();	
		 //Esta condición es temporal, hasta que se decida si en el front se buscará por idiomas o será en general
		 if(product.getDescription()!=null && ! product.getDescription().equals("")){
			 aux.append(" p.descripcion_es like :descripcion or p.descripcion_en like :descripcion or p.descripcion_fr like :descripcion");
			 paramMap.put("descripcion", product.getDescription());
			 where = true;
		 }
//		 if(product.getDescriptionEs()!=null && ! product.getDescriptionEs().equals("")){			 
//			 aux.append(" p.descripcion_es like :descripcionEs");
//			 paramMap.put("descripcionEs", product.getDescriptionEs());
//			 where = true;
//		 }
//		 if(product.getDescriptionEn()!=null && ! product.getDescriptionEn().equals("")){	
//			 if(where){
//				 aux.append(" and");
//			 }
//			 aux.append(" p.descripcion_en like :descripcionEn");
//			 paramMap.put("descripcionEn", product.getDescriptionEs());
//			 where = true;
//		 }
//		 if(product.getDescriptionFr()!=null && ! product.getDescriptionFr().equals("")){
//			 if(where){
//				 aux.append(" and");
//			 }
//			 aux.append(" p.descripcion_fr like :descripcionFr");
//			 paramMap.put("descripcionFr", product.getDescriptionEs());
//			 where = true;
//		 }
		 if(product.getTitle()!=null && ! product.getTitle().equals("")){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" p.titulo like :titulo");
			 paramMap.put("titulo", product.getTitle());
			 where = true;
		 }
		 if(product.getSku()!=null && ! product.getSku().equals("")){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.sku like :sku");
			 paramMap.put("sku", product.getSku());
			 where = true;
		 }
		 if(product.getTalla()!=null && product.getTalla().intValue()>0){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.talla = :talla");
			 paramMap.put("talla", product.getTalla());
			 where = true;
		 }
		 if(product.getGreaterThan()!=null && product.getGreaterThan().doubleValue() > 0.0){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.precio > :greater");
			 paramMap.put("greater", product.getGreaterThan());
			 where = true;
		 }
		 if(product.getLessThan()!=null && product.getLessThan().doubleValue() > 0.0){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.precio < :less");
			 paramMap.put("less", product.getLessThan());
			 where = true;
		 }
		 if(where){
			 sql.append(" WHERE");
			 sql.append(aux);
			 sql.append(" AND p.activo = :enable");
			 if(join){
				 sql.append(" AND ps.activo = :enable");
			 }
		 }
		 else{
			 sql.append(" WHERE p.activo = :enable ");
			 if(join){
				 sql.append("AND ps.activo = :enable ");
			 }
		 }
		 paramMap.put("enable",Enable);
		 sql.append(" ORDER BY p.id DESC LIMIT :page , :inPage");
		 paramMap.put("page", page);
		 paramMap.put("inPage", inPage);
		return jdbcTemplate.query(sql.toString(), paramMap, new ProductRowMapper(true));
	}

	@Override
	public Integer getCountProductsByFilter(Product product) {
		
		 boolean where = false;
		 StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM productos p ");
		 if((product.getSku()!=null && ! product.getSku().equals(""))||(product.getTalla()!=null && product.getTalla().intValue()>0)){
			 sql.append("LEFT JOIN productos_sku ps ON ps.producto = p.id ");
		 }
		 StringBuilder aux = new StringBuilder("");
		 Map<String,Object> paramMap = new HashMap<>();		 
		 if(product.getDescriptionEs()!=null && ! product.getDescriptionEs().equals("")){			 
			 aux.append(" p.descripcion_es like :descripcionEs");
			 paramMap.put("descripcionEs", product.getDescriptionEs());
			 where = true;
		 }
		 if(product.getDescriptionEn()!=null && ! product.getDescriptionEn().equals("")){	
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" p.descripcion_en like :descripcionEn");
			 paramMap.put("descripcionEn", product.getDescriptionEs());
			 where = true;
		 }
		 if(product.getDescriptionFr()!=null && ! product.getDescriptionFr().equals("")){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" p.descripcion_fr like :descripcionFr");
			 paramMap.put("descripcionFr", product.getDescriptionEs());
			 where = true;
		 }
		 if(product.getSku()!=null && ! product.getSku().equals("")){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.sku like :sku");
			 paramMap.put("sku", product.getSku());
			 where = true;
		 }
		 if(product.getTalla()!=null && product.getTalla().intValue()>0){
			 if(where){
				 aux.append(" and");
			 }
			 aux.append(" ps.talla = :talla");
			 paramMap.put("talla", product.getTalla());
			 where = true;
		 }
		 if(where){
			 sql.append(" WHERE");
			 sql.append(aux);
			 sql.append(" AND p.activo = :enable");			 
		 }
		 else{
			 sql.append(" WHERE p.activo = :enable");
		 }
		 paramMap.put("enable",Enable); 		
		
		List<Integer> counting = jdbcTemplate.query(sql.toString(), paramMap, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int col) throws SQLException {
				return new Integer(rs.getInt(1));
			}
			
		});
		return counting.get(0);
	}
	
	
	/* SkuProduct */
	@Override
	public List<SkuProduct> getSkuProductsByProduct(Integer idProduct) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE producto = :idProduct AND p.activo = :enable ORDER BY p.talla";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("idProduct",idProduct);
		paramMap.addValue("enable", Enable);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowMapper(true));
	}
	
	@Override
	public List<SkuProduct> getSkuProductByProductFilter(Product product) {
		StringBuilder sql = new StringBuilder("SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto");
		StringBuilder aux = new StringBuilder("");
		boolean where=false;
		Map<String,Object> paramMap = new HashMap<>();
		if(product.getSku()!=null){
			aux.append(" p.sku like :sku");
			paramMap.put("sku", product.getSku());
			where = true;
		}
		if(product.getTalla()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" p.talla = :talla");
			paramMap.put("talla", product.getTalla());
			where = true;
		}
		if(product.getGreaterThan()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" p.precio > :greater");
			paramMap.put("greater", product.getGreaterThan());
			where = true;
		}
		if(product.getLessThan()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" p.precio < :less");
			paramMap.put("less", product.getLessThan());
			where = true;
		}
		sql.append(" WHERE");
		if(where){
			sql.append(aux);
			sql.append(" AND");
		}
		sql.append(" p.producto = :producto AND p.activo = :activo");
		paramMap.put("activo", Enable);
		paramMap.put("producto", product.getId());
		sql.append(" ORDER BY p.talla DESC");
		
		return jdbcTemplate.query(sql.toString(), paramMap, new SkuProductRowMapper(true));
	}


	@Override
	public SkuProduct getSkuProductBySku(String sku) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE sku LIKE :sku AND p.activo = :enable ORDER BY p.talla";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("sku",sku);
		paramMap.addValue("enable", Enable);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowExtractor(true));
	}
	
	@Override
	public List<SkuProduct> getSkuProductBySku(Integer idProduct, String sku) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE sku LIKE :sku AND p.producto = :idProduct AND p.activo = :enable ORDER BY p.talla";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("sku",sku);
		paramMap.addValue("idProduct", idProduct);
		paramMap.addValue("enable", Enable);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowMapper(true));
	}
	
	@Override
	public List<SkuProduct> getSkuProductBySku(Integer idProduct, String sku, Integer size) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE sku LIKE :sku AND p.producto = :idProduct AND p.talla = size AND p.activo = :enable ORDER BY p.talla";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("sku",sku);
		paramMap.addValue("idProduct", idProduct);
		paramMap.addValue("size", size);
		paramMap.addValue("enable", Enable);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowMapper(true));
	}
	
	@Override
	public void createSkuProduct(SkuProduct skuProduct) {
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("producto",skuProduct.getProduct());
		paramMap.put("sku", skuProduct.getSku());
		paramMap.put("talla", skuProduct.getSize());
		paramMap.put("precio", skuProduct.getPrice());
		paramMap.put("almacen", skuProduct.getStock());
		paramMap.put("activo", Enable);
		
		skuProductInsertActor.execute(paramMap);
		
	}

	@Override
	public void updateSkuProducts(SkuProduct skuProduct) {
		String sql = "UPDATE productos_sku SET talla = :talla, precio = :precio, almacen = :almacen, sku = :sku WHERE id = :id";
		
		Map<String,Object> paramMap= new HashMap<>();
		paramMap.put("talla", skuProduct.getSize());
		paramMap.put("precio", skuProduct.getPrice());
		paramMap.put("almacen", skuProduct.getStock());
		paramMap.put("sku", skuProduct.getSku());
		paramMap.put("id", skuProduct.getId());
		
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public SkuProduct getSkuProductById(Integer idSkuProduct) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE p.id = :id ORDER BY p.talla";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idSkuProduct);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowExtractor(true));
		
	}
	
	@Override
	public SkuProduct getSkuProductByProductAndSize(Integer idProduct, Integer size) {
		String sql = "SELECT p.*, t.talla, pr.descripcion_es, pr.titulo, pr.descripcion_en, pr.descripcion_fr FROM productos_sku p LEFT JOIN tallas t ON p.talla = t.id JOIN productos pr ON pr.id = p.producto WHERE p.talla = :size AND p.producto = :idProduct AND p.activo = :enable ORDER BY p.talla";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("size",size);
		paramMap.addValue("idProduct", idProduct);
		paramMap.addValue("enable", Enable);
		return jdbcTemplate.query(sql, paramMap, new SkuProductRowExtractor(true));
	}
	

	@Override
	public void removeSkuProductByProduct(Integer idProduct) {
		String sql = "UPDATE productos_sku SET activo = :enable WHERE producto = :id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("enable",Disable);
		paramMap.addValue("id", idProduct);
		
		jdbcTemplate.update(sql, paramMap);
		
	}
	
	public void removeSkuProductById(Integer idSkuProduct) {
		String sql = "UPDATE productos_sku SET activo = :enable WHERE id = :id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("enable",Disable);
		paramMap.addValue("id", idSkuProduct);
		
		jdbcTemplate.update(sql, paramMap);
		
	}


	@Override
	public List<IdNameTable> getSizes() {
		String sql = "SELECT * FROM tallas";
		return jdbcTemplate.query(sql, new SizeRowMapper());
	}

	@Override
	public IdNameTable getSizeById(Integer id) {
		String sql = "SELECT * FROM tallas WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",id);
		return jdbcTemplate.query(sql, paramMap, new SizeRowExtractor());
	}

	@Override
	public void createSize(IdNameTable size) {
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("talla", size.getName());
		
		Number id = sizeInsertActor.executeAndReturnKey(paramMap);
		size.setId(id.intValue());
	}

	@Override
	public List<SizeDescription> getSizeDescription() {
		String sql = "SELECT md.*, t.talla FROM medidas_descripcion md LEFT JOIN tallas t on md.id_talla = t.id";		
		return jdbcTemplate.query(sql, new SizeDescriptionRowMapper(true));
	}

	@Override
	public void createSizeDescription(SizeDescription sizeDescription) {
		TransactionStatus transactionStatus = 
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			IdNameTable talla = new IdNameTable();
			talla.setName(sizeDescription.getTallaText());
			createSize(talla);
			if(talla.getId()!=null){
				Map<String,Object> paramMap = new HashMap<>();
				
				paramMap.put("pecho_alto", sizeDescription.getPechoAlto());
				paramMap.put("pecho_bajo", sizeDescription.getPechoBajo());
				paramMap.put("abdomen", sizeDescription.getAbdomen());
				paramMap.put("cadera", sizeDescription.getCadera());
				paramMap.put("hombros", sizeDescription.getHombros());
				paramMap.put("espalda", sizeDescription.getEspalda());
				paramMap.put("hombro_solo", sizeDescription.getHombroSolo());
				paramMap.put("siza", sizeDescription.getSiza());
				paramMap.put("manga", sizeDescription.getManga());
				paramMap.put("puno", sizeDescription.getPuno());
				paramMap.put("cuello", sizeDescription.getCuello());
				paramMap.put("largo_frente", sizeDescription.getLargoFrente());
				paramMap.put("largo_espalda", sizeDescription.getLargoEspalda());
				paramMap.put("id_talla", sizeDescription.getIdTalla());
				
				sizeDescriptionInsertActor.execute(paramMap);
				
				transactionManager.commit(transactionStatus);
				
			}else{
				transactionManager.rollback(transactionStatus);
			}
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
		}
		
	}

	@Override
	public void updateSizeDescription(SizeDescription sizeDescription) {
		
		String sql = "UPDATE medidas_descripcion SET pecho_alto = :pecho_alto, pecho_bajo = :pecho_bajo, abdomen = :abdomen, cadera = :cadera, hombros = :hombros, espalda = :espalda, hombro_solo = :hombro_solo, siza = :siza, manga = :manga, puno = :puno, cuello = :cuello, largo_frente = :largo_frente, largo_espalda = :largo_espalda, id_talla = :id_talla WHERE id_medidas_descripcion = :id_medidas";
		
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("pecho_alto", sizeDescription.getPechoAlto());
		paramMap.put("pecho_bajo", sizeDescription.getPechoBajo());
		paramMap.put("abdomen", sizeDescription.getAbdomen());
		paramMap.put("cadera", sizeDescription.getCadera());
		paramMap.put("hombros", sizeDescription.getHombros());
		paramMap.put("espalda", sizeDescription.getEspalda());
		paramMap.put("hombro_solo", sizeDescription.getHombroSolo());
		paramMap.put("siza", sizeDescription.getSiza());
		paramMap.put("manga", sizeDescription.getManga());
		paramMap.put("puno", sizeDescription.getPuno());
		paramMap.put("cuello", sizeDescription.getCuello());
		paramMap.put("largo_frente", sizeDescription.getLargoFrente());
		paramMap.put("largo_espalda", sizeDescription.getLargoEspalda());
		paramMap.put("id_talla", sizeDescription.getIdTalla());
		paramMap.put("id_medidas", sizeDescription.getId());
		
		jdbcTemplate.update(sql, paramMap);
		
	}

	@Override
	public ProductDetail getProductDetail(Integer idProductDetail) {
		String sql = "SELECT * FROM producto_detalles WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idProductDetail);		
		return jdbcTemplate.query(sql, paramMap, new ProductDetailRowExtractor());
	}

	@Override
	public List<Product> topProducts() {
		String sql = "SELECT p.*, SUM(od.cantidad) AS cantidad_vendida FROM productos p LEFT JOIN productos_sku ps ON p.id = ps.producto JOIN orden_detalles od ON od.id_producto_sku = ps.id JOIN ordenes o on o.id = od.orden WHERE o.estado>=5 AND o.estado<8 GROUP BY ps.producto ORDER BY cantidad_vendida DESC LIMIT 5";
		return jdbcTemplate.query(sql, new TopProductRowMapper());
	}

	@Override
	public List<SkuProduct> skuProductsBySalesProducts(Integer idProduct) {
		String sql = "SELECT ps.*, SUM(od.cantidad) AS Cantidad_individual FROM productos_sku ps LEFT JOIN orden_detalles od ON ps.id = od.id_producto_sku LEFT JOIN ordenes o ON o.id = od.orden WHERE o.estado>= :sales AND o.estado< :warning AND ps.producto = :id GROUP BY ps.id";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("sales", OrderService.approvedOrder);
		paramMap.put("warning", OrderService.warning);
		paramMap.put("id", idProduct);
		return jdbcTemplate.query(sql,paramMap,new SkuProductTopRowMapper());
	}

	@Override
	public List<IdNumTable> getSalesBySize() {
		String sql = "SELECT ps.talla, t.talla, SUM(od.cantidad), SUM((od.precio_individual * cantidad)) FROM productos_sku ps LEFT JOIN orden_detalles od  ON od.id_producto_sku = ps.id JOIN tallas t ON ps.talla = t.id JOIN ordenes o on o.id = od.orden WHERE o.estado >= :approved AND o.estado < :warning GROUP BY ps.talla";		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("approved", OrderService.approvedOrder);
		paramMap.put("warning", OrderService.warning);
		return jdbcTemplate.query(sql, paramMap, new RowMapper<IdNumTable>() {

			@Override
			public IdNumTable mapRow(ResultSet rs, int rowNum) throws SQLException {
				IdNumTable numTable = new IdNumTable();
				
				numTable.setId(rs.getInt(1));
				numTable.setName(rs.getString(2));
				numTable.setNum(rs.getInt(3));
				numTable.setDoubleAttribute(rs.getDouble(4));
				
				return numTable;
			}
		});
		
	}

	@Override
	public List<IdNameTable> getDepartments() {
		String sql = "SELECT * FROM departamentos";
		return jdbcTemplate.query(sql, new SizeRowMapper());
	}

	@Override
	public Product getProductByIdWhithoutFilter(Integer id) {
		String query = "select p.*, d.descripcion from productos p left join departamentos d on p.departamento=d.id where p.id = :id";
		SqlParameterSource productMap = new MapSqlParameterSource("id",id);
		return jdbcTemplate.query(query, productMap, new ProductRowExtractor(true));
	}

	@Override
	public List<MessageStock> getMessageStockBySkuProduct(Integer idSku) {
		String sql = "SELECT * FROM stock_message WHERE id_sku_producto = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idSku);
		return jdbcTemplate.query(sql, paramMap, new MessageStockRowMapper());
	}

	@Override
	public void deleteMessageStock(Integer id) {
		String sql = "DELETE FROM stock_message WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",id);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<Integer> getUsersIdShoppingAndWishByProduct(Integer product) {
		String sql = "SELECT DISTINCT(o.usuario) FROM orden_detalles od LEFT JOIN productos_sku ps ON od.id_producto_sku = ps.id LEFT JOIN ordenes o ON od.orden = o.id WHERE ps.producto = :product and (o.estado= :wish or o.estado = :shopping)";
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("wish", OrderService.statusWishList);
		paramMap.put("shopping", OrderService.statusShoppingCar);
		paramMap.put("product", product);
		
		return jdbcTemplate.query(sql, paramMap, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getInt(1);
			}
		});
	}

	

}
