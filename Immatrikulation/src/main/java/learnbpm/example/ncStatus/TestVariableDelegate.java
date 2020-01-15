package learnbpm.example.ncStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.BooleanValue;
import org.camunda.bpm.engine.variable.value.IntegerValue;

public class TestVariableDelegate implements JavaDelegate {

	public TestVariableDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		    IntegerValue test = Variables.integerValue(4);
			execution.setVariable("BewerberId", test);
			execution.setVariable("BewerberName", "TestName");
			execution.setVariable("BewerberEmail", "camundaproject12341234@gmail.com");
			execution.setVariable("Matrikelnummer", 123456);
			int bewerberID = (int) execution.getVariable("BewerberId");
			bewerberID = 3;
			execution.setVariable("BewerberId", bewerberID);
			
			
			String zulassungsBezeichnung;
			Connection connection;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

			String sql = "select sz.zulassungsbezeichnung from studiengangszulassung sz JOIN studiengang s "
					+ " on s.zulassungsID = sz.ZulassungsID "
					+ " JOIN bewerber b "
					+ " on b.StudiengangID = s.StudiengangID " 
					+ " where b.BewerberID = " +  bewerberID + " ";
			System.out.println(sql);
			Statement stmt = connection.createStatement();

			ResultSet res = stmt.executeQuery(sql);
			res.first();
			zulassungsBezeichnung = (String) res.getObject(1);
			execution.setVariable("zulassungsBezeichnung", zulassungsBezeichnung);

	}

}
