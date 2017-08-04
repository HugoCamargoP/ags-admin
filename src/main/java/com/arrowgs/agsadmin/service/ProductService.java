package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;


public interface ProductService {
	
	//Product
	List<Product> getProducts();
	List<Product> getProductsByFilter(Product product, Integer page, Integer inPage);
	Product getProductById(Integer id);
	void addProduct(Product product);
	void modifyProduct(Product product);		
	void removeProductById(Integer id);	
	Integer getProductsCountyFilter(Product product);
	
	//SkuProduct
	List<SkuProduct> getSkuProductsByProduct(Integer idProduct);
	SkuProduct getSkuProductBySku(String sku);
	SkuProduct getSkuProductById(Integer idSkuProduct);
	void updateSkuProducts(SkuProduct skuProduct);
	void modifySkuProductList(List<SkuProduct> skuProducts);
	
	//ProductDetail
	List<ProductDetail> getProductDetails(Integer idProduct);
	List<ProductDetail> getAllProductDetail();
	ProductDetail oneProductDetail(Integer idProduct);
	void addProductDetail(ProductDetail productDetail);
	void removeProductDetail(Integer idProductDetail);
	void modifyProductDetail(ProductDetail productDetail);
	
	//Size
	void createSize(IdNameTable size);
	List<IdNameTable> getSizes();
	IdNameTable getSizeById(Integer id);
	
	//Size Description
	void createSizeDescription(SizeDescription sizeDescription);
	public List<SizeDescription> getSizeDescription();
}
