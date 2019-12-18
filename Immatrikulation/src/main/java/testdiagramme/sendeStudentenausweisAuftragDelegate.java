package testdiagramme;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class sendeStudentenausweisAuftragDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "mazlum.taycimen@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;
	public sendeStudentenausweisAuftragDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//Emailinstanz erzeugen
		Email email = new SimpleEmail();
		 
 
		//verschiedene Parameter zur Versendung setzen
	 
		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true); 
 
	 
	 
		email.setFrom("mazlum.taycimen@gmail.com"); // Replyadresse angeben
		email.setSubject("Bitte Ausweis drucken"); //Betreff

		email.setMsg("Hallo Prüfungsamt, bitte drucken Sie den Ausweis für den Studenten mit der Matrikelnummer: " + "hier die Matrikelnummer" + ".");
		email.addTo("Pruefungsamt@testhochschule.de");

		email.send();

	}

}
