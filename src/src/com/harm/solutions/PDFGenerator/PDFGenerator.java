package src.com.harm.solutions.PDFGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.itextpdf.text.pdf.PdfTemplate;
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

	public void updateAndSavePDFToDesktop(Map<String, JTextField> fieldContents, Doc preScanData, Doc postScanData)
			throws IOException, DocumentException {

		boolean isPrescan = (null != preScanData) ? true : false;
		boolean isPostScan = (null != postScanData) ? true : false;

		String dest = utility.generateFileName(fieldContents);
		outputFile = new File(dest);
		Document document = new Document(PageSize.A4);
		FileOutputStream stream = new FileOutputStream(outputFile);
		PdfWriter writer = PdfWriter.getInstance(document, stream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		writePDFPage(document, writer, cb, 1, fieldContents, stream, isPrescan, isPostScan);
		writePDFPage(document, writer, cb, 2, fieldContents, stream, isPrescan, isPostScan);
		writeFinalPage(document, cb, fieldContents, preScanData, stream, postScanData);
		inputDocumentAttributes(document);
		stream.close();

	}

	private void inputDocumentAttributes(Document document) {
		document.addAuthor("Joshua Harm");
		document.addCreator("Joshua Harm");
		document.addLanguage("English");
	}

	private void writeFinalPage(Document document, PdfContentByte cb, Map<String, JTextField> fieldContents,
			Doc preScanData, FileOutputStream stream, Doc postScanData) {

		try {
			document.open();
			stream.flush();
			PdfWriter writer = PdfWriter.getInstance(document, stream);
			PdfTemplate tableTemplate = PdfTemplate.createTemplate(writer, 1500, 1300);

			cb.addTemplate(tableTemplate, 0, 0);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void writePDFPage(Document document, PdfWriter writer, PdfContentByte cb, int pageNumber,
			Map<String, JTextField> map, FileOutputStream stream, boolean isPrescan, boolean isPostScan) {

		if (1 == pageNumber) {
			addContentToPageOne(map, stream, isPrescan, isPostScan);
		}

		PdfImportedPage page = writer.getImportedPage(pdfReader, pageNumber);
		document.newPage();
		cb.addTemplate(page, 0, 0);
	}

	private void addContentToPageOne(Map<String, JTextField> map, FileOutputStream stream, boolean isPrescan,
			boolean isPostScan) {

		LocalDateTime ldt = LocalDateTime.now();

		try {
			PdfStamper stamper = new PdfStamper(pdfReader, stream);

			// Company Fields
			stamper.getAcroFields().setField("Company Name", map.get("companyName").getText());
			stamper.getAcroFields().setField("Street Address", map.get("companyStreetAddress").getText());
			stamper.getAcroFields().setField("City ST ZIP Code", map.get("companyCityStateZip").getText());
			stamper.getAcroFields().setField("Phone", map.get("companyPhoneNumber").getText());

			// Invoice Stuff
			stamper.getAcroFields().setField("Invoice No", map.get("invoiceNumber").getText());
			stamper.getAcroFields().setField("Invoice Date", DateTimeFormatter.ofPattern("MM-dd-yyyy").format(ldt));
			stamper.getAcroFields().setField("Due Date", map.get("invoicePayableDate").getText());

			// Customer fields
			stamper.getAcroFields().setField("Name_2", map.get("customerFullName").getText());
			stamper.getAcroFields().setField("Street Address_2", map.get("customerStreetAddress").getText());
			stamper.getAcroFields().setField("City ST ZIP Code_2", map.get("customerCityStateZip").getText());
			stamper.getAcroFields().setField("Phone_2", map.get("customerPhoneNumber").getText());

			inputStaticContentIntoPDF(stamper, isPrescan, isPostScan);

			stamper.setFormFlattening(true);

			stamper.close();

		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void inputStaticContentIntoPDF(PdfStamper stamper, boolean isPrescan, boolean isPostScan)
			throws IOException, DocumentException {
		// Static content

		String subTotal = (isPrescan && isPostScan) ? "300.00" : "150.00";
		String total = (isPrescan && isPostScan) ? "$300.00" : "$150.00";

		if (isPrescan && isPostScan) {
			stamper.getAcroFields().setField("DescriptionRow1", "Diagnostic OBD Scan: Pre-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow1", " 1.0 x Scan");
			stamper.getAcroFields().setField("DescriptionRow2", "Diagnostic OBD Scan: Post-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow2", " 1.0 x Scan");

			stamper.getAcroFields().setField("Price Row1", "150.00");
			stamper.getAcroFields().setField("Price Row2", "150.00");
			stamper.getAcroFields().setField("Total Row1", "$150.00");
			stamper.getAcroFields().setField("Total Row2", "$150.00");
		} else if (isPrescan && !isPostScan) {
			stamper.getAcroFields().setField("DescriptionRow1", "Diagnostic OBD Scan: Pre-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow1", " 1.0 x Scan");
			stamper.getAcroFields().setField("Price Row1", "150.00");
			stamper.getAcroFields().setField("Total Row1", "$150.00");
		} else if (!isPrescan && isPostScan) {
			stamper.getAcroFields().setField("DescriptionRow1", "Diagnostic OBD Scan: Post-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow1", " 1.0 x Scan");
			stamper.getAcroFields().setField("Price Row1", "150.00");
			stamper.getAcroFields().setField("Total Row1", "$150.00");
		}

		stamper.getAcroFields().setField("Total Row10", total);
		stamper.getAcroFields().setField("Total Subtotal Sales Tax Other Total_1", "row1");
		stamper.getAcroFields().setField("Total Subtotal Sales Tax Other Total_2", "--");
		stamper.getAcroFields().setField("Total Subtotal Sales Tax Other Total_4", total);
		stamper.getAcroFields().setField("Thank you for your business Please send payment within", "30");
		stamper.getAcroFields().setField("will be a", "15%");
		stamper.getAcroFields().setField("per", "week");
	}

	private void initPDFTemplate() throws MalformedURLException, IOException {
		templateFile = new File("invoice_template.pdf");
		pdfReader = new PdfReader(templateFile.getAbsolutePath());

	}

}
