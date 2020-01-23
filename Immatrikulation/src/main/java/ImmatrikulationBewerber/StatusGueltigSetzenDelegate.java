package ImmatrikulationBewerber;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StatusGueltigSetzenDelegate implements JavaDelegate {

	public StatusGueltigSetzenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String bewerberIdString = (String) execution.getVariable("BewerberId");
		Integer bewerberId = Integer.parseInt(bewerberIdString);
		execution.setVariable("BewerberId", bewerberId);

		Boolean statusvoll = (Boolean) execution.getVariable("statusvoll");

		Connection connection;
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		if (statusvoll) {
			Statement statement = connection.createStatement();
			String sql = "UPDATE his.bewerber SET StatusID = 2 WHERE (BewerberId = " + bewerberId + ")";
			statement.executeUpdate(sql);

			execution.setVariable("statusID", 2);
			execution.setVariable("gueltig", true);

			System.out.println("Die Bewerbung ist gültig");
			System.out.println(" ");
		} else {
			Statement statement2 = connection.createStatement();
			String sql2 = "UPDATE his.bewerber SET StatusID = 3 WHERE BewerberId = " + bewerberId + " ";
			statement2.executeUpdate(sql2);

			execution.setVariable("statusID", 3);
			execution.setVariable("gueltig", false);

			System.out.println("Die Bewerbung ist ungültig");
			System.out.println(" ");
		}

	}

}
