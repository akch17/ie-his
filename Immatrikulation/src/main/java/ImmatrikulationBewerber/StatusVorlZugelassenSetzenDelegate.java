package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StatusVorlZugelassenSetzenDelegate implements JavaDelegate {

	public StatusVorlZugelassenSetzenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
	String	sql = "UPDATE `his`.`bewerber` SET `StatusID` = 8  WHERE `BewerberID` = " +   execution.getVariable("BewerberId");
	// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
	Connection connection;

	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

	Statement stmt = connection.createStatement();
 
	// Ausführen des Updates auf der DB
	stmt.executeUpdate(sql);
	 
	// Datenbank schließen
	connection.close();
	
	execution.setVariable("statusID", 8);
	System.out.println("Der Bewerber " + execution.getVariable("BewerberId") + " wurde vorläufig zugelassen");
	System.out.println(" ");

	}

}
