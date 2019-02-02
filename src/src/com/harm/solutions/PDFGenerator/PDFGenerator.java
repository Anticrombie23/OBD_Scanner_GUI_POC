package src.com.harm.solutions.PDFGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import src.com.harm.solutions.models.Doc;
import src.com.harm.solutions.utilities.FileNameUtility;

public class PDFGenerator extends PdfPageEventHelper {

	File templateFile;
	File outputFile;
	PdfReader pdfReader;
	FileNameUtility utility = new FileNameUtility();

	public PDFGenerator() throws MalformedURLException, IOException {
		initPDFTemplate();
	}

	public void updateAndSavePDFToDesktop(Map<String, JTextField> fieldContents, Doc rawData)
			throws IOException, DocumentException {

		String dest = utility.generateFileName(fieldContents);
		outputFile = new File(dest);
		Document document = new Document(PageSize.A4);
		FileOutputStream stream = new FileOutputStream(outputFile);
		PdfWriter writer = PdfWriter.getInstance(document, stream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		writePDFPage(document, writer, cb, 1, fieldContents, rawData, stream);
		writePDFPage(document, writer, cb, 2, fieldContents, rawData, stream);

		stream.close();
		// document.close();

	}

	protected void writePDFPage(Document document, PdfWriter writer, PdfContentByte cb, int pageNumber,
			Map<String, JTextField> map, Doc rawData, FileOutputStream stream) {

		if (1 == pageNumber) {
			addDynamicContentToPageOne(map, rawData, stream);
		}

		PdfImportedPage page = writer.getImportedPage(pdfReader, pageNumber);
		document.newPage();
		cb.addTemplate(page, 0, 0);
	}

	private void addDynamicContentToPageOne(Map<String, JTextField> map, Doc rawData, FileOutputStream stream) {
		try {
			PdfStamper stamper = new PdfStamper(pdfReader, stream);

			// Company Fields
			stamper.getAcroFields().setField("Name", map.get("companyName").getText());
			stamper.getAcroFields().setField("Street Address", map.get("companyStreetAddress").getText());
			stamper.getAcroFields().setField("City ST ZIP Code", map.get("companyCityStateZip").getText());
			stamper.getAcroFields().setField("Phone", map.get("companyPhoneNumber").getText());

			// Invoice Stuff
			stamper.getAcroFields().setField("Invoice No", map.get("invoiceNumber").getText());
			stamper.getAcroFields().setField("Invoice Date", map.get("invoicePayableDate").getText());

			// Customer fields
			stamper.getAcroFields().setField("Name_2", map.get("customerFullName").getText());
			stamper.getAcroFields().setField("Street Address_2", map.get("customerStreetAddress").getText());
			stamper.getAcroFields().setField("City ST ZIP Code_2", map.get("customerCityStateZip").getText());
			stamper.getAcroFields().setField("Phone_2", map.get("customerPhoneNumber").getText());

			stamper.close();

		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initPDFTemplate() throws MalformedURLException, IOException {
		templateFile = new File("invoice_template.pdf");
		pdfReader = new PdfReader(templateFile.getAbsolutePath());

	}

}
