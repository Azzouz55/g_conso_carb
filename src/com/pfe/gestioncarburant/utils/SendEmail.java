package com.pfe.gestioncarburant.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void envoyer(String recepteur, String object, String subject) throws MessagingException {
		final String username = "gestionCarburant7@gmail.com";
		final String password = "gestioncarburant00";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.fallback", "true");
		props.put("mail.smtp.ssl.socketFactory", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from-email@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepteur));
		message.setSubject(subject);
		message.setText(object);
		Transport.send(message);
		System.out.println("Done");
	}
	// public static void main(String[] args) {
	//
	// envoyer("mouradbounasri@gmail.com", "test", "123");
	//
	//
	// }
}
