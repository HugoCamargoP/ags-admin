package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.User;

public interface YetiberaMailService extends MailService{

	public void recoverPassword(String email, String token);
	public void resetPassword(User user);
	public void sendWishAndShoppingProductRemoveMessage(String product, List<Integer> users);
	public void contact(String userEmail, String msg, String subject);
}
