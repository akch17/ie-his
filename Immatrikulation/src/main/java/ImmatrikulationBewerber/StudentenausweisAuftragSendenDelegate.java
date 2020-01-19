package ImmatrikulationBewerber;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StudentenausweisAuftragSendenDelegate implements JavaDelegate {

	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public StudentenausweisAuftragSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Emailinstanz erzeugen
		Email email = new SimpleEmail();

		// verschiedene Parameter zur Versendung setzen

		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true);

		email.setFrom("camundaproject12341234@gmail.com"); // Replyadresse angeben
		email.setSubject("Bitte Ausweis drucken"); // Betreff

		email.setMsg("Hallo Prüfungsamt, bitte drucken Sie den Ausweis für den Studenten mit der Matrikelnummer: "
				+ execution.getVariable("Matrikelnummer") + ".");
		email.addTo("camundaproject12341234@gmail.com");

		email.send();
		
		System.out.println(" ");
		System.out.println("Es wurde ein Auftrag zum Ausdrucken des Studentenausweises an das Prüfungsamt verschickt");
		System.out.println(" ");

	}

}
