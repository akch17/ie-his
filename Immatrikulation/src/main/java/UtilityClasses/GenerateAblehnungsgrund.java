package UtilityClasses;

public class GenerateAblehnungsgrund {

	public String AblehungsgrundGenerieren(int Status) {

		switch (Status) {

		case 15:
			return "Liebe Studieninteressierte, lieber Studieninteressierter,\r\n" + "\r\n" + "\r\n"
					+ "vielen Dank für Deine Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
					+ "\r\n" + " \r\n" + "\r\n"
					+ "Nach sorgfältiger Prüfung Deiner Unterlagen müssen wir Dir leider mitteilen, dass diese nicht vollständig sind.\r\n"
					+ "Bitte kontrolliere diese noch einmal nach und reiche die fehlenden Unterlagen fristgerecht nach.\r\n"
					+ "Ein Kontrolldatenblatt für die jeweiligen Studiengänge sowie alle fristen, findest du auf unserer Homepage unter https://hochschule-riedtal.de \r\n"
					+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
					+ " Hochschule Riedtal ";

		case 25:
			return "Liebe Studieninteressierte, lieber Studieninteressierter,\r\n" + "\r\n" + "\r\n"
					+ "vielen Dank für Deine Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
					+ "\r\n" + " \r\n" + "\r\n"
					+ "Bei der Vielzahl der eingegangenen Studienbewerbungen ist uns die Auswahl nicht leicht gefallen. \r\n"
					+ "Nach sorgfältiger Prüfung Deiner Unterlagen müssen wir Dir leider mitteilen, dass wir Deine Bewerbung nicht in die engere Wahl nehmen konnten. Der Nc war nicht ausreichend. \r\n"
					+ "Für das uns entgegengebrachte Vertrauen bedanken wir uns und wünschen Ihnen viel Erfolg bei der Suche nach einem geeigneten Studienplatz.\r\n"
					+ "\r\n" + "\r\n"
					+ "Zu Deiner Information: Alle von Dir gespeicherten Daten werden aus unseren Systemen gelöscht.\r\n"
					+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
					+ " Hochschule Riedtal ";

		case 35:
			return "Liebe Studieninteressierte, lieber Studieninteressierter,\r\n" + "\r\n" + "\r\n"
					+ "vielen Dank für Deine Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
					+ "\r\n" + " \r\n" + "\r\n"
					+ "Bei der Vielzahl der eingegangenen Studienbewerbungen ist uns die Auswahl nicht leicht gefallen. \r\n"
					+ "Nach sorgfältiger Prüfung Deiner Unterlagen müssen wir Dir leider mitteilen, dass wir Deine Bewerbung nicht in die engere Wahl nehmen konnten, da du den Aufnahmetest nicht bestanden hast.\r\n"
					+ "Für das uns entgegengebrachte Vertrauen bedanken wir uns und wünschen Ihnen viel Erfolg bei der Suche nach einem geeigneten Studienplatz.\r\n"
					+ "\r\n" + "\r\n"
					+ "Zu Deiner Information: Alle von Dir gespeicherten Daten werden aus unseren Systemen gelöscht.\r\n"
					+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
					+ " Hochschule Riedtal ";


		case 55:
			return "Liebe Studieninteressierte, lieber Studieninteressierter,\r\n" + "\r\n" + "\r\n"
			+ "vielen Dank für Deine Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
			+ "\r\n" + " \r\n" + "\r\n"
			+ "Bei der Vielzahl der eingegangenen Studienbewerbungen ist uns die Auswahl nicht leicht gefallen. \r\n"
			+ "Nach sorgfältiger Prüfung Deiner Unterlagen müssen wir Dir leider mitteilen, dass wir Dich aufgrund der ausgeblieben Zahlung des Semesterbeitrages nicht erfolgreich immatrikulieren konnten.\r\n"
			+ "Für das uns entgegengebrachte Vertrauen bedanken wir uns und wünschen Ihnen viel Erfolg bei der Suche nach einem geeigneten Studienplatz.\r\n"
			+ "\r\n" + "\r\n"
			+ "Zu Deiner Information: Alle von Dir gespeicherten Daten werden aus unseren Systemen gelöscht.\r\n"
			+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
			+ " Hochschule Riedtal ";


		}
		return null;

	}

	public static void main(String[] args) {
		GenerateAblehnungsgrund Ablehnung = new GenerateAblehnungsgrund();
		System.out.println(Ablehnung.AblehungsgrundGenerieren(35));
	}

}
