package ZusaetzlicheArbeit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class SelectFuerBuchhaltungDelegate implements JavaDelegate {

	public SelectFuerBuchhaltungDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
				Connection connection;

				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

				Statement stmt = connection.createStatement();

			 
		// Select auf alle Bewerber mit Status "vorläufig zugelassen" ID Name Nachname
				String sqlBewerberID = "select BewerberID from his.bewerber where his.bewerber.StatusID = 8";
			 

				// Ausführen des Selects auf der DB
				ResultSet res = stmt.executeQuery(sqlBewerberID);

						 
				ArrayList<String> BewerberIDs = new ArrayList<String>();
			 
				while (res.next()) {
					BewerberIDs.add(res.getString(1));
				}
				String sqlBewerberVorname = "select Bewerbervorname from his.bewerber where his.bewerber.StatusID = 8";
			 
				ArrayList<String> BewerberVorname = new ArrayList<String>(); 
				  res = stmt.executeQuery(sqlBewerberVorname);
				  while (res.next()) {
					  BewerberVorname.add(res.getString(1));
					}
					String sqlBewerberNachname = "select Bewerbernachname from his.bewerber where his.bewerber.StatusID = 8";
				 
					ArrayList<String> BewerberNachname = new ArrayList<String>(); 
					  res = stmt.executeQuery(sqlBewerberNachname);
					  while (res.next()) {
						  BewerberNachname.add(res.getString(1));
						}
					  
				  
						  execution.setVariable("BewerberIDs",Variables.objectValue(BewerberIDs)
									.serializationDataFormat(SerializationDataFormats.JSON).create());
						   execution.setVariable("BewerberVorname",Variables.objectValue(BewerberVorname)
									.serializationDataFormat(SerializationDataFormats.JSON).create());
						execution.setVariable("BewerberNachname",Variables.objectValue(BewerberNachname)
									.serializationDataFormat(SerializationDataFormats.JSON).create());
						
						 

	}

}
