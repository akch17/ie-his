package NcAuswertung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StatusNcSetzenDelegate implements JavaDelegate {

	public StatusNcSetzenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		List<Integer> studiengangliste = new ArrayList<Integer>();

		Connection connection;
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");
		String sql = "Select s.StudiengangID from his.studiengangszulassung z join his.studiengang s on z.ZulassungsID = s.ZulassungsID where z.ZulassungsID = 3 or z.ZulassungsID = 4;";
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		if (res != null) {
			while (res.next()) {
				Integer studiengang = (Integer) res.getObject(1);
				studiengangliste.add(studiengang);
			}
		}

		for (Integer s : studiengangliste) {
			String sqlNc = "Select z.NC from his.studiengangszulassung z join his.studiengang s on z.ZulassungsID = s.ZulassungsID where s.StudiengangID =  "
					+ s + " ";
			Statement stmtNc = connection.createStatement();
			ResultSet resNc = stmtNc.executeQuery(sqlNc);
			Integer nc = null;
			if (resNc != null) {
				resNc.first();
				nc = (Integer) resNc.getObject(1);
			}
			String sqlPlaetze = "Select StudiengangAnzahlPlätze from his.Studiengang where studiengangID = " + s + " ";
			Statement stmtPlaetze = connection.createStatement();
			
			ResultSet resPlaetze = stmtPlaetze.executeQuery(sqlPlaetze);
			Integer anzahlPlaetze = 0;
			if(resPlaetze != null) {
			resPlaetze.first();
			anzahlPlaetze = (Integer) resPlaetze.getObject(1);
			}
			// Bewerber-Status auf "NC ausreichend" setzen
			List<Integer> bewerberIDs = new ArrayList<Integer>();

			String sqlBewerberID = "select b.bewerberId from his.Studiengang s join his.bewerber b"
					+ "   on  s.StudiengangID = b.StudiengangID " + "   where b.StudiengangID = " + s
					+ " and b.BewerberNC <= " + nc + " and b.StatusID = 2 " + " ORDER BY b.BewerberNC ASC" + " LIMIT "
					+ anzahlPlaetze + " ";
			Statement stmtBewerberID = connection.createStatement();
			ResultSet resBewerberID = stmtBewerberID.executeQuery(sqlBewerberID);
			if (resBewerberID != null) {

				while (resBewerberID.next()) {
					Integer bewerberID = (Integer) resBewerberID.getObject(1);
					bewerberIDs.add(bewerberID);
				}
			}

			for (Integer i : bewerberIDs) {
				String sqlStatus = "UPDATE `his`.`bewerber` SET `StatusID` = 4 WHERE `BewerberID` = " + i.toString()
						+ " ";
				Statement stmtStatus = connection.createStatement();
				stmtStatus.executeUpdate(sqlStatus);
			}

			// Bewerber-Status auf "NC nicht ausreichend" setzen
			List<Integer> bewerberIDs2 = new ArrayList<Integer>();

			String sqlBewerberID2 = "select b.bewerberId from his.Studiengang s join his.bewerber b"
					+ "   on  s.StudiengangID = b.StudiengangID " + "   where b.StudiengangID = " + s
					+ " and b.BewerberNC <= " + nc + " and b.StatusID = 2 ";
			Statement stmtBewerberID2 = connection.createStatement();

			ResultSet resBewerberID2 = stmtBewerberID2.executeQuery(sqlBewerberID2);
			if (resBewerberID2 != null) {
				while (resBewerberID2.next()) {
					Integer bewerberID2 = (Integer) resBewerberID2.getObject(1);
					bewerberIDs2.add(bewerberID2);
				}
			}
			for (Integer i : bewerberIDs2) {
				String sqlStatus2 = "UPDATE `his`.`bewerber` SET `StatusID` = 4 WHERE `BewerberID` = " + i.toString()
						+ " ";
				Statement stmtStatus2 = connection.createStatement();
				stmtStatus2.executeUpdate(sqlStatus2);
			}

		}

		connection.close();
		System.out.println("Die NC-Auswertung wurde für die folgenden Studiengang-ID durchgeführt: ");
		System.out.println(studiengangliste);
		System.out.println(" ");

	}

}
