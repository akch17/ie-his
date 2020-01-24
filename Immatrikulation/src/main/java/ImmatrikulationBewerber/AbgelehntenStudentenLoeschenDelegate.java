package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.mysql.jdbc.Blob;

public class AbgelehntenStudentenLoeschenDelegate implements JavaDelegate {

	public AbgelehntenStudentenLoeschenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		 
	 
		 
		
		String sqlDelete = "DELETE FROM his.bewerber WHERE bewerberid = " + execution.getVariable("BewerberId");
		Statement statementDelete = connection.createStatement();
		statementDelete.executeUpdate(sqlDelete);
	 
		
		System.out.println(" ");
	 
		System.out.println("Der abgelehnte Bewerber mit der ID " + execution.getVariable("BewerberId") + " wurde gelöscht");

	}

}
