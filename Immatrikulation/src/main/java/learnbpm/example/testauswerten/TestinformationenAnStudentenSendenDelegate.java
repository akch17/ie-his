package learnbpm.example.testauswerten;

import java.util.ArrayList;
 

 

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
 

public class TestinformationenAnStudentenSendenDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public TestinformationenAnStudentenSendenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//Emailinstanz erzeugen
		Email email = new SimpleEmail();
		ArrayList<String> emaillist = new ArrayList <String>();
		emaillist = (ArrayList<String>) execution.getVariable("BewerberEmail");
		//verschiedene Parameter zur Versendung setzen
	 
		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true); 
 
	 
	 
		email.setFrom("mazlum.taycimen@gmail.com"); // Replyadresse angeben
		email.setSubject("Testerinnerung"); //Betreff

		email.setMsg("Lieber Bewerber, bitte denken Sie an Ihren Testtermin. Den Termin könnten Sie auf unserer Internetseite:https://www.testhochschule.de/testtermine entnehmen! Wir wünschen Ihnen viel Erfolg.");
		
	//	email.addTo("öäüüÖÄÜ@gmail.com");
	// 	for (int i = 0; i < emaillist.size() ;i++){
	// 	email.addTo(emaillist.get(i));
	// 	}
	 
 

	//	email.send();

	 
 
	}

}
