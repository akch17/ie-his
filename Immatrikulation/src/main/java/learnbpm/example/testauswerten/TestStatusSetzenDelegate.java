package learnbpm.example.testauswerten;

import java.sql.Connection;
import java.sql.DriverManager;
 
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
 
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
 
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;


public class TestStatusSetzenDelegate implements JavaDelegate {

	public TestStatusSetzenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();
		// JsonValue aller Bewerber, die Bestanden haben
		JsonValue  Bestanden =  execution.getVariableTyped("Bestanden");
		
		SpinJsonNode Jsonnode = Bestanden.getValue();
		// Als Objectarray, um damit besser umzugehen
		Object[] ArrayBestanden =   Jsonnode.elements().toArray();
		
		String sql;
		// alle Bewerber die bestanden haben auf Status 6 bestanden setzen
		for (int i = 0; i < ArrayBestanden.length; i++) {
			sql = "UPDATE `his`.`bewerber` SET `StatusID` = 6  WHERE `BewerberID` = " + ArrayBestanden[i];
			// Ausführen des Updates auf der DB
			stmt.executeUpdate(sql);		 
		}
		 // Nun alle die nicht bestanden haben auf nicht bestanden setzen
		// Dazu selecten wir einfach alle die nicht Status 6 haben und updaten den wert auf nicht bestanden
		sql = "SELECT BewerberID "
				+ " FROM his.bewerber JOIN his.studiengang ON his.bewerber.StudiengangID = his.studiengang.StudiengangID join his.studiengangszulassung on his.studiengang.ZulassungsID = his.studiengangszulassung.ZulassungsID"
				+ " where (his.studiengang.ZulassungsID = 2  or his.studiengang.ZulassungsID = 4)"
				+ " and his.bewerber.StatusID <> 6";
		ArrayList<String> DurchgefallenID = new ArrayList<String>(); 
		ResultSet res = stmt.executeQuery(sql);
		  while (res.next()) {
			  DurchgefallenID.add(res.getString(1));
			}
		  
		for (int i = 0; i < DurchgefallenID.size(); i++) {
		sql =	"UPDATE `his`.`bewerber` SET `StatusID` = 7  WHERE `BewerberID` = " +  DurchgefallenID.get(i);
			stmt.executeUpdate(sql);		
		} 
		
		// Datenbank schließen
		connection.close();
	}

}
