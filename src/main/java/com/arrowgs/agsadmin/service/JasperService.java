package com.arrowgs.agsadmin.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.User;



public interface JasperService {
	
	public ByteArrayOutputStream getReportPdf(Integer reportType, List<Order> orders, List<Product> products, List<IdNumTable> salesBySize) throws Exception;
	
	public ByteArrayOutputStream getTopFivePdf(List<Order> orders, List<Product> products, List<IdNumTable> countries, List<User> users) throws Exception;

}
