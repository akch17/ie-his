package testdiagramme;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import com.mysql.jdbc.Blob;

public class BewerberZuStudentDelegate implements JavaDelegate {

	public BewerberZuStudentDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");

		Statement statement;
		statement = connection.createStatement();
		int id;
		// Matrikelnummer generieren
		String sql = "select max(StudentID) from his.Student";
		ResultSet resultSet;
		resultSet = statement.executeQuery(sql);
		id = 0;

		while (resultSet.next()) {
			id = resultSet.getInt("max(StudentID)");
			id++;
			execution.setVariable("Matrikelnummer", id);

		}

		// Bewerber aus der Bewerber-Tabelle holen
		// String sql = "SELECT BewerberID,BewerberVorname,BewerberNachname,
		// BewerberAdresse, BewerberPLZ, BewerberEmail FROM his.bewerber";
		execution.setVariable("BewerberID", 13);
		sql = "select * from his.bewerber where bewerberID = " + execution.getVariable("BewerberID");
		// Ausführen des Selects auf der DB
		resultSet = statement.executeQuery(sql);


		String BewerberVorname;
		String BewerberNachname;
		String BewerberAdresse;
		int BewerberPLZ;
		String BewerberEmail;
		Blob BewerberZeugnis;
		Blob BewerberLebenslauf;
		Blob BewerberPassbild;
		double BewerberNC;
		int StudiengangID;
		

		while (resultSet.next()) {
			BewerberVorname = resultSet.getString("BewerberVorname");
			BewerberNachname = resultSet.getString("BewerberNachname");
			BewerberAdresse = resultSet.getString("BewerberAdresse");
			BewerberPLZ = resultSet.getInt("BewerberPLZ");
			BewerberEmail = resultSet.getString("BewerberEmail");
			BewerberZeugnis = (Blob) resultSet.getBlob("BewerberZeugnis");
			BewerberLebenslauf = (Blob) resultSet.getBlob("BewerberLebenslauf");
			BewerberPassbild = (Blob) resultSet.getBlob("BewerberPassbild");
			BewerberNC = resultSet.getInt("BewerberNC");
			StudiengangID = resultSet.getInt("StudiengangID");

			String sqlUmwandlung = "INSERT INTO his.student"
					+ "(MatrikelNr, StudentVorname, StudentNachname, StudentAdresse, StudentPLZ, "
					+ "StudentEmail, StudentZeugnis, StudentLebenslauf, StudentPassbild, StudentNC, StudentAktuellesSemester "
					+ "StudiengangID, StatusID, SemesterbeitragID)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement student = null;
			student = connection.prepareStatement(sqlUmwandlung);

			student.setInt(1, id);
			student.setString(2, BewerberVorname);
			student.setString(3, BewerberNachname);
			student.setString(4, BewerberAdresse);
			student.setInt(5, BewerberPLZ);
			student.setString(6, BewerberEmail);
			student.setBlob(7, BewerberZeugnis);
			student.setBlob(8, BewerberLebenslauf);
			student.setBlob(9, BewerberPassbild);
			student.setDouble(10, BewerberNC);
			student.setInt(11, 1);
			student.setInt(12, StudiengangID);
			student.setInt(13, 11);
			student.setInt(14, 1);
		}

	}

}