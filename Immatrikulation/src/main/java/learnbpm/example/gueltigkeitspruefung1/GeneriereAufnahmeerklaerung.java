package learnbpm.example.gueltigkeitspruefung1;

public class GeneriereAufnahmeerklaerung {

	String msg = "";

	
	public void generateSchreiben(String name) {

		msg = "Sehr geehrte(r) Frau/Herr " + name + " ,\r\n" + "\r\n" + "\r\n"
				+ "vielen Dank für Ihre Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
				+ "\r\n" + "\r\n"
				+ "Wir freuen uns, Ihnen mitteilen zu können, dass wir Sie an der technischen Hochschule Mittelhessen herzlich willkommen heißen möchten.\r\n"
				+ "\r\n" + "Bitte überweisen Sie hierzu bis zum xx.xx.xxxx den Betrag von xxx,xx € an:\r\n" + "\r\n"
				+ "Technische Hochschule Mittelhessen\r\n" + "IBAN:\r\n" + "BIC: HELADEFF\r\n"
				+ "Kreditinstitut: DE61 5005 0000 0001 0066 34\r\n"
				+ "Verwendungszweck: Ihr vollständiger Name, Studiengang\r\n" + "\r\n"
				+ "Nach erfolreichem Zahlunseingang werden wir uns erneut mit Ihnen in Verbindung setzen.\r\n" + " \r\n"
				+ "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
				+ "Technische Hochschule Mittelhessen";

	}
}
