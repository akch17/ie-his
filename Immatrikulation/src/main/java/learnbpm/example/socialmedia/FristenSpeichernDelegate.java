package learnbpm.example.socialmedia;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FristenSpeichernDelegate implements JavaDelegate {

	public FristenSpeichernDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();

		Date Bewerbungsstart = (Date) execution.getVariable("Bewerbungsstart");
		Date Bewerbungsende = (Date) execution.getVariable("Bewerbungsende");
		Date Zahlungsfriststart = (Date) execution.getVariable("Zahlungsfriststart");
		Date Zahlungsfristende = (Date) execution.getVariable("Zahlungsfristende");

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String Bewerbungsstartformat = sdf.format(Bewerbungsstart);
		String Bewerbungsendeformat = sdf.format(Bewerbungsende);
		String Zahlungsfriststartformat = sdf.format(Zahlungsfriststart);
		String Zahlungsfristendeformat = sdf.format(Zahlungsfristende);

		// Fristen Bewerbung in die Datenbank
		String sql = "INSERT INTO `his`.`datum` (`DatumID`, `Fristanfang`, `Bezeichnung`,`Fristende`) "
				+ "VALUES (?, ?, ?, ?)";

		PreparedStatement fristen = null;
		fristen = connection.prepareStatement(sql);

		fristen.setInt(1, 1);
		fristen.setString(2, Bewerbungsstartformat);
		fristen.setString(3, "Bewerberzeitraum");
		fristen.setString(4, Bewerbungsendeformat);

		fristen.executeUpdate();

		try {
			fristen.close();
		} catch (Exception e) {

		}

		// Fristen Zahlung in die Datenbank
		sql = "INSERT INTO `his`.`datum` (`DatumID`, `Fristanfang`, `Bezeichnung`,`Fristende`) "
				+ "VALUES (?, ?, ?, ?)";

		fristen = null;
		fristen = connection.prepareStatement(sql);

		fristen.setInt(1, 2);
		fristen.setString(2, Zahlungsfriststartformat);
		fristen.setString(3, "Zahlungszeitraum");
		fristen.setString(4, Zahlungsfristendeformat);

		fristen.executeUpdate();

		try {
			fristen.close();
		} catch (Exception e) {

		}

		// Datenbank schließen
		connection.close();

	}

}
