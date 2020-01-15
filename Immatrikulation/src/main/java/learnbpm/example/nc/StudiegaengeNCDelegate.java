package learnbpm.example.nc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class StudiegaengeNCDelegate implements JavaDelegate {

	public StudiegaengeNCDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<Integer> studiengangliste = new ArrayList<Integer>();

		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		String sql = "Select s.StudiengangID from studiengangszulassung z join studiengang s on z.ZulassungsID = s.ZulassungsID where z.ZulassungsID = 3 or z.ZulassungsID = 4;";
		System.out.println(sql);
		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(sql);

		res.first();
		Integer studiengang = (Integer) res.getObject(1);
		studiengangliste.add(studiengang);
		
		while (res.next()) {
			studiengang = (Integer) res.getObject(1);
			studiengangliste.add(studiengang);
		}

		System.out.println(studiengangliste);
		
		for(Integer s: studiengangliste) {
			String sqlNc = "Select z.NC from studiengangszulassung z join studiengang s on z.ZulassungsID = s.ZulassungsID where s.StudiengangID =  " + s + " "; 
			System.out.println(sqlNc);
			Statement stmtNc = connection.createStatement();

			ResultSet resNc = stmtNc.executeQuery(sqlNc);
			resNc.first();
			Integer nc = (Integer) resNc.getObject(1);
			System.out.println(nc);
			
			String sqlPlaetze = "Select StudiengangAnzahlPlätze from his.Studiengang where studiengangID = " + s + " ";
			System.out.println(sqlPlaetze);
			Statement stmtPlaetze = connection.createStatement();

			ResultSet resPlaetze = stmtPlaetze.executeQuery(sqlPlaetze);
			resPlaetze.first();
			Integer anzahlPlaetze = (Integer) resPlaetze.getObject(1);
			System.out.println(anzahlPlaetze);
			
			List<Integer> bewerberIDs = new ArrayList<Integer>();
			
			String sqlBewerberID = "select b.bewerberId from Studiengang s join bewerber b"
					+ "   on  s.StudiengangID = b.StudiengangID " 
					+ "   where b.StudiengangID = " + s + " and b.BewerberNC <= " + nc + " "  
					+ " ORDER BY b.BewerberNC ASC"
					+ " LIMIT "+ anzahlPlaetze +" ";
			System.out.println(sqlBewerberID);
			Statement stmtBewerberID = connection.createStatement();

			ResultSet resBewerberID = stmtBewerberID.executeQuery(sqlBewerberID);
			resBewerberID.first();
			
			resBewerberID.first();
			Integer bewerberID = (Integer) resBewerberID.getObject(1);
			bewerberIDs.add(bewerberID);
			
			while (resBewerberID.next()) {
				bewerberID = (Integer) resBewerberID.getObject(1);
				bewerberIDs.add(bewerberID);
			}
			System.out.println(bewerberIDs);
			
			for (Integer i : bewerberIDs) {
				String sqlStatus = "UPDATE `his`.`bewerber` SET `StatusID` = 4 WHERE `BewerberID` = " + i.toString() + " ";
				Statement stmtStatus = connection.createStatement();
				System.out.println(sqlStatus);
				stmtStatus.executeUpdate(sqlStatus);
			}
			
			connection.close();
		}

	}

}
