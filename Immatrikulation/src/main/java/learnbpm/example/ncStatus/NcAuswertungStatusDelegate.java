package learnbpm.example.ncStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.BooleanValue;

public class NcAuswertungStatusDelegate implements JavaDelegate {

	public NcAuswertungStatusDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		
		int bewerberID = (int) execution.getVariable("BewerberId");
		
		//TODO
		int status = 0;
		
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		String sql = "Select StatusID from his.bewerber where BewerberID = "+ bewerberID + "";
		System.out.println(sql);
		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(sql);
		boolean nc;
		res.first();
		status = (Integer) res.getObject(1);
		//TODO
		status = 4;
		System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEST");
		if (status == 4) { //Note ausreichend
		    nc = true; 
		    BooleanValue test = Variables.booleanValue(true);
			execution.setVariable("statusNc", test);
		}else if (status == 5) { //Note nicht ausreichend
			BooleanValue test = Variables.booleanValue(false);
			nc = false;
			execution.setVariable("statusNc", test);
		}else {
		}
		
		
	}

}
