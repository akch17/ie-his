package ImmatrikulationBewerber;

import java.io.ByteArrayOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AufnahmeErklaerungSendenDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public AufnahmeErklaerungSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		document.addTitle("Aufnahmeerklärung");

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("Sehr geehrte(r) Frau/Herr ," + execution.getVariable("BewerberNachname") 
		+ "\r\n" + "\r\n" + "\r\n"
		+ "vielen Dank für Ihre Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
		+ "\r\n" + " \r\n" + "\r\n"
		+ "Wir freuen uns, Ihnen mitteilen zu können, dass wir Sie an der technischen Hochschule Mittelhessen herzlich willkommen heißen möchten.\r\n"
		+ "\r\n" + "Bitte überweisen Sie hierzu den Semesterbeitrag auf unser Hochschulkonto.\r\n "
		+ "\r\n" + "\r\n"
		+ "\r\n" + "Nähere Informationen zum Betrag und zu den Fristen erhalten Sie auf https://www.hochschule-riedtal.de.\r\n "
		+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
		+ " Hochschule Riedtal"));
		document.add(paragraph);

		document.close();

		byte[] bytes = outputStream.toByteArray();

		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText("Sehr geehrte(r) Frau/Herr ," + execution.getVariable("BewerberNachname") 
				+ "\r\n" + "\r\n" + "\r\n"
				+ "vielen Dank für Ihre Bewerbung und dem damit verbundenen Interesse an unserer Hochschule.\r\n"
				+ "\r\n" + " \r\n" + "\r\n"
				+ "Im Anhang finden Sie die Annahmeerklärung für ihr Studium an unserer Hochschule.\r\n"
				+ " \r\n" + "\r\n" + "\r\n" + "Mit freundlichen Grüßen\r\n" + "\r\n" + "Ihr Studiensekretariat \r\n"
				+ " Hochschule Riedtal");

		DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		pdfBodyPart.setFileName("Aufnahmeeklärung.pdf");

		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(textBodyPart);
		mimeMultipart.addBodyPart(pdfBodyPart);

		Email email = new SimpleEmail();
		email.setContent(mimeMultipart);
		// verschiedene Parameter zur Versendung setzen

		email.setHostName(HOST);
		email.setAuthentication(USER, PWD);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true);

		email.setFrom("camundaproject12341234@gmail.com"); // Replyadresse
															// angeben
		email.setSubject("Aufnahmeerklärung"); // Betreff

		email.addTo("camundaproject12341234@gmail.com");
		email.send();
		
		System.out.println("An den Bewerber " + execution.getVariable("BewerberId") + " wurde eine vorläufige Aufnahmeerklärung verschickt");
		System.out.println(" ");
	}

}
