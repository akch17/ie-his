package ImmatrikulationBewerber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.mysql.jdbc.Blob;

public class BewerberlisteErstellenDelegate implements JavaDelegate {

	public BewerberlisteErstellenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		String sql = "SELECT BewerberId FROM his.bewerber where statusID = 1";
		Map<String, String> bewerberliste = new HashMap<String, String>();
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		res.first();
		Integer bewerber;
		
		while (res.next()) {
			bewerber = (Integer) res.getObject(1);
			bewerberliste.put(bewerber.toString(), bewerber.toString());
		}

		execution.setVariable("ALLE_BEWERBER", Variables.objectValue(bewerberliste)
				.serializationDataFormat(SerializationDataFormats.JSON).create());
		

	}

}
