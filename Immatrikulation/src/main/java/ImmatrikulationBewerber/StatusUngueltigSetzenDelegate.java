package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StatusUngueltigSetzenDelegate implements JavaDelegate {

	public StatusUngueltigSetzenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Integer bewerberId = (Integer) execution.getVariable("BewerberId");
		
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		Statement statement = connection.createStatement();
		String sql = "UPDATE his.bewerber SET StatusID = 3 WHERE (BewerberId = "+bewerberId+")";
		statement.executeUpdate(sql);
		
		execution.setVariable("statusID" , 3);
		
		System.out.println("Die Bewerbung ist ungültig");
		System.out.println(" ");

	}

}
