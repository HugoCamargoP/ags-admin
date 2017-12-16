package com.arrowgs.agsadmin.service;

import com.arrowgs.agsadmin.entities.User;

public interface YetiberaMailService extends MailService{

	public void recoverPassword(String email, String token);
	public void resetPassword(User user);
}
