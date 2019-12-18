package testdiagramme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;

public class ÜberprüfeStatusBestandenDelegate implements JavaDelegate {

	public ÜberprüfeStatusBestandenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();
	 
	String	sql =	"select StatusID from his.bewerber "
			+ " where his.bewerber.BewerberID = " + execution.getVariable("BewerberID");
			 		
	 
			ResultSet  res = stmt.executeQuery(sql);
			res.first();
			int status = res.getInt(1);
			 if  (status == 6){
				 execution.setVariable("bestanden", true);
			 }
			 else {
				 execution.setVariable("bestanden", false);
			 }
		// Datenbank schließen
		connection.close();

	}

}
