package ZusaetzlicheArbeit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;

public class SetzeStatusBuchhaltungDelegate implements JavaDelegate {

	public SetzeStatusBuchhaltungDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement stmt = connection.createStatement();
		// JsonValue aller Bewerber, die bezahlt haben
		JsonValue bezahlt = execution.getVariableTyped("Bezahlt");

		SpinJsonNode Jsonnode = bezahlt.getValue();
		// Als Objectarray, um damit besser umzugehen
		Object[] ArrayBezahlt = Jsonnode.elements().toArray();

		String sql;
		// alle Bewerber, die bezahlt haben: setze status 9 : bezahlt
		for (int i = 0; i < ArrayBezahlt.length; i++) {
			sql = "UPDATE `his`.`bewerber` SET `StatusID` = 9  WHERE `BewerberID` = " + ArrayBezahlt[i];
			// Ausführen des Updates auf der DB
			stmt.executeUpdate(sql);
		}
		// Nun alle die nicht bezahlt haben auf nicht bezahlt setzen
		// Dazu selecten wir einfach alle mit status 8(vorläufig zugelassen) da
		// davor gesetzt wurde wer bestanden hat
		// und updaten den wert auf nicht bezahlt
		sql = "select BewerberID from his.bewerber where his.bewerber.StatusID = 8";
		ArrayList<String> NichtbezahltID = new ArrayList<String>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			NichtbezahltID.add(res.getString(1));
		}

		for (int i = 0; i < NichtbezahltID.size(); i++) {
			sql = "UPDATE `his`.`bewerber` SET `StatusID` = 7  WHERE `BewerberID` = " + NichtbezahltID.get(i);
			stmt.executeUpdate(sql);
		}

		// Datenbank schließen
		connection.close();

	}

}
