package learnbpm.example.gueltigkeitspruefung1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.mysql.jdbc.Blob;

public class BewerberlisteErstellenDelegate implements JavaDelegate {

	public BewerberlisteErstellenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		// Connection zu DB aufbauen; DB-Name: his; user: root; password:
		// root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement statement = connection.createStatement();

		// Bewerber aus der Bewerber-Tabelle holen
		//String sql = "SELECT BewerberID,BewerberVorname,BewerberNachname, BewerberAdresse, BewerberPLZ, BewerberEmail FROM his.bewerber";
		String sql = "SELECT * FROM his.bewerber";
		
	    System.out.println("Testausgabe SQL-Query:"+sql);

		// Ausführen des Selects auf der DB
		ResultSet resultSet = statement.executeQuery(sql);

		// Alle Bewerber in eine Hash Map speichern
		Map<Integer, String> bewerber = new HashMap<Integer, String>();
		Map<String, String> bewerberNachname1 = new HashMap<String, String>();
		Map<String, String> bewerberVorname1 = new HashMap<String, String>();
		Map<Integer, String> bewerberPLZ1 = new HashMap<Integer, String>();
		Map<String, String> bewerberAdresse1 = new HashMap<String, String>();
		Map<String, String> bewerberEmail1 = new HashMap<String, String>();
		Map<Blob, String> bewerberZeugnis1 = new HashMap<Blob, String>();
		int BewerberID;
		String BewerberVorname;
		String BewerberNachname;
		String BewerberAdresse;
		int BewerberPLZ;
		String BewerberEmail;
		Blob BewerberZeugnis;

		while (resultSet.next()) {
			BewerberID = resultSet.getInt("BewerberID");
			BewerberVorname = resultSet.getString("BewerberVorname");
			BewerberNachname = resultSet.getString("BewerberNachname");
			BewerberAdresse = resultSet.getString("BewerberAdresse");
			BewerberPLZ = resultSet.getInt("BewerberPLZ");
			BewerberEmail = resultSet.getString("BewerberEmail");
			BewerberZeugnis = (Blob) resultSet.getBlob("BewerberZeugnis");
			bewerber.put(BewerberID, resultSet.getString("BewerberID"));
			bewerberVorname1.put(BewerberVorname, resultSet.getString("BewerberVorname"));
			bewerberNachname1.put(BewerberNachname, resultSet.getString("BewerberNachname"));
			bewerberAdresse1.put(BewerberAdresse, resultSet.getString("BewerberAdresse"));
			bewerberPLZ1.put(BewerberPLZ, resultSet.getString("BewerberPLZ"));
			bewerberEmail1.put(BewerberEmail, resultSet.getString("BewerberEmail"));
			//bewerberZeugnis1.put(BewerberZeugnis, resultSet.getString("BewerberZeugnis"));
			//bewerber.put(BewerberID, resultSet.getString("BewerberLebenslauf"));
			//bewerber.put(BewerberID, resultSet.getString("BewerberPassbild"));
			//bewerber.put(BewerberID, resultSet.getString("BewerberNC"));
			System.out.println(BewerberNachname);
		}

		execution.setVariable("BewerberNachname", Variables.objectValue(bewerberNachname1).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberVorname", Variables.objectValue(bewerberVorname1).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberAdresse", Variables.objectValue(bewerberAdresse1).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberPLZ", Variables.objectValue(bewerberPLZ1).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberEmail", Variables.objectValue(bewerberEmail1).serializationDataFormat(SerializationDataFormats.JSON).create());
		//execution.setVariable("BewerberZeugnis", Variables.objectValue(bewerberZeugnis1).serializationDataFormat(SerializationDataFormats.JSON).create());
		//execution.setVariable("BewerberLebenslauf", Variables.objectValue(bewerber).serializationDataFormat(SerializationDataFormats.JSON).create());
		//execution.setVariable("BewerberPassbild", Variables.objectValue(bewerber).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberID", Variables.objectValue(bewerber).serializationDataFormat(SerializationDataFormats.JSON).create());
		//execution.setVariable("BewerberNC", Variables.objectValue(bewerber).serializationDataFormat(SerializationDataFormats.JSON).create());
		//statement.setBlob("BewerberZeugnis", Variables.objectValue(bewerberZeugnis1).serializationDataFormat(SerializationDataFormats.JSON).create());


	}

}
