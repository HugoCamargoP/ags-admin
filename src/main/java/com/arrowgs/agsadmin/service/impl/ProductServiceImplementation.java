package com.arrowgs.agsadmin.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.ProductDao;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;
import com.arrowgs.agsadmin.service.ProductService;



@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductDao productDao;
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);
	
	@Override
	public List<Product> getProducts() {
		try{
			List<Product> products = productDao.getProducts();
			Iterator<Product> iterator = products.iterator();
			while(iterator.hasNext()){
				Product actual = iterator.next();
				actual.setProductDetails(productDao.getProductDetails(actual.getId()));
				actual.setSkuProduct(productDao.getSkuProductsByProduct(actual.getId()));
			}
			return products;
		}catch(Exception e){
			logger.error("ProductService : getProducts : "+ e.toString());
			throw e;	
		}
	}

	@Override
	public Product getProductById(Integer id) {
		try{
			Product product = productDao.getProductById(id);
			if(product!=null)
			{
				product.setProductDetails(productDao.getProductDetails(id));
				product.setSkuProduct(productDao.getSkuProductsByProduct(id));
			}
			return product;
		}catch(Exception e){
			logger.error("ProductService : getProductById : "+ e.toString());
			throw e;	
		}
	}
	
	@Override
	public List<Product> getProductsByFilter(Product product, Integer page, Integer inPage) {
		
		try{
			List<Product> products = productDao.getProductsByFilter(product, page, inPage);
			Iterator<Product> iterator = products.iterator();
			while(iterator.hasNext()){
				Product actual = iterator.next();
				actual.setProductDetails(productDao.getProductDetails(actual.getId())); 
				actual.setSkuProduct(productDao.getSkuProductsByProduct(actual.getId()));
			}
			return products;		
		}catch(Exception e){
			logger.error("ProductService : getProductsByFilter : "+ e.toString());
			throw e;
		}
	}	
	
	@Override
	public void addProduct(Product product) {
		try{
			productDao.addProduct(product);
		}catch(Exception e){
			logger.error("ProductService : addProduct : "+ e.toString());
			throw e;
		}
	}
		

	@Override
	public void modifyProduct(Product product) {
		try{
			productDao.modifyProduct(product);
		}catch(Exception e){
			logger.error("ProductService : modifyProduct : "+ e.toString());
			throw e;
		}
		
	}
	
	@Override
	public void modifySkuProductList(List<SkuProduct> skuProducts) {
		try{
			productDao.modifyListSkuProduct(skuProducts);
		}catch(Exception e){
			logger.error("ProductService : modifySkuProductList : "+ e.toString());
			throw e;
		}
	}

	@Override
	public void removeProductById(Integer id) {
		try{
			productDao.removeProductById(id); 
		}catch(Exception e){
			logger.error("ProductService : removeProductById : "+ e.toString());
			throw e;
		}
	}

	/********* ProductDetail *********/
	
	@Override
	public List<ProductDetail> getProductDetails(Integer idProduct) {
		try{
			return productDao.getProductDetails(idProduct);
		}catch(Exception e){
			logger.error("ProductService : getProductDetail : "+ e.toString());
			throw e;
		}
	}

	@Override
	public List<ProductDetail> getAllProductDetail() {
		try{
			return productDao.getAllProductDetails();
		}catch(Exception e){
			logger.error("ProductService : getAllProductDetail : "+ e.toString());
			throw e;
		}
	}

	@Override
	public void addProductDetail(ProductDetail productDetail) {
		try{
			productDao.addProductDetail(productDetail);
		}catch(Exception e){
			logger.error("ProductService : addProductDetail : "+ e.toString());
			throw e;
		}
		
	}

	@Override
	public void removeProductDetail(Integer idProductDetail) {		
		try{
			productDao.removeProductDetail(idProductDetail);
		}catch(Exception e){
			logger.error("ProductService : removeProductDetail : "+ e.toString());
			throw e;
		}
	}

	@Override
	public void modifyProductDetail(ProductDetail productDetail) {
		try{
			productDao.modifyProductDetail(productDetail);
		}catch(Exception e){
			logger.error("ProductService : modifyProductDetail : "+ e.toString());
			throw e;
		}
		
	}
	
	@Override
	public ProductDetail oneProductDetail(Integer idProduct) {		
		try{
			return productDao.oneProductDetail(idProduct);
		}catch(Exception e){
			logger.error("ProductService : oneProductDetail : "+ e.toString());
			throw e;
		}
	}	

	@Override
	public Integer getProductsCountyFilter(Product product) { 
		try{
			return productDao.getCountProductsByFilter(product);
		}catch(Exception e){
			logger.error("ProductService : getProductsCountFilter : "+ e.toString());
			throw e;
		}
	}

	@Override
	public List<SkuProduct> getSkuProductsByProduct(Integer idProduct) {
		try{
			return productDao.getSkuProductsByProduct(idProduct);
		}catch(Exception e){
			logger.error("ProductService : getSkuProductsByProduct : "+ e.toString());
			throw e;
		}
	}

	@Override
	public SkuProduct getSkuProductBySku(String sku) {		
		try{
			return productDao.getSkuProductBySku(sku);
		}catch(Exception e){
			logger.error("ProductService : getSkuProductsbySku : "+ e.toString());
			throw e;
		}
	}

	@Override
	public void updateSkuProducts(SkuProduct skuProduct) {		
		try{
			productDao.updateSkuProducts(skuProduct);
		}catch(Exception e){
			logger.error("ProductService : updateSkuProducts : "+ e.toString());
			throw e;
		}
	}

	@Override
	public SkuProduct getSkuProductById(Integer idSkuProduct) {
		try{
			return productDao.getSkuProductById(idSkuProduct);
		}catch(Exception e){
			logger.error("ProductService : getSkuProductById : "+ e.toString());
			throw e;
		}
	}

	@Override
	public void createSize(IdNameTable size) {
		try{
			productDao.createSize(size);
		}catch(Exception e){
			logger.error("ProductService : createSize : "+ e.toString());
			throw e;
		}
		
	}

	@Override
	public List<IdNameTable> getSizes() {
		List<IdNameTable> sizes;
		try{
			sizes = productDao.getSizes();
		}catch(Exception e){
			logger.error("ProductService : getSizes : "+ e.toString());
			throw e;
		}
		return sizes;
	}

	@Override
	public IdNameTable getSizeById(Integer id) {
		IdNameTable size;
		try{
			size = productDao.getSizeById(id);
		}catch(Exception e){
			logger.error("ProductService : getSizeById : "+ e.toString());
			throw e;
		}
		return size;
	}

	@Override
	public List<SizeDescription> getSizeDescription() {
		List<SizeDescription> sizes;
		try{
			sizes = productDao.getSizeDescription();
		}catch(Exception e){
			logger.error("ProductService : getSizeDescription : "+ e.toString());
			throw e;
		}
		return sizes;
	}

	@Override
	public void createSizeDescription(SizeDescription sizeDescription) {
		try{
			productDao.createSizeDescription(sizeDescription);
		}catch(Exception e){
			logger.error("ProductService : createSizeDescription : "+ e.toString());
			throw e;
		}
		
	}


}
