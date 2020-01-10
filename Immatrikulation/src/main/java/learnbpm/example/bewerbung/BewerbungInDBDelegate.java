package learnbpm.example.bewerbung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.omg.CORBA.portable.OutputStream;

public class BewerbungInDBDelegate implements JavaDelegate {

	static Integer id;
	
	public BewerbungInDBDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		FileValue lebenslaufDoc = execution.getVariableTyped("LEBENSLAUF_DOC");
		InputStream lebenslauf = lebenslaufDoc.getValue();
		FileValue zeugnisDoc = execution.getVariableTyped("ZEUGNIS_DOC");
		InputStream zeugnis = zeugnisDoc.getValue();
		FileValue passbildDoc = execution.getVariableTyped("PASSBILD_DOC");		
		InputStream passbild = passbildDoc.getValue();
				
		
		Double nc = (Double) execution.getVariable("nc");
		String adresse = (String) execution.getVariable("adresse");
		String email = (String) execution.getVariable("email");
		Integer plz =  (Integer) execution.getVariable("plz");
		String nachname = (String) execution.getVariable("nachname");
		String vorname = (String) execution.getVariable("vorname");
		String studiengang = (String) execution.getVariable("studiengang");
		
		

					
		
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root");
		
		Statement statement;
		statement = connection.createStatement();
		
		String sql = "select max(BewerberID) from his.bewerber";
		ResultSet resultSet;
		resultSet = statement.executeQuery(sql);
		 id = 0;
		
		
		
		while (resultSet.next()) {
			id = resultSet.getInt("max(BewerberID)");
			id++;
			execution.setVariable("BewerberID", id);
			
		}
		Statement statement2;
		statement2 = connection.createStatement();
		String sqlStudiengang = "Select StudiengangID from his.studiengang where StudiengangBezeichnung Like '" + studiengang + "'";
		ResultSet resultSet2;
		resultSet2 = statement2.executeQuery(sqlStudiengang);
		resultSet2.first();
		Integer studiengangID = (Integer) resultSet2.getObject(1);
		
		
		String sqlBewerbung = "INSERT INTO his.bewerber"  
				+ "(BewerberID, BewerberVorname, BewerberNachname, BewerberAdresse, BewerberPLZ, " 
				+ "BewerberEmail, BewerberZeugnis, BewerberLebenslauf, BewerberPassbild, BewerberNC, " 
				+ "StudiengangID, StatusID)"  
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement bewerber = null;
		bewerber = connection.prepareStatement(sqlBewerbung);
		
		bewerber.setInt(1, id);
		bewerber.setString(2, vorname);
		bewerber.setString(3, nachname);
		bewerber.setString(4, adresse);
		bewerber.setInt(5, plz);
		bewerber.setString(6, email);
		bewerber.setBlob(7, zeugnis);
		bewerber.setBlob(8, lebenslauf);
		bewerber.setBlob(9, passbild);
		bewerber.setDouble(10, nc);
		bewerber.setInt(11, studiengangID);
		bewerber.setInt(12, 1);
		
		
		bewerber.executeUpdate();
		
		
		
		dateiAbspeichern(lebenslauf);
		dateiAbspeichern(passbild);
		dateiAbspeichern(zeugnis);
		
		
		try {
			bewerber.close();
		} catch(Exception e) {
			
		}
		connection.close();
		
	}
	
	public void dateiAbspeichern(InputStream inputstream){
		try {
			inputstream.reset();
		byte[] buffer = new byte[inputstream.available()];
		inputstream.read(buffer);
		Random r = new Random();
		Integer i = r.nextInt();
		String zufall = i.toString();
		
		String dateiUndPfad = "/test/bewerber_ID"+id+"_"+zufall+".pdf";
		File zielDatei = new File(dateiUndPfad);
		FileOutputStream out = new FileOutputStream(zielDatei);
		out.write(buffer);
		out.close();
		
		} catch (IOException e) {
			System.out.println("Datei konnte nicht abgespeichert werden");
			e.printStackTrace();
		}
	}

}