package com.arrowgs.agsadmin.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.Product;



public interface JasperService {
	
	public ByteArrayOutputStream getReportPdf(Integer reportType, List<Order> orders, List<Product> products, List<IdNumTable> salesBySize) throws Exception;

}
