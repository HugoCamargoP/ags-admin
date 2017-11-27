package com.arrowgs.agsadmin.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SizeDescription;
import com.arrowgs.agsadmin.entities.SkuProduct;
import com.arrowgs.agsadmin.helpers.ClassHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.helpers.ImagePropertiesHelper;
import com.arrowgs.agsadmin.helpers.PathHelper;
import com.arrowgs.agsadmin.service.ProductService;
import com.arrowgs.agsadmin.service.ProductService.ProductStatus;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;;

@CrossOrigin
@RestController
public class ProductApi {

	@Autowired
	ProductService productService;	
	
	@Autowired
	MessageSource messageSource;

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
	
	@RequestMapping(path = ApiMappings.ProductSku, method = RequestMethod.POST)
	public Map<String,? extends Object> createSkuProduct(@RequestBody SkuProduct skuProduct, Locale locale){
		ResponseStatus status;	
		String error = "";
		try{
			ProductStatus productStatus = productService.createSkuProduct(skuProduct);
			status = ResponseStatus.ExternalError;
			if(productStatus == ProductStatus.OK){
				error = messageSource.getMessage("succ.Ok", null, "", locale);
				status = ResponseStatus.OK;
			}
			if(productStatus == ProductStatus.SizeAlreadyExist){
				error = messageSource.getMessage("err.sizeAlreadyExist", null, "", locale);
				status = ResponseStatus.OK;
			}
			if(productStatus == ProductStatus.SKUAlreadyExist){
				error = messageSource.getMessage("err.skuAlreadyExist", null, "", locale);				
			}

		}catch(Exception e){
			error = messageSource.getMessage("err.somethingWrong", null, "", locale);	
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null, error);
	}
	
	@RequestMapping(path = ApiMappings.ProductSku+"/{idSkuProduct}", method = RequestMethod.DELETE)
	public Map<String,? extends Object> deleteSkuProduct(@PathVariable Integer idSkuProduct){
		ResponseStatus status;	
		try{
			productService.removeSkuProductById(idSkuProduct);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
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
		return ControllerHelper.mapResponse(status, product);
	}
	
	@RequestMapping(path = ApiMappings.Product, method = RequestMethod.PUT)
	public Map<String,? extends Object> updateProduct(@RequestBody Product product, Locale locale){
		ResponseStatus status;
		ProductStatus productStatus;
		String error = "";
		try{
			productStatus = productService.modifyProduct(product);
			product = productService.getProductById(product.getId());
			if(productStatus == ProductStatus.OK){
				error = messageSource.getMessage("succ.UpdateOk	", null, "", locale);
			}
			if(productStatus == ProductStatus.SizeAlreadyExist){
				error = messageSource.getMessage("err.sizeAlreadyExist", null, "", locale);
			}
			if(productStatus == ProductStatus.SKUAlreadyExist){
				error = messageSource.getMessage("err.skuAlreadyExist", null, "", locale);				
			}
			status = ResponseStatus.OK;
		}catch(Exception e){
			error = messageSource.getMessage("err.somethingWrong", null, "", locale);	
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, product, error);
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
	
	
	//IMAGES
	@RequestMapping(path = ApiMappings.ProductDetail+"/{product}", method = RequestMethod.POST)
	public Map<String,? extends Object> addProductDetail(@RequestPart("file") MultipartFile imageFile, @PathVariable Integer product, HttpServletRequest request){
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
			
			path = path+"/"+image;
			String content = imageFile.getContentType();
			content = content.substring(6);
			path = path + "." +content;
			String imageName = ImagePropertiesHelper.localHostResource();
			imageName = imageName + image.toString() + "." + content;
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
			File finalFile = new File(path);
			if(finalFile.createNewFile()){
				ImageIO.write(src, content, finalFile);
				ProductDetail result = new ProductDetail();
				result.setProduct(product);
				result.setUrl(imageName);
				
//				BufferedImage imageBuf = ImageIO.read(finalFile);
//				
//				File compressedImageFile = new File(ImagePropertiesHelper.resource()+"/compressed."+content);
//				OutputStream os = new FileOutputStream(compressedImageFile);
//				
//				Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(content);
//				ImageWriter writer = (ImageWriter) writers.next();
//				
//				ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//			    writer.setOutput(ios);
//				
//			    ImageWriteParam param = writer.getDefaultWriteParam();
//			    
//			    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//			    param.setCompressionQuality(0.5f);
//			    writer.write(null, new IIOImage(imageBuf, null, null), param);
//			      
//			    os.close();
//			    ios.close();
//			    writer.dispose();	
			    
				productService.addProductDetail(result);
				status = ResponseStatus.OK;
			}
			else{
				status = ResponseStatus.ExternalError;
			}
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	@RequestMapping(path = ApiMappings.ProductDetail+"/{product}/list", method = RequestMethod.POST)
	public Map<String,? extends Object> addProductDetailList(@RequestPart("file") List<MultipartFile> imageFiles, @PathVariable Integer product, HttpServletRequest request, Locale locale){
		ResponseStatus status = ResponseStatus.OK;
		String error = "", errorFinal="";
		try{
			if(imageFiles!=null){
				ProductDetail last = productService.getLastProductDetail();
				Integer image;
				if(last==null){
					image = 1;
				}
				else{
					image = last.getId().intValue() + 1;
				}
				
				Iterator<MultipartFile> iterator = imageFiles.iterator();			
				boolean begin = false;
				while(iterator.hasNext()){
					if(!begin&&last!=null){
						last = productService.getLastProductDetail();
						image = last.getId().intValue() + 1;
					}else{
						image++;
					}
					MultipartFile imageFile = iterator.next();
					byte[] data = imageFile.getBytes();
					MagicMatch match = Magic.getMagicMatch(data);
					String mimeType = match.getMimeType();				
					
					String path = ImagePropertiesHelper.resource();
					if(mimeType.contains("image")){				
						path = path+"/"+image;
						String content = imageFile.getContentType();
						content = content.substring(6);
						path = path + "." +content;
						String imageName = ImagePropertiesHelper.localHostResource();
						imageName = imageName + image.toString() + "." + content;
						BufferedImage src = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
						File finalFile = new File(path);
						if(finalFile.createNewFile()){
							ImageIO.write(src, content, finalFile);
							ProductDetail result = new ProductDetail();
							result.setProduct(product);
							result.setUrl(imageName);							
							
//							BufferedImage imageBuf = ImageIO.read(finalFile);
//							
//							File compressedImageFile = new File(ImagePropertiesHelper.resource()+"/compressed."+content);
//							OutputStream os = new FileOutputStream(compressedImageFile);
//							
//							Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(content);
//							ImageWriter writer = (ImageWriter) writers.next();
//							
//							ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//						    writer.setOutput(ios);
//							
//						    ImageWriteParam param = writer.getDefaultWriteParam();
//						    
//						    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//						    param.setCompressionQuality(0.05f);
//						    writer.write(null, new IIOImage(imageBuf, null, null), param);
//						      
//						    os.close();
//						    ios.close();
//						    writer.dispose();						    
						    
						    productService.addProductDetail(result);
							status = ResponseStatus.OK;
						}
						else{
							status = ResponseStatus.ExternalError;
						}
					}
					else{
						error = error + imageFile.getOriginalFilename() + "||";
					}
				}
			}else{
				status= ResponseStatus.ExternalError;
			}
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
			errorFinal = messageSource.getMessage("err.ImagesUpload", null, "", locale);
		}
		if(!error.equals("")){
			errorFinal = messageSource.getMessage("err.ImagesUpload", null, "", locale) + "||";
			errorFinal = errorFinal + error;
			
		}
		return ControllerHelper.mapResponse(status, null,errorFinal);
	}
	
	
//	@RequestMapping(path = ApiMappings.ProductDetail+"/{product}/array", method = RequestMethod.POST)
//	public Map<String,? extends Object> addProductDetailArray(@RequestPart("file") MultipartFile[] imageFiles, @PathVariable Integer product, HttpServletRequest request){
//		ResponseStatus status;
//		try{
//			ProductDetail last = productService.getLastProductDetail();
//			Integer image;
//			if(last==null){
//				image = 1;
//			}
//			else{
//				image = last.getId().intValue() + 1;
//			}
//			
//			status = ResponseStatus.ExternalError;
//			boolean begin = false;
//			for(MultipartFile imageFile : imageFiles){
//				if(!begin&&last!=null){
//					last = productService.getLastProductDetail();
//					image = last.getId().intValue() + 1;
//				}else{
//					image++;
//				}
//				
//				String path = ImagePropertiesHelper.resource();
//				
//				path = path+"/"+image;
//				String content = imageFile.getContentType();
//				content = content.substring(6);
//				path = path + "." +content;
//				String imageName = ImagePropertiesHelper.localHostResource();
//				imageName = imageName + image.toString() + "." + content;
//				BufferedImage src = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
//				File finalFile = new File(path);
//				if(finalFile.createNewFile()){
//					ImageIO.write(src, content, finalFile);
//					ProductDetail result = new ProductDetail();
//					result.setProduct(product);
//					result.setUrl(imageName);
//					productService.addProductDetail(result);
//					status = ResponseStatus.OK;
//				}
//				else{
//					status = ResponseStatus.ExternalError;
//				}
//			}
//		}catch(Exception e){
//			status = ResponseStatus.ExternalError;
//		}
//		return ControllerHelper.mapResponse(status, null);
//	}
	
	//END IMAGES
	
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
		if(product.getDescription()!=null)
		{
			product.setDescription(PathHelper.sqlLike(product.getDescription()));
		}
		if(product.getSku()!=null){
			product.setSku(PathHelper.sqlLike(product.getSku()));
		}
		if(product.getTitle()!=null){
			product.setTitle(PathHelper.sqlLike(product.getTitle()));
		}
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
	
	
	@RequestMapping(path = ApiMappings.Departments, method = RequestMethod.GET)
	public Map<String,? extends Object> getDepartments(){
		ResponseStatus status;
		List<IdNameTable> departments;
		try{
			departments = productService.getDepartments();
			status = ResponseStatus.OK;
		}catch(Exception e){
			departments = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, departments);
	}
	
}
