package com.arrowgs.agsadmin.daos;

import java.util.List;

import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;

public interface ProductDao {

	static final String ProductTable      	= "productos";
	static final String ProducDetailTable 	= "producto_detalles";
	static final String SkuProductTable   	= "productos_sku";
	static final String	SizeTable		  	= "tallas";
	static final String SizeDescriptionTable= "medidas_descripcion";
	
	static final Integer Enable = 1;
	static final Integer Disable = 0;
	
	//Product
	List<Product> getProducts();
	List<Product> getProductsByFilter(Product product, Integer page, Integer inPage);
	void addProduct(Product product);
	Product getProductById(Integer id);
	void modifyProduct(Product product);
	void modifyProduct(Product product, Integer id);	
	void removeProductById(Integer id);
	Integer getCountProductsByFilter(Product product);
	
	//SkuProduct
	List<SkuProduct> getSkuProductsByProduct(Integer idProduct);
	List<SkuProduct> getSkuProductByProductAndSize(Integer idProduct, Integer size);
	List<SkuProduct> getSkuProductBySku(Integer idProduct, String sku);
	List<SkuProduct> getSkuProductBySku(Integer idProduct, String sku, Integer size);
	void createSkuProduct(SkuProduct skuProduct);
	SkuProduct getSkuProductBySku(String sku);
	SkuProduct getSkuProductById(Integer idSkuProduct);
	void modifyListSkuProduct(List<SkuProduct> skuProducts);
	void updateSkuProducts(SkuProduct skuProduct);	
	void removeSkuProductByProduct(Integer idProduct);
	void removeSkuProductById(Integer idSkuProduct);
	
	//ProductDetail
	List<ProductDetail> getAllProductDetails();
	List<ProductDetail> getProductDetails(Integer idProduct);
	ProductDetail getProductDetail(Integer idProductDetail);
	ProductDetail getLastProductDetail();
	ProductDetail oneProductDetail(Integer idProduct);
	void addProductDetail(ProductDetail productDetail);
	void modifyProductDetail(ProductDetail productDetail);
	void removeProductDetail(Integer idProductDetail);
	void removeProductDetailByProduct(Integer idProduct);
	
	
	//Size
	void createSize(IdNameTable size);
	List<IdNameTable> getSizes();
	IdNameTable getSizeById(Integer id);
	
	//SizeDescription
	List<SizeDescription> getSizeDescription();
	void createSizeDescription(SizeDescription sizeDescription);
	void updateSizeDescription(SizeDescription sizeDescription);
	
}
