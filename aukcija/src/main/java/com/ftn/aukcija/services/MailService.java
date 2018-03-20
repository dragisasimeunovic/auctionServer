package com.ftn.aukcija.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ftn.aukcija.model.Korisnik;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendConfirmationMail(Korisnik korisnik, String task) {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom("boxboux@gmail.com");
			messageHelper.setTo("boxboux@gmail.com");
			messageHelper.setSubject("Aktivacioni mejl - AukcijaApp");
			messageHelper.setText(confirmationMailMessageText(korisnik, task));
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Aktivacioni mejl uspjesno poslat!");
		
	}
	
	public String confirmationMailMessageText(Korisnik korisnik, String task) {
		
		StringBuilder message = new StringBuilder();
		message.append("Postovani " + korisnik.getIme() + ", ");
		message.append("\n\n");
		message.append("Kako biste potvrdili registraciju kliknite na link ispod: ");
		message.append("\n\n");
		message.append("http://localhost:" + AppService.getServerPort() 
		+ "/registration/confirmRegistration?username=" + korisnik.getKorisnickoIme() 
		+ "&task=" + task);
		message.append("\n\n");
		message.append("Hvala Vam, ");
		message.append("\n");
		message.append("AukcijaApp");
		
		return message.toString();
		
	}
	
}
