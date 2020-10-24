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

public class AppTest {
	// verificar as configurações do smtp do email. Sugestão criar um email para
	// test
	private String userName = "patricia.program@gmail.com";
	private String senha ="192168Mel";
	
	@org.junit.Test

	public void testeEmail() {
		try {
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
			Address[] toUser = InternetAddress.parse("patricia.18_08@hotmail.com , souza.fagner1989@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName)); // quem esta enviando
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Primeiro email Java"); //assunto do email
			message.setText("Bom dia gato! Este email foi enviado via java. Um dia vc chega lá hahaha :)");
			
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
