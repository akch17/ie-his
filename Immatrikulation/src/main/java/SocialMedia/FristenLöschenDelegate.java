package SocialMedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FristenLöschenDelegate implements JavaDelegate {

	public FristenLöschenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password:
		// root
		Connection connection;

		// Datenbankname: his
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tippspiel?user=root&password=root");

		Statement stmt = connection.createStatement();

		String sql = "DELETE from his.datum WHERE datumid BETWEEN 1 AND 2";

		// Ausführen des Updates auf der DB
		stmt.executeUpdate(sql);

		// Datenbank schließen
		connection.close();

	}

}
