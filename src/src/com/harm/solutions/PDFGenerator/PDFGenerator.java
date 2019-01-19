package src.com.harm.solutions.PDFGenerator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFGenerator {

	File file;

	PDDocument document;

	public PDFGenerator() throws MalformedURLException, IOException {
		initPDFTemplate();
	}

	public void updatePDF() {
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
