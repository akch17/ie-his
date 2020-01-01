package testdiagramme;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.File;

import javax.imageio.ImageIO;;

public class SendeEmailMitDokumentDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "mazlum.taycimen@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;
	public SendeEmailMitDokumentDelegate() {
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
				email.addTo("mazlum.taycimen@gmail.com");
				File file = new File("test" + ".pdf");
				file.createNewFile();
				System.out.println(file.getAbsolutePath());
				
				email.send();

	}

}
