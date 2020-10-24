package br.com.enviando_email;



public class AppTest {

	
	@org.junit.Test

	public void testeEmail() throws Exception {
		
		SendEmailObject sendEmail = 
				new SendEmailObject("patricia.18_08@hotmail.com", "Curso Java Test", "testando email com JAVA", "Este texto é a descrição do meu email.");
		
		sendEmail.sendEmail();
		

	}

}
