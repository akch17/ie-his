package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import UtilityClasses.GenerateAblehnungsgrund;

public class AblehnungVerschickenDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public AblehnungVerschickenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Der Status wird von dem Bewerber durch die BewerberID gelesen (DB)
		// Im Anschluss wird der Ablehnungsgrund ermittelt durch die
		// UtiltyKlasse
		// Und anschließend wird die Email versendet.
		// select statusID from his.bewerber where BewerberID = 3

		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		Statement stmt = connection.createStatement();
		

		int statusID = (int) execution.getVariable("statusID");

		String sql = "select StatusAktuell from his.Status where StatusID = " + statusID;
		int StatusID = 0;
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			StatusID = res.getInt(1);
		}

		// Datenbank schließen
		connection.close();

		// Nun den Ablehnungsgrund generieren
		String Ablehnungsgrund;
		GenerateAblehnungsgrund ablehnung = new GenerateAblehnungsgrund();
		Ablehnungsgrund = ablehnung.AblehungsgrundGenerieren(StatusID);

		// Nun Email erzeugen
		// Emailinstanz erzeugen
		Email email = new SimpleEmail();
		
		String emailadresse = (String) execution.getVariable("BewerberEmail");
		// verschiedene Parameter zur Versendung setzen

		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true);

		email.setFrom("camundaproject12341234@gmail.com"); // Replyadresse angeben
		email.setSubject("Ablehnung"); // Betreff

		email.setMsg(Ablehnungsgrund);
		email.addTo(emailadresse);

		email.send();
		
		System.out.println("Der Bewerber wird abgelehnt. ");
		System.out.println("Status: " + execution.getVariable("statusID"));

	}

}
