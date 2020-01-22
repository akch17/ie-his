package ImmatrikulationBewerber;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class BewerberdatenSelektierenDelegate implements JavaDelegate {

	public BewerberdatenSelektierenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		Statement statement = connection.createStatement();
		
		//TODO NC und PDFs hinzufügen
		// Bewerber aus der Bewerber-Tabelle holen
		String sql = "SELECT BewerberID,BewerberVorname,BewerberNachname, BewerberAdresse, BewerberPLZ, BewerberEmail, BewerberNC, BewerberZeugnis, BewerberPassbild, BewerberLebenslauf  FROM his.bewerber where bewerberID = "
				+ execution.getVariable("BewerberId") + " ";

		// Ausführen des Selects auf der DB
		ResultSet resultSet = statement.executeQuery(sql);

//		// Alle Bewerber in eine Hash Map speichern
		String bewerberNachname;
		String bewerberVorname;
		int bewerberPLZ;
		String bewerberAdresse;
		String bewerberEmail;
		Blob bewerberZeugnis = null;
		Blob bewerberLebenslauf = null;
		Blob bewerberPassbild = null;
		double nc;
		
		
		
		

		while (resultSet.next()) {
			bewerberVorname = resultSet.getString("BewerberVorname");
			bewerberNachname = resultSet.getString("BewerberNachname");
			bewerberAdresse = resultSet.getString("BewerberAdresse");
			bewerberPLZ = resultSet.getInt("BewerberPLZ");
			bewerberEmail = "camundaproject12341234@gmail.com";
			nc = resultSet.getDouble("BewerberNC");
			
			try {
			bewerberZeugnis = (Blob) resultSet.getBlob("BewerberZeugnis");
			bewerberLebenslauf = (Blob) resultSet.getBlob("BewerberLebenslauf");
			bewerberPassbild = (Blob) resultSet.getBlob("BewerberPassbild");
			}catch(Exception e) {
				System.out.println("Die Bewerberdaten konnten nicht selektiert werden");
			}

			execution.setVariable("BewerberNachname", Variables.objectValue(bewerberNachname)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberVorname", Variables.objectValue(bewerberVorname)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberAdresse", Variables.objectValue(bewerberAdresse)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberPLZ",
					Variables.objectValue(bewerberPLZ).serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberEmail", Variables.objectValue(bewerberEmail)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberNc", Variables.objectValue(nc)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			
			
			try {
			execution.setVariable("BewerberZeugnis", Variables.objectValue(bewerberZeugnis)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberLebenslauf", Variables.objectValue(bewerberLebenslauf)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			execution.setVariable("BewerberPassbild", Variables.objectValue(bewerberPassbild)
					.serializationDataFormat(SerializationDataFormats.JSON).create());
			}catch(Exception e){
				System.out.println("Die Bewerberdokumente konnten nicht geladen werden");
			}
			

			execution.setVariable("BewerberEmail", "camundaproject12341234@gmail.com");
			
			
			System.out.println("Der Bewerber mit der ID: " + execution.getVariable("BewerberId") + " wurde ausgewählt");
			System.out.println("Bewerber-Vorname: " + execution.getVariable("BewerberVorname") + " ");
			System.out.println("Bewerber-Nachname: "  + execution.getVariable("BewerberNachname") + " ");
			System.out.println("PLZ: " + execution.getVariable("BewerberPLZ") + " ");
			System.out.println("Adresse: " + execution.getVariable("BewerberAdresse") + " ");
			System.out.println(" ");
		}

	}
}