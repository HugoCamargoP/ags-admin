package com.arrowgs.agsadmin.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.entities.User;
import com.arrowgs.agsadmin.helpers.EmailPropertiesHelper;
import com.arrowgs.agsadmin.service.UserService;
import com.arrowgs.agsadmin.service.YetiberaMailService;


@Service
public class YetiberaMailServiceImplementation extends BasicMailServiceImplementation implements YetiberaMailService{

	private ExecutorService executorService;
	
	@Autowired
	private UserService userService;
	
	public YetiberaMailServiceImplementation() {
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void sendMessage(String[] to, String from, String[] cc, AbstractResource[] attachments, String subject,
			String msg) {
		
		final String[] 			 thisTo 		 = to;
		final String	 		 thisFrom		 = from;
		final String			 thisSubject	 = subject;
		final String			 thisMsg		 = msg;
		final String[] 			 thisCc			 = cc;
		final AbstractResource[] thisAttachments = attachments;
		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				YetiberaMailServiceImplementation.super.sendMessage(thisTo, thisFrom, thisCc, thisAttachments, thisSubject, thisMsg);
			}
		});
		
	}
	
	@Override
	public void recoverPassword(String email, String token) {
		User user = userService.getUserByEmail(email);
		if(user!=null){
			StringBuilder msg= new StringBuilder("");
			msg.append("<p>Hola</p>");
			msg.append("<p>Nos han pedido que restauremos tu cuenta de Maison Yetibera. Sigue las instrucciones si has hecho esta petici&oacute;n</p>");
			msg.append("<p>Caso contrario, ignora &eacute;ste mensaje.</p>");
			msg.append("<p>Para restablecer su contrase&ntilde;a, ingrese a este link: ");
			msg.append("http://127.0.0.1:8080/yetibera/recovery-pass?token=");
			msg.append(token);
			msg.append("</p>");
			msg.append("<p>Si dar click no funciona, puedes copiar el link y pegarlo en la barra de navegaci&oacute;n</p>");
			msg.append("<p>La vigencia del url es s&oacute;lo de 15 minutos</p>");
			String[] emails = {email};
			sendMessage(emails, null, null, null, "Contrasena olvidada", msg.toString());
		}			
		
	}

	@Override
	public void resetPassword(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendWishAndShoppingProductRemoveMessage(String product, List<Integer> users) {
		List<String> usersEmail = null;
		if(users!=null && !users.isEmpty()){
			usersEmail = new ArrayList<>();
			Iterator<Integer> iterator = users.iterator();
			while(iterator.hasNext()){
				Integer actual = iterator.next();
				User user = userService.getUserById(actual);
				usersEmail.add(user.getEmail());
			}
		}
		if(usersEmail!=null){
			StringBuilder msg= new StringBuilder("");
			msg.append("<p>Hola</p>");
			msg.append("<p>Hay productos en su perfil que ya no se encuentran disponibles y debido a ello nos hemos visto obligados a eliminarlos</p>");
			msg.append("<p>El producto eliminado ha sido: ").append(product).append("</p>");
			msg.append("<p>Lamentamos las molestias y gracias por su comprensi&oacute;n</p>");
			String[] emails = new String[usersEmail.size()];
			emails =  usersEmail.toArray(emails);
			sendMessage(emails, EmailPropertiesHelper.getEmailContact(), null, null, "Estimado Usuario", msg.toString());
		}	
		
	}

	@Override
	public void contact(String userEmail, String msg, String subject) {
		String[] to = {userEmail};
		String from = EmailPropertiesHelper.getEmailFrom();
		
		sendMessage(to, from, null, null, subject, msg);
		
	}

	
}
