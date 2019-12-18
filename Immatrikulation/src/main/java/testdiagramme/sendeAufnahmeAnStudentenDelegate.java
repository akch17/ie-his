package testdiagramme;

import java.util.ArrayList;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class sendeAufnahmeAnStudentenDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "mazlum.taycimen@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;
	public sendeAufnahmeAnStudentenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//Emailinstanz erzeugen
				Email email = new SimpleEmail();
				 
		 String emailadresse =  (String) execution.getVariable("BewerberEmail");
				//verschiedene Parameter zur Versendung setzen
			 
				email.setHostName(HOST);
				email.setAuthentication(USER, PWD);
				email.setSmtpPort(PORT);
				email.setSSLOnConnect(true); 
		 
			 
			 
				email.setFrom("mazlum.taycimen@gmail.com"); // Replyadresse angeben
				email.setSubject("Aufnahmeerklaerung"); //Betreff

				email.setMsg("Lieber Bewerber, hiermit erhalten Sie Ihre vorläufige Aufnahmeerklärung. Bitte denken Sie an die Zahlungsfrist!");
				email.addTo(emailadresse);

				email.send();

	}

}
