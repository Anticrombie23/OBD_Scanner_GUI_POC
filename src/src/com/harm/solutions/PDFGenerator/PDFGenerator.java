package src.com.harm.solutions.PDFGenerator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JTextField;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import src.com.harm.solutions.models.Doc;

public class PDFGenerator {

	File file;
	PDDocument document;

	public PDFGenerator() throws MalformedURLException, IOException {
		initPDFTemplate();
	}

	public void updateAndSavePDFToDesktop(List<JTextField> fieldContents, Doc rawData) {

		updatePDF(fieldContents, rawData);
		savePDFToDesktop();

	}

	private void savePDFToDesktop() {
		
		
		

	}

	private void updatePDF(List<JTextField> fieldContents, Doc rawData) {
		PDPage page = document.getPage(1);
		try {
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initPDFTemplate() throws MalformedURLException, IOException {
		File file = new File("invoice_template.pdf");

		try {
			document = PDDocument.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
