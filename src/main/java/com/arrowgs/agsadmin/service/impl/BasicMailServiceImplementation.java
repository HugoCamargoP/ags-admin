package com.arrowgs.agsadmin.service.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.arrowgs.agsadmin.service.MailService;



public class BasicMailServiceImplementation implements MailService{

    private JavaMailSender mailSender;	
    private SimpleMailMessage templateMessage;
    private static Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	@Override
	public void sendMessage(String[] to, String from, String[] cc, AbstractResource[] attachments, String subject, String msg) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true);
			
			for( String t : to ){
				msgHelper.addTo(t);
			}
			
			if( cc != null ){
				for( String c : cc ){
					msgHelper.addTo(c);
				}
			}
			
			if( attachments != null ){
				for( AbstractResource res : attachments ){
					msgHelper.addAttachment(res.getFilename(), res);
				}
			}
			
			if( from != null && from.length() > 0 ){
				msgHelper.setFrom(from);
			} else {
				msgHelper.setFrom(templateMessage.getFrom());
			}
			
			if( subject != null && subject.length() > 0 ){
				msgHelper.setSubject(subject);
			} else {
				msgHelper.setSubject(templateMessage.getSubject());
			}
			
			if( msg != null && msg.length() > 0 ){
				msgHelper.setText(msg, true);
			} else {
				msgHelper.setText(templateMessage.getText(), true);
			}
		
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			logger.error("BasicMailServiceImpl sendMessage(String[] to, String from, String[] cc, AbstractResource[] attachments, String subject, String msg): " + e.toString());
			e.printStackTrace();
		}
	}
	
}
