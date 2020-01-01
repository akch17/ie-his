package UtilityClasses;

public class GenerateAblehnungsgrund {

	public String GenerateAblehnungsgrund(int Status) {
		 
		
		switch (Status){
		
		case 15:
			return "Lieber Bewerber, leider wurde ihre Bewerbung als ungültig ausgewertet. Bitte vergewissern "
					+ "Sie sich, dass Sie alle nötigen Unterlagen korrekt einreichen. Dazu finden Sie "
					+ "auf unserer Webseite eine Liste mit allen benötigten Unterlagen. Bitte reichen Sie im "
					+ "Anschluss alle Unterlagen nach";
		case 25:
			return "Lieber Bewerber, leider hat Ihre Durchschnittsnote im diesen Jahr nicht ausgereicht. Sie "
					+ "haben die Möglichkeit, sich im nächsten Semester erneut für den Studiengang zu bewerben.";
			
		case 35:
			return "Lieber Bewerber, leider haben Sie den Eignungstest nicht bestanden. Sie haben dennoch "
					+ "die Möglichkeit im nächsten Semester sich erneut für den Studiengang zu bewerben.";
			
		case 55: 
			return "Lieber Bewerber, leider haben Sie die Studiengebühren nicht rechtzeitig bezahlt. Wenn "
					+ "Sie sich für einen anderen Studiengang entschieden haben, wünschen wir Ihnen viel "
					+ "Erfolg.";
		}
		return null;

		
	}

	public static void main(String[] args) {
		GenerateAblehnungsgrund Ablehnung = new GenerateAblehnungsgrund();
		System.out.println(Ablehnung.GenerateAblehnungsgrund(35));
	}

}
