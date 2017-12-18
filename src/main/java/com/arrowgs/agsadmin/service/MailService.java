package com.arrowgs.agsadmin.service;

import org.springframework.core.io.AbstractResource;

public interface MailService {
	
	void sendMessage(String[] to, String from, String[] cc, AbstractResource[] attachments, String subject, String msg);
	
}
