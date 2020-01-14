package learnbpm.example.gueltigkeitspruefung1;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendeAblehnungsbescheid implements JavaDelegate {

	
		
		private static final String HOST = "smtp.gmail.com";
		private static final String USER = "";
		private static final String PWD = "";
		private static final Integer PORT = 465;
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("Aufnahmeschreiben senden");
		GeneriereUnvollstaendigkeitsBescheid schreiben = new GeneriereUnvollstaendigkeitsBescheid();
		String name = (String) execution.getVariable("BewerberNachnameStatus");
		schreiben.generateSchreiben(name);

		// Emailinstanz erzeugen
		Email email = new SimpleEmail();
		email.setCharset("utf-8");
		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSL(true); // veraltete Methode; Warnung kann aber ignoriert werden

		email.setFrom("noreply@thm.org");

		email.setSubject("Ablehnungsbescheid"); // Betreff
		// E-Mailtext mit integriertem Wert der Prozessvariablen
		email.setMsg(schreiben.msg);

		// Empfänger setzen ( der Einfachheit halber ist hier der Sender auch der
		// Empfänger)
		email.addTo(USER);

		email.send();

	}

}
