package TestAuswertung;

import java.util.ArrayList;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TestinformationenAnStudentenSendenDelegate implements JavaDelegate {

	// Durch Bewerberliste iterarieren und an alle Email schreiben
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public TestinformationenAnStudentenSendenDelegate() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Emailinstanz erzeugen
		Email email = new SimpleEmail();
		ArrayList<String> emaillist = new ArrayList<String>();
		emaillist = (ArrayList<String>) execution.getVariable("BewerberEmail");
		// verschiedene Parameter zur Versendung setzen

		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true);

		email.setFrom("camundaproject12341234@gmail.com"); // Replyadresse angeben
		email.setSubject("Testerinnerung"); // Betreff

		email.setMsg("Liebe Studieninteressierte, lieber Studieninteressierter,\r\n" + "\r\n" + "\r\n"
				+ "vielen Dank für Deine Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
				+ "\r\n" + " \r\n" + "\r\n"
				+ "Bitte beachte die Termine für die Eignungsprüfungen der jeweiligen Studiengänge. Die aktuellen Termine findest du unter https://ww.hochschule-riedtal.de/eignungstesttermine. \r\n"
				+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
				+ " Hochschule Riedtal");

		for (int i = 0; i < emaillist.size(); i++) {
			email.addTo(emaillist.get(i));
		}

		email.send();

	}

}
