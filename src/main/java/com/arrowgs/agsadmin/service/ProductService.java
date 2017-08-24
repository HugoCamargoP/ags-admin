package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;


public interface ProductService {
	
	static enum ProductStatus{
		OK, SizeAlreadyExist, SKUAlreadyExist
	}
	
	//Product
	List<Product> getProducts();
	List<Product> getProductsByFilter(Product product, Integer page, Integer inPage);
	Product getProductById(Integer id);
	ProductStatus addProduct(Product product);
	ProductStatus modifyProduct(Product product);		
	void removeProductById(Integer id);	
	Integer getProductsCountyFilter(Product product);
	
	//SkuProduct
	List<SkuProduct> getSkuProductsByProduct(Integer idProduct);
	ProductStatus createSkuProduct(SkuProduct skuProduct);
	SkuProduct getSkuProductBySku(String sku);
	SkuProduct getSkuProductById(Integer idSkuProduct);
	ProductStatus updateSkuProducts(SkuProduct skuProduct);
	void modifySkuProductList(List<SkuProduct> skuProducts);
	void removeSkuProductByProduct(Integer idProduct);
	void removeSkuProductById(Integer idSkuProduct);
	
	//ProductDetail
	List<ProductDetail> getProductDetails(Integer idProduct);
	List<ProductDetail> getAllProductDetail();
	ProductDetail oneProductDetail(Integer idProduct);
	ProductDetail getLastProductDetail();
	void addProductDetail(ProductDetail productDetail);
	void removeProductDetail(Integer idProductDetail);
	void modifyProductDetail(ProductDetail productDetail);
	
	//Size
	void createSize(IdNameTable size);
	List<IdNameTable> getSizes();
	IdNameTable getSizeById(Integer id);
	
	//Size Description
	void createSizeDescription(SizeDescription sizeDescription);
	void updateSizeDescription(SizeDescription sizeDescription);
	public List<SizeDescription> getSizeDescription();
}
