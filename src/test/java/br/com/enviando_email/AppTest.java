package br.com.enviando_email;

import br.com.enviando_email.SendEmailObject;

public class AppTest {

	
	@org.junit.Test

	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilderTextEmail = new StringBuilder();
		stringBuilderTextEmail.append("<br/><br/>");
		stringBuilderTextEmail.append("<b>Olá, programador!</b><br/><br/>");
		stringBuilderTextEmail.append("Acesso ao curso Java <br/> <br/>");
		stringBuilderTextEmail.append("Para ter acesso ao curso click no link abaixo:<br/><br/>");
		
		stringBuilderTextEmail.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color: #2525a7; padding: 14px 25px; text-align:center; text-decoration: none; display: inline-block; border-radius: 30px; font-family:courier; border :3px solid green; background-color : #99DA39;\">Acessar Portal</a><br/><br/>");
		stringBuilderTextEmail.append("Informações para acesso: <br/><br/>");
		stringBuilderTextEmail.append("<b>Login: </b>B596@JAVA <br/>");
		stringBuilderTextEmail.append("<b>Senha:</b>B596Dev. <br/><br/>");
		stringBuilderTextEmail.append("Esperamos por você. <br>Atenciosamente:<br/>");
		stringBuilderTextEmail.append("<b>Equipe DevJava <br/><br/>");
		
		SendEmailObject sendEmail = new SendEmailObject ("patricia.18_08@hotmail.com",  "Curso Java Test", 
						"testando email com JAVA"
						,stringBuilderTextEmail.toString());
			
		sendEmail.sendEmailPdf(true);
		

	Thread.sleep(5000);
	}

}
