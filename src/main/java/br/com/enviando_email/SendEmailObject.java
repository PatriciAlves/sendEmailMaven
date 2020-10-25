package br.com.enviando_email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailObject {

	private String userName = "patricia.program@gmail.com";
	private String senha ="192168Mel";
	
	private String recipientList = "";
	private String senderName ="";
	private String subjectEmail ="";
	private String textEmail = "";
	
	
	public SendEmailObject(String recipientsList, String senderNames, String subjectsEmail, String textEmails) {
		this.recipientList = recipientsList;
		this.senderName = senderNames;
		this.subjectEmail= subjectsEmail;
		this.textEmail= textEmails;
	}
	public void sendEmail(boolean sendHtml) throws Exception {
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); // autorização
		properties.put("mail.smtp.starttls", "true");// autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com");// servidor do gmail Google
		properties.put("mail.smtp.port", "465"); // porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465");// expecifica a porta a ser conectada
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // classe socket de
																							// conexão
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		Address[] toUser = InternetAddress.parse(recipientList);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, senderName)); // quem esta enviando+ nome profissional
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(subjectEmail); //assunto do email
		
		if (sendHtml) {
			message.setContent(textEmail, "text/html; charset=utf-8");
		}else {
		message.setText(textEmail);
		}
		Transport.send(message);
		
	}
}
