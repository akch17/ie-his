package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NcAuswertungPruefenDelegate implements JavaDelegate {

	public NcAuswertungPruefenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		int bewerberID = (int) execution.getVariable("BewerberId");
		int status = 0;
		
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		String sql = "Select StatusID from his.bewerber where BewerberID = "+ bewerberID + "";
		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(sql);
		boolean nc;
		res.first();
		status = (Integer) res.getObject(1);
		execution.setVariable("statusID", status);
		if (status == 4) { //Note ausreichend
		    nc = true; 
			execution.setVariable("statusNc", nc);
		}else if (status == 5) { 
			nc = false;
			execution.setVariable("statusNc", nc);
		}else {
		}
		
		
	}

}
