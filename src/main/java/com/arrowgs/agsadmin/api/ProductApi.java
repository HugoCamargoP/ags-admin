package com.arrowgs.agsadmin.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arrowgs.agsadmin.service.ProductService;
import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.helpers.ClassHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.helpers.ImagePropertiesHelper;
import com.arrowgs.agsadmin.helpers.PathHelper;;

@CrossOrigin
@RestController
public class ProductApi {

	@Autowired
	ProductService productService;	

	@RequestMapping(path = ApiMappings.Product, method = RequestMethod.GET)
	public Map<String,? extends Object> getAllProducts(){
		ResponseStatus status;
		List<Product> products;
		try{
			products = productService.getProducts();
			status = ResponseStatus.OK;
		}catch(Exception e){
			products = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, products);
	}
	
	@RequestMapping(path = ApiMappings.Product+"/{idProduct}", method = RequestMethod.GET)
	public Map<String,? extends Object> getProducts(@PathVariable Integer idProduct){
		ResponseStatus status;
		Product products;
		try{
			products = productService.getProductById(idProduct);
			status = ResponseStatus.OK;
		}catch(Exception e){
			products = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, products);
	}	
	
	@RequestMapping(path = ApiMappings.Product, method = RequestMethod.POST)
	public Map<String,? extends Object> createProduct(@RequestBody Product product){
		ResponseStatus status;
		try{
			productService.addProduct(product);
			status = ResponseStatus.OK;
		}catch(Exception e){			
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	@RequestMapping(path = ApiMappings.Product, method = RequestMethod.PUT)
	public Map<String,? extends Object> updateProduct(@RequestBody Product product){
		ResponseStatus status;
		try{
			productService.modifyProduct(product);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, product);
	}
	
	@RequestMapping(path = ApiMappings.Product+"/{id}", method = RequestMethod.DELETE)
	public Map<String,? extends Object> removeProduct(@PathVariable Integer id){
		ResponseStatus status;
		try{
			productService.removeProductById(id); 
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	@RequestMapping(path = ApiMappings.ProductDetail, method = RequestMethod.POST)
	public Map<String,? extends Object> addProductDetail(@RequestParam("file") MultipartFile imageFile, @RequestParam("product") Integer product){
		ResponseStatus status;
		try{
			ProductDetail last = productService.getLastProductDetail();
			Integer image;
			if(last==null){
				image = 1;
			}
			else{
				image = last.getId().intValue() + 1;
			}
			String path = ImagePropertiesHelper.resource();
			path = path+"\\"+image;
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
			File finalFile = new File(path);
			ImageIO.write(src, imageFile.getContentType(), finalFile);
			ProductDetail result = new ProductDetail();
			result.setProduct(product);
			result.setUrl(path);
			productService.addProductDetail(result);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	@RequestMapping(path = ApiMappings.ProductDetail, method = RequestMethod.GET)
	public Map<String,? extends Object> getAllProductDetail(){
		ResponseStatus status;
		List<ProductDetail> details;
		try{
			details = productService.getAllProductDetail();
			status = ResponseStatus.OK;
		}catch(Exception e){
			details = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, details);
	}
	
	
	@RequestMapping(path = ApiMappings.ProductDetail+"/{idProduct}", method = RequestMethod.GET)
	public Map<String,? extends Object> getProductDetails(@PathVariable Integer idProduct){
		ResponseStatus status;
		List<ProductDetail> details;
		try{
			details = productService.getProductDetails(idProduct);
			status = ResponseStatus.OK;
		}catch(Exception e){
			details = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, details);
	}

	@RequestMapping(path = ApiMappings.ProductDetail, method = RequestMethod.PUT)
	public Map<String,? extends Object> updateProductDetail(@RequestBody ProductDetail productDetail){
		ResponseStatus status;
		try{
			productService.modifyProductDetail(productDetail);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, productDetail);
	}
		
	
	@RequestMapping(path = ApiMappings.ProductDetail+"/{idProductDetail}", method = RequestMethod.DELETE)
	public Map<String,? extends Object> removeProductDetails(@PathVariable Integer idProductDetail){
		ResponseStatus status;
		try{
			productService.removeProductDetail(idProductDetail);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}	
	
	
	@RequestMapping(path = ApiMappings.ProductByFilter+"/{path} {page} {inPage}", method = RequestMethod.GET)
	public Map<String,? extends Object> getProductsByFilter(@PathVariable String path,@PathVariable Integer page ,@PathVariable Integer inPage){
		ResponseStatus status;
		Map<String,Object> params = PathHelper.fromPathToMap(path);
		Product product = ClassHelper.fromStringMap(Product.class, params);
		product.setDescription(PathHelper.sqlLike(product.getDescription()));
		List<Product> products;
		try{
			products = productService.getProductsByFilter(product, page, inPage);
			status = ResponseStatus.OK;
		}catch(Exception e){
			products = null;	
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, products);
	}
	
	@RequestMapping(path = ApiMappings.ProductCountFilter +"/{path} {inPage}", method = RequestMethod.GET)
	public Map<String,? extends Object> getCountByFilter(@PathVariable String path, @PathVariable Integer inPage){
		ResponseStatus status;
		Map<String,Object> countingMap = new HashMap<>();
		countingMap = PathHelper.fromPathToMap(path);
		Product product = ClassHelper.fromStringMap(Product.class, countingMap);
		if(product.getDescription()!=null)
		{
			product.setDescription(PathHelper.sqlLike(product.getDescription()));
		}
		countingMap.clear();
		try{
			Integer count = productService.getProductsCountyFilter(product);
			countingMap.put("total", count);
			if(count%inPage != 0){			
				count = count/inPage + 1;
			}
			else{
				count/=inPage;
			}
			countingMap.put("pages", count);
			status = ResponseStatus.OK;			
		}catch(Exception e){
			status = ResponseStatus.ExternalError;			
		}
		return ControllerHelper.mapResponse(status, countingMap);
	}
	
	@RequestMapping(path = ApiMappings.ProductSizes, method = RequestMethod.GET)
	public Map<String,? extends Object> getSizes(){
		ResponseStatus status;
		List<IdNameTable> sizes;
		try{
			sizes = productService.getSizes();
			status = ResponseStatus.OK;
		}catch(Exception e){
			sizes = null;
			status = ResponseStatus.ExternalError;
			throw e;
		}
		return ControllerHelper.mapResponse(status, sizes);
	}
	
	@RequestMapping(path = ApiMappings.ProductSizes+"/{id}", method = RequestMethod.GET)
	public Map<String,? extends Object> getSizeById(@PathVariable Integer id){
		ResponseStatus status;
		IdNameTable size;
		try{
			size = productService.getSizeById(id);
			status = ResponseStatus.OK;
		}catch(Exception e){
			size = null;
			status = ResponseStatus.ExternalError;
			throw e;
		}
		return ControllerHelper.mapResponse(status, size);
	}
	
	@RequestMapping(path = ApiMappings.ProductSizesDescription, method = RequestMethod.GET)
	public Map<String,? extends Object> getSizeDescriptions(){
		ResponseStatus status;
		List<SizeDescription> sd;
		try{
			sd = productService.getSizeDescription();
			status = ResponseStatus.OK;
		}catch(Exception e){
			sd = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, sd);
	}
	
	
	@RequestMapping(path = ApiMappings.ProductSizesDescription, method = RequestMethod.POST)
	public Map<String,? extends Object> createSizeDescription(@RequestBody SizeDescription sizeDescription){
		ResponseStatus status;
		try{
			productService.createSizeDescription(sizeDescription);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	@RequestMapping(path = ApiMappings.ProductSizesDescription, method = RequestMethod.PUT)
	public Map<String,? extends Object> updateSizeDescription(@RequestBody SizeDescription sizeDescription){
		ResponseStatus status;
		try{
			productService.updateSizeDescription(sizeDescription);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
}
