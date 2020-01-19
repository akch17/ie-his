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

		Connection connection;
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		Statement statement = connection.createStatement();
		String sql = "UPDATE his.bewerber SET StatusID = 2 WHERE (BewerberId = " + bewerberId + ")";
		statement.executeUpdate(sql);

		execution.setVariable("statusID", 2);

		System.out.println("Die Bewerbung ist gültig");
		System.out.println(" ");

	}

}
