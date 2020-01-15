package ZusaetzlicheArbeit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;

import UtilityClasses.GenerateAblehnungsgrund;

public class VerschickeAblehnungDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public VerschickeAblehnungDelegate() {
		// TODO Auto-generated constructor stub
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

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();
		

		int BewerberID = (int) execution.getVariable("BewerberId");

		String sql = "select statusID from his.bewerber where BewerberID = " + BewerberID;
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
		Ablehnungsgrund = ablehnung.GenerateAblehnungsgrund(StatusID);

		// Nun Email erzeugen
		// Emailinstanz erzeugen
		Email email = new SimpleEmail();
		
		// das hier löschen beim zusammenführen
		execution.setVariable("BewerberEmail", "camundaproject12341234@gmail.com");
		
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

	}

}
