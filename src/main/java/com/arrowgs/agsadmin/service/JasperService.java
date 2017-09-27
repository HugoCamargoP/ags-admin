package com.arrowgs.agsadmin.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.arrowgs.agsadmin.entities.Order;



public interface JasperService {
	
	public ByteArrayOutputStream getReportPdf(Integer reportType, List<Order> orders) throws Exception;

}
