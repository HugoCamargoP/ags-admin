package com.arrowgs.agsadmin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.ProductDao;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;
import com.arrowgs.agsadmin.helpers.ImagePropertiesHelper;
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
				product.setId(actual.getId());
				actual.setSkuProduct(productDao.getSkuProductByProductFilter(product));
			}
			return products;		
		}catch(Exception e){
			logger.error("ProductService : getProductsByFilter : "+ e.toString());
			throw e;
		}
	}	
	
	@Override
	public ProductStatus addProduct(Product product) {
		ProductStatus status;
		try{			
			status = ProductStatus.OK;
			if(product.getSkuProduct()!=null && !product.getSkuProduct().isEmpty())
			{
				Iterator<SkuProduct> iterator = product.getSkuProduct().iterator();
				while(iterator.hasNext()){
					SkuProduct actual = iterator.next();
					SkuProduct aux = getSkuProductBySku(actual.getSku());
					if(aux!=null){
						status = ProductStatus.SKUAlreadyExist;
						break;
					}
				}
			}
			if(status==ProductStatus.OK){
				productDao.addProduct(product);
			}
		}catch(Exception e){
			logger.error("ProductService : addProduct : "+ e.toString());
			throw e;
		}
		return status;
	}
		

	@Override
	public ProductStatus modifyProduct(Product product) {
		ProductStatus status;
		try{
			status = ProductStatus.OK;
			if(product.getSkuProduct()!=null){				
				Iterator<SkuProduct> iterator = product.getSkuProduct().iterator();
				while(iterator.hasNext()){
					SkuProduct actual = iterator.next();
					SkuProduct exist = productDao.getSkuProductByProductAndSize(product.getId(), actual.getSize());
					if(!(exist==null || exist.getId().intValue() == actual.getId().intValue())){
						status = ProductStatus.SizeAlreadyExist;
						break;
					}
					exist = null;
					exist = productDao.getSkuProductBySku(actual.getSku());
					if(!(exist==null || exist.getId().intValue() == actual.getId().intValue())){
						status = ProductStatus.SKUAlreadyExist;
						break;
					}
				}
			}
			if(status == ProductStatus.OK){
				productDao.modifyProduct(product);
			}
		}catch(Exception e){
			logger.error("ProductService : modifyProduct : "+ e.toString());
			throw e;
		}
		return status;
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
	public void removeSkuProductByProduct(Integer idProduct) {
		try{
			productDao.removeSkuProductByProduct(idProduct);
		}catch(Exception e){
			logger.error("ProductService : removeSkuProductByProduct : "+ e.toString());
			throw e;
		}
		
	}

	@Override
	public void removeSkuProductById(Integer idSkuProduct) {
		try{
			productDao.removeSkuProductById(idSkuProduct);
		}catch(Exception e){
			logger.error("ProductService : removeSkuProductById : "+ e.toString());
			throw e;	
		}
		
	}

	@Override
	public void removeProductById(Integer id) {
		try{
			List<ProductDetail> productDetails = productDao.getProductDetails(id);
			Iterator<ProductDetail> iterator = productDetails.iterator();
			while(iterator.hasNext()){
				ProductDetail actual = iterator.next();
				removeProductDetail(actual.getId());
			}
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
	public ProductDetail getLastProductDetail() {
		try{
			return productDao.getLastProductDetail();
		}catch(Exception e){
			logger.error("ProductService : getLastProductDetail : "+ e.toString());
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
			ProductDetail productDetail = productDao.getProductDetail(idProductDetail);
			Integer begin = productDetail.getUrl().lastIndexOf('/');
			String imagePath = ImagePropertiesHelper.resource();
			imagePath = imagePath+ "/" +productDetail.getUrl().substring(begin+1);
			
			File image = new File(imagePath);
			if(image.exists()){
				image.delete();
			}
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
	public ProductStatus createSkuProduct(SkuProduct skuProduct) {
		ProductStatus status;
		try{
			SkuProduct exist = productDao.getSkuProductByProductAndSize(skuProduct.getProduct(), skuProduct.getSize());
			if(exist==null)
			{
				exist = productDao.getSkuProductBySku(skuProduct.getSku());
				if(exist==null)
				{
					productDao.createSkuProduct(skuProduct);
					status = ProductStatus.OK;
				}else{
					status = ProductStatus.SKUAlreadyExist;
				}
				
			}else{
				status = ProductStatus.SizeAlreadyExist;
			}
		}catch(Exception e){
			logger.error("ProductService : createSkuProduct : "+ e.toString());
			throw e;
		}
		
		return status;
		
	}

	@Override
	public ProductStatus updateSkuProducts(SkuProduct skuProduct) {
		ProductStatus status;
		try{
			SkuProduct exist = productDao.getSkuProductByProductAndSize(skuProduct.getProduct(), skuProduct.getSize());
			if(exist==null || exist.getId().intValue() == skuProduct.getId().intValue())
			{
				exist = productDao.getSkuProductBySku(skuProduct.getSku());
				if(exist==null || exist.getId().intValue() == skuProduct.getId().intValue())
				{
					productDao.updateSkuProducts(skuProduct);
					status = ProductStatus.OK;
				}else{
					status = ProductStatus.SKUAlreadyExist;
				}
				
			}else{
				status = ProductStatus.SizeAlreadyExist;
			}

		}catch(Exception e){
			logger.error("ProductService : updateSkuProducts : "+ e.toString());
			throw e;
		}
		
		return status;
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

	@Override
	public void updateSizeDescription(SizeDescription sizeDescription) {
		try{
			productDao.updateSizeDescription(sizeDescription);
		}catch(Exception e){
			logger.error("ProductService : updateSizeDescription : "+ e.toString());
			throw e;
		}
		
	}

	@Override
	public List<Product> topProducts() {
		List<Product> top=null;
		try{
			top = productDao.topProducts();
			if(top!=null){
				Iterator<Product> iterator = top.iterator();
				while(iterator.hasNext()){
					Product product = iterator.next();
					product.setSkuProduct(productDao.skuProductsBySalesProducts(product.getId()));
				}
			}
		}catch(Exception e){
			logger.error("ProductService : topProducts : " + e.toString());
			throw e;
		}
		return top;
	}

	@Override
	public List<IdNumTable> getSalesBySize() {
		List<IdNumTable> sizeSales;
		try{
			sizeSales = productDao.getSalesBySize();
		}catch(Exception e){
			logger.error("ProductService : getSalesBySize : "+ e.toString());
			throw e;
		}
		return sizeSales;
	}

	@Override
	public List<IdNameTable> getDepartments() {
		List<IdNameTable> departments;
		try{
			departments = productDao.getDepartments();
		}catch(Exception e){
			logger.error("ProductService : getDepartments : " + e.toString());
			throw e;
		}
		return departments;
	}

	//Must be ordered by product
	@Override
	public List<Product> makeProductListByOrderedOrderDetail(List<OrderDetail> orderDetail) {
		List<Product> products=null;
		try{
			products = new ArrayList<>();
			Iterator<OrderDetail> iterator = orderDetail.iterator();
			List<OrderDetail> orderDetails = new ArrayList<>();
			int productId = -1, skuId=-1;
			while(iterator.hasNext()){
				OrderDetail actual = iterator.next();
				if(actual.getIdProductSku().intValue()!=skuId){					
					SkuProduct skuProduct = productDao.getSkuProductById(actual.getIdProductSku().intValue());
					Product lastOne;
					Product product = productDao.getProductByIdWhithoutFilter(skuProduct.getProduct());
					if(productId==-1 && orderDetails.isEmpty()){
						productId = product.getId().intValue();
						lastOne = product;
						skuId = actual.getIdProductSku().intValue();
					}
					else{
						lastOne =  productDao.getProductByIdWhithoutFilter(productDao.getSkuProductById(skuId).getProduct());
					}
					if(product.getId().intValue()!=productId){						
						List<OrderDetail> realOrder = new ArrayList<>();
						Iterator<OrderDetail> iteratorDos = orderDetails.iterator();
						while(iteratorDos.hasNext()){
							OrderDetail set = iteratorDos.next();
							realOrder.add(set);
						}
						
						lastOne.setOrdersDetails(realOrder);
						lastOne.setDescription(lastOne.getDescriptionEn());
						products.add(lastOne);
						productId = product.getId().intValue();
						orderDetails.clear();
						orderDetails.add(actual);
						skuId = actual.getIdProductSku().intValue();
					}
					else{
						orderDetails.add(actual);
						if(products!=null && products.isEmpty() && !iterator.hasNext()){
							lastOne.setOrdersDetails(orderDetails);
							products.add(lastOne);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("ProductService : makeProductListByOrderedOrderDetail : " + e.toString());
			throw e;
		}
		return products;
	}


}
