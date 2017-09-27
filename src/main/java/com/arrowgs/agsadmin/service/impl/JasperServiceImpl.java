package com.arrowgs.agsadmin.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.entities.Order;
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
	public ByteArrayOutputStream getReportPdf(Integer reportType, List<Order> orders) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		switch(reportType){
		case 1:
			InputStream is = JasperServiceImpl.class.getClassLoader().getResourceAsStream("/jasper/orders.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(is);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					  jasperReport, null, new JRBeanCollectionDataSource(orders));
			JRPdfExporter exporter = new JRPdfExporter();
			 
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.exportReport();
			break;
		default:
		}
		return os;
	}


}
