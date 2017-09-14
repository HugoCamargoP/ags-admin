package com.arrowgs.agsadmin.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.arrowgs.agsadmin.entities.Order;

public interface PdfDocumentService {
	
	static final String PDF_MIME_TYPE = "application/pdf";

	ByteArrayOutputStream createDocument(List<Order> order);	

}
