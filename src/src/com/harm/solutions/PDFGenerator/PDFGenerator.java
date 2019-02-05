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
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import src.com.harm.solutions.models.Doc;
import src.com.harm.solutions.models.ScanType;
import src.com.harm.solutions.utilities.FileNameUtility;

public class PDFGenerator extends PdfPageEventHelper {

	File templateFile;
	File outputFile;
	PdfReader pdfReader;
	FileNameUtility utility = new FileNameUtility();
	Doc preScan, postScan;

	ScanType scanType;

	public PDFGenerator(Doc preScan, Doc postScan) throws MalformedURLException, IOException {
		this.preScan = preScan;
		this.postScan = postScan;
		getScanType(preScan, postScan);
		initPDFTemplate();
	}

	private void getScanType(Doc preScan, Doc postScan) {
		boolean isPrescan = (null != preScan) ? true : false;
		boolean isPostScan = (null != postScan) ? true : false;
		scanType = ScanType.determineScanType(isPrescan, isPostScan);
	}

	public void updateAndSavePDFToDesktop(Map<String, JTextField> fieldContents) throws IOException, DocumentException {
		String dest = utility.generateFileName(fieldContents);
		outputFile = new File(dest);
		Document document = new Document(PageSize.A4);
		FileOutputStream stream = new FileOutputStream(outputFile);
		PdfWriter writer = PdfWriter.getInstance(document, stream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		writePDFPage(document, writer, cb, 1, fieldContents, stream);
		writePDFPage(document, writer, cb, 2, fieldContents, stream);
		inputDocumentAttributes(document);
		stream.close();

	}

	private void inputDocumentAttributes(Document document) {
		document.addAuthor("Joshua Harm");
		document.addCreator("Joshua Harm");
		document.addLanguage("English");
	}

	protected void writePDFPage(Document document, PdfWriter writer, PdfContentByte cb, int pageNumber,
			Map<String, JTextField> map, FileOutputStream stream) {

		if (1 == pageNumber) {
			addContentToPageOneAndThree(map, stream);
		}

		PdfImportedPage page = writer.getImportedPage(pdfReader, pageNumber);
		document.newPage();
		cb.addTemplate(page, 0, 0);
	}

	private void addContentToPageOneAndThree(Map<String, JTextField> map, FileOutputStream stream) {

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

			inputStaticContentIntoPDF(stamper, map);
			stamper.setFormFlattening(true);
			stamper.close();

		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void inputStaticContentIntoPDF(PdfStamper stamper, Map<String, JTextField> map)
			throws IOException, DocumentException {
		// Static content
		String total = scanType.equals(ScanType.BothScans) ? "$300.00" : "$150.00";

		if (scanType.equals(ScanType.BothScans)) {
			stamper.getAcroFields().setField("DescriptionRow1", "Diagnostic OBD Scan: Pre-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow1", " 1.0 x Scan");
			stamper.getAcroFields().setField("DescriptionRow2", "Diagnostic OBD Scan: Post-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow2", " 1.0 x Scan");

			stamper.getAcroFields().setField("Price Row1", "150.00");
			stamper.getAcroFields().setField("Price Row2", "150.00");
			stamper.getAcroFields().setField("Total Row1", "$150.00");
			stamper.getAcroFields().setField("Total Row2", "$150.00");
		} else if (scanType.equals(ScanType.PreScanOnly)) {
			stamper.getAcroFields().setField("DescriptionRow1", "Diagnostic OBD Scan: Pre-Scan");
			stamper.getAcroFields().setField("Quantity  HoursRow1", " 1.0 x Scan");
			stamper.getAcroFields().setField("Price Row1", "150.00");
			stamper.getAcroFields().setField("Total Row1", "$150.00");
		} else if (scanType.equals(ScanType.PostScanOnly)) {
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

		addPageThreeContent(stamper, map);

	}

	private void addPageThreeContent(PdfStamper stamper, Map<String, JTextField> map) {
		Rectangle rec = new Rectangle(615, 725);
		stamper.insertPage(3, rec);

		PdfContentByte canvas = stamper.getOverContent(3);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("TESTTESTTEST"), 36, 540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph("hello people!"), 36, 540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph("hello people!"), 36, 540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph("hello people!"), 36, 540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph("hello people!"), 36, 540, 0);
	}

	private void initPDFTemplate() throws MalformedURLException, IOException {
		templateFile = new File("invoice_template.pdf");
		pdfReader = new PdfReader(templateFile.getAbsolutePath());

	}

}
