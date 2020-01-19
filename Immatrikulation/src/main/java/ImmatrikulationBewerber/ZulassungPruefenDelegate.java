package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ZulassungPruefenDelegate implements JavaDelegate {

	public ZulassungPruefenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Integer bewerberId = (Integer) execution.getVariable("BewerberId");

		String zulassungsBezeichnung;
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		String sql = "select sz.zulassungsbezeichnung from studiengangszulassung sz JOIN studiengang s "
				+ " on s.zulassungsID = sz.ZulassungsID " + " JOIN bewerber b "
				+ " on b.StudiengangID = s.StudiengangID " + " where b.BewerberID = " + bewerberId + " ";
		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(sql);
		res.first();
		zulassungsBezeichnung = (String) res.getObject(1);
		execution.setVariable("zulassungsBezeichnung", zulassungsBezeichnung);

		System.out.println(
				"Der Bewerber hat sich für einen Studiengang mit " + zulassungsBezeichnung + "-Zulassung beworben");
		System.out.println(" ");

	}

}
