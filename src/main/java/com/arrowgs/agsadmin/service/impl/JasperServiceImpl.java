package com.arrowgs.agsadmin.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.service.JasperService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class JasperServiceImpl implements JasperService{

	@Override
	public ByteArrayOutputStream getReportPdf(Integer reportType, List<Order> orders, List<Product> products, List<IdNumTable> salesBySize) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream is;
		JRPdfExporter exporter;
		InputStream orderDetailSub;
		InputStream orderRecordSub;
		InputStream orderAmountSub;
		Map<String,Object> paramMap = new HashMap<>();
		switch(reportType){
		case 1:
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/orders.jrxml");
			orderDetailSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderDetailSubReport.jrxml");
			JasperReport orderDetailCom= JasperCompileManager.compileReport(orderDetailSub);
			orderRecordSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderRecordSubReport.jrxml");
			JasperReport orderRecordCom= JasperCompileManager.compileReport(orderRecordSub);
			orderAmountSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderAmountSubReport.jrxml");
			JasperReport orderAmountCom= JasperCompileManager.compileReport(orderAmountSub);
			
			paramMap.clear();
			paramMap.put("orderDetailSub", orderDetailCom);
			paramMap.put("orderRecordSub", orderRecordCom);
			paramMap.put("orderAmountSub", orderAmountCom);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(is);			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					  jasperReport, paramMap, new JRBeanCollectionDataSource(orders));
			exporter = new JRPdfExporter();
			 
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();
			break;
			
		case 2:
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/salesProduct.jrxml");
			
			InputStream orderDetailSubRep = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderDetailSubReport.jrxml");
			JasperReport orderDetailSubRepCom = JasperCompileManager.compileReport(orderDetailSubRep);
			paramMap.clear();
			
			paramMap.put("orderDetailSubRepCom",orderDetailSubRepCom);
			
			
			JasperReport reportSales = JasperCompileManager.compileReport(is);
			JasperPrint	 printSales = JasperFillManager.fillReport(
						reportSales, paramMap, new JRBeanCollectionDataSource(products));
			exporter = new JRPdfExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(printSales));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();
			break;
			
		case 3:
			//InputStream
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/ordersByUser.jrxml");
			orderDetailSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderDetailSubReport.jrxml");			
			orderRecordSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderRecordSubReport.jrxml");			
			orderAmountSub = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderAmountSubReport.jrxml");
			
			//Compilados
			JasperReport orderRecordComp= JasperCompileManager.compileReport(orderRecordSub);
			JasperReport orderDetailComp= JasperCompileManager.compileReport(orderDetailSub);
			JasperReport orderAmountComp= JasperCompileManager.compileReport(orderAmountSub);
			
			//Mapeo de Parámetros
			paramMap.clear();
			paramMap.put("orderDetailSub", orderDetailComp);
			paramMap.put("orderRecordSub", orderRecordComp);
			paramMap.put("orderAmountSub", orderAmountComp);
			
			//Compilado y llenado de recurso madre
			JasperReport userOrders = JasperCompileManager.compileReport(is);
			JasperPrint  userOrdersPrint = JasperFillManager.fillReport(
					userOrders, paramMap, new JRBeanCollectionDataSource(orders));
			
			//Export e Impresion
			exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(userOrdersPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();		
			break;
			
		case 4:
			//InputStream
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/stocktaking.jrxml");
			InputStream stockImages = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/productDetailsSubReport.jrxml");
			InputStream skuProduct = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/skuProductSubReport.jrxml");
			
			//Compilados
			JasperReport stockImagesComp = JasperCompileManager.compileReport(stockImages);
			JasperReport skuProductComp = JasperCompileManager.compileReport(skuProduct);
			
			//Mapeo de parámteros
			paramMap.clear();
			paramMap.put("productDetails", stockImagesComp);
			paramMap.put("skuProduct", skuProductComp);
			
			JasperReport stock = JasperCompileManager.compileReport(is);
			JasperPrint stockPrint = JasperFillManager.fillReport(
					stock, paramMap , new JRBeanCollectionDataSource(products));
			
			//Export e Impresión
			exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(stockPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();
			break;
			
		case 5:
			//Inputstream
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/salesBySizeReport.jrxml");
						
			//Compilados
			JasperReport salesBySizeComp = JasperCompileManager.compileReport(is);
			JasperPrint salesBySizePrint = JasperFillManager.fillReport(
					salesBySizeComp, null , new JRBeanCollectionDataSource(salesBySize) );
			
			//Export e Impresión
			exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(salesBySizePrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();
			break;
		
		case 6:
			is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/salesProductByUser.jrxml");
			InputStream orderDetailSubIn = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/subreport/orderDetailSubReport.jrxml");
			JasperReport orderDetailSubRepComp = JasperCompileManager.compileReport(orderDetailSubIn);
			paramMap.clear();
			
			paramMap.put("orderDetailSubRepComp",orderDetailSubRepComp);
			
			
			JasperReport reportSalesByUser = JasperCompileManager.compileReport(is);
			JasperPrint	 printSalesByUser = JasperFillManager.fillReport(
						reportSalesByUser, paramMap, new JRBeanCollectionDataSource(products));
			exporter = new JRPdfExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(printSalesByUser));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();			
			break;
		default:
		}
		return os;
	}


}
