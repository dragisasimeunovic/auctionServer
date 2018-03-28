package com.ftn.aukcija.services;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ftn.aukcija.constants.Constants;
import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.model.Ponuda;
import com.ftn.aukcija.model.ZahtevZaNabavku;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private PonudaService ponudaService;

	// confirmation mail
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
	
	// nema kategorije mail
	public void noCompaniesFromCategoryMail(ZahtevZaNabavku zahtevZaNabavku, Korisnik korisnik, String task) {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom("boxboux@gmail.com");
			messageHelper.setTo("boxboux@gmail.com");
			messageHelper.setSubject("Odbijen zahtjev - AukcijaApp");
			messageHelper.setText(noCompaniesFromCategoryText(zahtevZaNabavku, korisnik, task));
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Aktivacioni mejl uspjesno poslat!");
		
	}
	
	
	public String noCompaniesFromCategoryText(ZahtevZaNabavku zahtevZaNabavku, Korisnik korisnik, String task) {
		
		String imeKategorije = kategorijaService.findBySifra(zahtevZaNabavku.getKategorija()).getIme();
		
		StringBuilder message = new StringBuilder();
		message.append("Postovani " + korisnik.getIme() + ", ");
		message.append("\n\n");
		message.append("Vas zahtjev za nabavku trenutno nije moguce izvrsiti. Ne postoji nijedna firma iz kategorije : ");
		message.append("\n\n");
		message.append(imeKategorije);
		message.append("\n\n");
		message.append("Hvala Vam, ");
		message.append("\n");
		message.append("AukcijaApp");
		
		return message.toString();
		
	}
	
	// manje firmi nego ponuda mail
		public void lackOfFirmsMail(ZahtevZaNabavku zahtjevZaNabavku, Korisnik korisnik, String task, ArrayList<Firma> firme) {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper;
			try {
				messageHelper = new MimeMessageHelper(message, true);
				messageHelper.setFrom("boxboux@gmail.com");
				messageHelper.setTo("boxboux@gmail.com");
				messageHelper.setSubject("Upozorenje - AukcijaApp");
				messageHelper.setText(lackOfFirmsMailText(zahtjevZaNabavku, korisnik, task, firme));
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			System.out.println("Mejl upozorenja uspjesno poslat!");
			
		}
		
		
		public String lackOfFirmsMailText(ZahtevZaNabavku zahtevZaNabavku, Korisnik korisnik, String task, ArrayList<Firma> firme) {
			
			StringBuilder message = new StringBuilder();
			message.append("Postovani " + korisnik.getIme() + ", ");
			message.append("\n\n");
			message.append("Broj firmi koje ispunjavaju uslove iz zahtjeva je manji od minimalnog broja ponuda koji ste naveli u zahtjevu. Trenutne firme koje ispunjavaju zathjev su: ");
			message.append("\n");
			for (Firma firma : firme) {
				message.append("\n- " + firma.getIme());
			}
			message.append("\n\n");
			message.append("Ukoliko se slazete da posaljete ponude na navedene firme kliknite na link ispod: ");
			message.append("\n\n");
			message.append("http://localhost:" + AppService.getServerPort() 
			+ "/nabavka/lackOfFirmsDecision?decision=" + Constants.YES 
			+ "&task=" + task);
			message.append("\n\n");
			message.append("Ukoliko se ne slazete, kliknite na link ispod: ");
			message.append("\n\n");
			message.append("http://localhost:" + AppService.getServerPort() 
			+ "/nabavka/lackOfFirmsDecision?decision=" + Constants.NO 
			+ "&task=" + task);
			message.append("\n\n");
			message.append("Hvala Vam, ");
			message.append("\n");
			message.append("AukcijaApp");
			
			return message.toString();
			
		}
		
		public void sendSupplyRequest(ZahtevZaNabavku zahtjevZaNabavku, String task, Firma firma){
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper;
			try {
				messageHelper = new MimeMessageHelper(message, true);
				messageHelper.setFrom("boxboux@gmail.com");
				messageHelper.setTo("boxboux@gmail.com");
				messageHelper.setSubject("Upozorenje - AukcijaApp");
				messageHelper.setText(sendSupplyRequestMailText(zahtjevZaNabavku, task, firma));
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			Ponuda ponuda = new Ponuda(null, zahtjevZaNabavku, firma, Constants.PONUDA_CEKANJE, 0.0, "");
			ponudaService.save(ponuda);
			
			System.out.println("Mejl upozorenja uspjesno poslat firmi: " + firma.getIme());
				
		}
		
		public String sendSupplyRequestMailText(ZahtevZaNabavku zahtevZaNabavku, String task, Firma firma) {
			
			StringBuilder message = new StringBuilder();
			message.append("Postovani " + firma.getAgent().getIme() + ", ");
			message.append("\n\n");
			message.append("Primili ste zahtjev za nabavku od korisnika " + zahtevZaNabavku.getKorisnik().getKorisnickoIme());
			message.append("\n\n");
			message.append("Ukoliko zelite da posaljete ponudu kliknite na link ispod, a zatim popunite ponudu: ");
			message.append("\n\n");
			message.append("LINK_DA");
			message.append("\n\n");
			message.append("Ukoliko ne zelite da posaljete ponudu kliknite na link ispod: ");
			message.append("\n\n");
			message.append("LINK_NE");
			message.append("\n\n");
			message.append("Hvala Vam, ");
			message.append("\n");
			message.append("AukcijaApp");
			
			return message.toString();
			
		}
	
	
	
}
