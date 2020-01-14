package testdiagramme;

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

public class sendeAufnahmeAnStudentenDelegate implements JavaDelegate {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "camundaproject12341234@gmail.com";
	private static final String PWD = "ichwillfort123";
	private static final Integer PORT = 587;

	public sendeAufnahmeAnStudentenDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		document.addTitle("Aufnahmeerklärung");

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("Hallo " + execution.getVariable("BewerberName")
				+ ", dies ist die Aufnahmeerklärung für ihr Studium an unserer Hochschule"));
		document.add(paragraph);

		document.close();

		byte[] bytes = outputStream.toByteArray();

		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText("Hallo " + execution.getVariable("BewerberName")
				+ ", im Anhang finden Sie die Aufnahmeerklärung für ihr Studium.");

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
	}

}
