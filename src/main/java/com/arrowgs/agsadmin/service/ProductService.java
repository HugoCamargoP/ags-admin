package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.MessageStock;
import com.arrowgs.agsadmin.entities.OrderDetail;
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
	List<Product> getOnlyProducts();
	List<Product> getProductsByFilter(Product product, Integer page, Integer inPage);
	Product getProductById(Integer id);
	ProductStatus addProduct(Product product);
	ProductStatus modifyProduct(Product product);
	List<Integer> getUsersIdShoppingAndWishByProduct(Integer product);
	void removeProductById(Integer id);	
	Integer getProductsCountyFilter(Product product);
	List<Product> makeProductListByOrderedOrderDetail(List<OrderDetail> orderDetail);
		/*Top Product*/
	List<Product> topProducts();
	
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
	
	//Sales By Size
	List<IdNumTable> getSalesBySize();
	
	//Departments
	List<IdNameTable> getDepartments();
	
	//MessageStock
	List<MessageStock> getMessageStockBySkuProduct(Integer idSku);
	void sendMessageStock(Integer idSku);
}
