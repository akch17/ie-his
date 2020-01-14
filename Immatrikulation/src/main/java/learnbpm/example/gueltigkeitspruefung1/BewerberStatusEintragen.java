package learnbpm.example.gueltigkeitspruefung1;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

public class BewerberStatusEintragen implements JavaDelegate {

	public BewerberStatusEintragen() {
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
		String sql = "UPDATE his.bewerber SET StatusID = 8 WHERE (BewerberNachname = '"+name+"')";
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
