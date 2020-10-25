package br.com.enviando_email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SendEmailObject {

	private String userName = "patricia.program@gmail.com";
	private String senha = "192168Mel";

	private String recipientList = "";
	private String senderName = "";
	private String subjectEmail = "";
	private String textEmail = "";

	public SendEmailObject(String recipientsList, String senderNames, String subjectsEmail, String textEmails) {
		this.recipientList = recipientsList;
		this.senderName = senderNames;
		this.subjectEmail = subjectsEmail;
		this.textEmail = textEmails;
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
		message.setSubject(subjectEmail); // assunto do email

		if (sendHtml) {
			message.setContent(textEmail, "text/html; charset=utf-8");
		} else {
			message.setText(textEmail);
		}
		Transport.send(message);

	}

	public void sendEmailPdf(boolean sendHtml) throws Exception {

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
		message.setSubject(subjectEmail); // assunto do email

		// Primeira parte do email: descrição do email

		MimeBodyPart BodyEmail = new MimeBodyPart();

		if (sendHtml) {
			BodyEmail.setContent(textEmail, "text/html; charset=utf-8");
		} else {
			BodyEmail.setText(textEmail);
		}

		// Segunda parte do email: anexo pdf.

		MimeBodyPart AttachmentEmail = new MimeBodyPart();
		AttachmentEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(PdfSimulator(), "application/pdf")));
		AttachmentEmail.setFileName("attachmentemail.pdf");

		Multipart multipart = new MimeMultipart();

		multipart.addBodyPart(BodyEmail);
		multipart.addBodyPart(AttachmentEmail);
		message.setContent(multipart);

		Transport.send(message);

	}
	// este metodo simula o pdf ou qualquer arquivo que possa ser enviado por email

	private FileInputStream PdfSimulator() throws Exception {

		com.itextpdf.text.Document document = new com.itextpdf.text.Document();

		File file = new File("file.pdf");
		file.createNewFile();

		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo para o simulador teste do javaEmail em PDF"));
		document.close();

		return new FileInputStream(file);

	}

}
