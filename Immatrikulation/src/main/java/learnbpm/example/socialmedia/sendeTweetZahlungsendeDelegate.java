package learnbpm.example.socialmedia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class sendeTweetZahlungsendeDelegate implements JavaDelegate {

	public sendeTweetZahlungsendeDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();

		
		// ACHTUNG: hier wird das tippdatum für den Spieltipp mit der idspieltipp=3 aus der
		//Datenbank geholt. Das setzt voraus, dass es den Eintrag gibt sonst gibt es eine
		// Fehlermeldung
		String sql = "Select Fristanfang from his.datum";
		String sql2 = "SELECT Fristende FROM his.datum";
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
 
		 //Bewerberstart", Datumstart.get(0));
		 //Zahlungsfriststart", Datumstart.get(1));
		 //Bewerberende", Datumende.get(0));
		 //Zahlugsende", Datumende.get(1));
		 
 
		 
		// Hilfsmethoden zum Testen um an jeden Tweet Zufallszahl zu hängen um ihn zu unterscheiden
		// Grund: Twitter meldet Fehler wenn gleicher Tweet zweimal gesendet wird
		Random r = new Random();
		Integer zufallszahl = r.nextInt();
		 
		
		
	 
				
				java.text.SimpleDateFormat datumformat = new java.text.SimpleDateFormat("dd.MM.yyyy");
				String Datum = datumformat.format(Datumende.get(1));		
				
		//Tweetnachricht zusammenstellen
		String content =   zufallszahl.toString() + "Liebe Studierende, die Zahlungsfrist endet am "+ Datum + ". Sie haben noch eine Woche Zeit das Geld zu überweisen.";
	 
		// Zugangstoken des Camunda-Twittertestaccounts holen
		AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo", "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
	   
		//Twitternachricht instanzieren
		Twitter twittermsg = new TwitterFactory().getInstance();
		
		//Authentifizierung bei Twitter
		twittermsg.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
		twittermsg.setOAuthAccessToken(accessToken);  
		
		//Senden der Nachricht
		twittermsg.updateStatus(content);
		
		// Tweeterfolg prüfen auf https://twitter.com/camunda_demo
	}

}
