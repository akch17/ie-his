package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TestAuswertungPruefenDelegate implements JavaDelegate {

	public TestAuswertungPruefenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		Statement stmt = connection.createStatement();

		String sql = "select StatusID from his.bewerber " + " where his.bewerber.BewerberID = "
				+ execution.getVariable("BewerberId");

		ResultSet res = stmt.executeQuery(sql);
		res.first();
		int status = res.getInt(1);

		if (status == 6) {
			execution.setVariable("bestanden", true);
			execution.setVariable("statusID", 6);
		} else {
			execution.setVariable("bestanden", false);
			execution.setVariable("statusID", 7);
		}

		connection.close();

	}

}
