package testdiagramme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StatusAufvorlZugelassenSetzenDelegate implements JavaDelegate {

	public StatusAufvorlZugelassenSetzenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	String	sql = "UPDATE `his`.`bewerber` SET `StatusID` = 8  WHERE `BewerberID` = " +   execution.getVariable("BewerberId");
	// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
	Connection connection;

	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

	Statement stmt = connection.createStatement();
 
	// Ausführen des Updates auf der DB
	stmt.executeUpdate(sql);
	 
		 
	// Datenbank schließen
	connection.close();

	}

}
