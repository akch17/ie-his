package learnbpm.example.gueltigkeitspruefung1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BewerberStatusEintragenUngueltig implements JavaDelegate {

	public BewerberStatusEintragenUngueltig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
String name = (String) execution.getVariable("BewerberNachnameStatus");
		
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement statement = connection.createStatement();

		// Bewerber aus der Bewerber-Tabelle holen
		//String sql = "SELECT BewerberID,BewerberVorname,BewerberNachname, BewerberAdresse, BewerberPLZ, BewerberEmail FROM his.bewerber";
		String sql = "UPDATE his.bewerber SET StatusID = 3 WHERE (BewerberNachname = '"+name+"')";
		//String sql = "UPDATE `his`.`bewerber` SET `StatusID` = '2' WHERE BewerberVorname` = '"+vorname+"'";
		//String sql = "INSERT INTO `bewerber`(StatusID) VALUE ('"+vorname+"')";
		//"UPDATE `his`.`bewerber` SET `StatusID` = '2' WHERE (`BewerberID` = " + vorname);
		//REPLACE INTO his.bewerber(StatusID) VALUES(8) WHERE BewerberVorname = vorname;
		//UPDATE `his`.`bewerber` SET `StatusID` = '2' WHERE (`BewerberVorname` = 'Maho');
		
	    System.out.println("Testausgabe SQL-Query:"+sql);

		// Ausführen des Selects auf der DB
		//ResultSet resultSet = statement.executeQuery(sql);
		statement.executeUpdate(sql);

	}

}
