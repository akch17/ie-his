package learnbpm.example.gueltigkeitspruefung1;

public class GeneriereUnvollstaendigkeitsBescheid {
	
	String msg = "";

	public void generateSchreiben(String name) {
		
		msg = "Sehr geehrte(r) Frau/Herr " + name + " ,\r\n" + "\r\n" + "\r\n"
				+ "herzlichen Dank für Ihre Bewerbung . Bei der Durchsicht Ihrer Unterlagen ist uns aufgefallen, dass Ihre Dokumente ungültig sind und wir Ihre Bewerbung somit nicht berücksichtigen können. Aufgrunddessen werden Sie aus dem Bewerbungsverfahren ausgeschlossen.\r\n" + 
				"\r\n" 
				+ "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
				+ "Technische Hochschule Mittelhessen";
	}

}
