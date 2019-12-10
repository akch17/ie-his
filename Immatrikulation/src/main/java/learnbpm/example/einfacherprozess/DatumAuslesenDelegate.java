package learnbpm.example.einfacherprozess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.joda.time.DateTime;

public class DatumAuslesenDelegate implements JavaDelegate {

	public DatumAuslesenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();

		
		// ACHTUNG: hier wird das tippdatum für den Spieltipp mit der idspieltipp=3 aus der
		//Datenbank geholt. Das setzt voraus, dass es den Eintrag gibt sonst gibt es eine
		// Fehlermeldung
		String sql = "Select AktuellesDatum from his.datum";
		String sql2 = "SELECT festgelegteZeiten FROM his.datum";
		// Optional: Ausgabe des Klassennamens und des SQL-Strings auf der Konsole zu Debuggingzwecken
		System.out.println(this.getClass().getName()+": \n"+sql);
		
		
		// Ausführen des Selects auf der DB
		ResultSet res = stmt.executeQuery(sql);
 
		// Wert aus erste Spalte über Methode getDate als Datum auslesen
	//	Date unformatiert_tippdatum2 = res.getDate(0);
		ArrayList<Date> Datumstart = new ArrayList<Date>();
		ArrayList<Date> Datumende = new ArrayList<Date>();
		 while (res.next()) { 
			 Datumstart.add(res.getDate(1));
		}

		res = stmt.executeQuery(sql2);
		 while (res.next()) { 
			 Datumende.add(res.getDate(1));
		}
 
		 arg0.setVariable("Bewerberstart", Datumstart.get(0));
		 arg0.setVariable("Zahlungsfriststart", Datumstart.get(1));
		 arg0.setVariable("Bewerberende", Datumende.get(0));
		 arg0.setVariable("Zahlugsende", Datumende.get(1));
		 
 
		 
	}

}
