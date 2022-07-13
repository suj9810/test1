package com.tech.springwebt_mail.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("she9810@gmail.com","woqbxyejvfpopnwt");
		
	}
}
