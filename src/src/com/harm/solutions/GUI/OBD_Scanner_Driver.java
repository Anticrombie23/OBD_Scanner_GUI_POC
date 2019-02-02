package src.com.harm.solutions.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.itextpdf.text.DocumentException;

import src.com.harm.solutions.PDFGenerator.PDFGenerator;
import src.com.harm.solutions.models.Doc;
import src.com.harm.solutions.utilities.FileNameUtility;
import src.com.harm.solutions.utilities.ProcessingException;

public class OBD_Scanner_Driver {

	// private final class ScannerUtils implements ActionListener {
	// private final JFileChooser chooser;
	// private final JLabel fileSelectedLabel;
	// private final JLabel notNullLabel;
	//
	// private ScannerUtils(JFileChooser chooser, JLabel fileSelectedLabel, JLabel
	// notNullLabel) {
	// this.chooser = chooser;
	// this.fileSelectedLabel = fileSelectedLabel;
	// this.notNullLabel = notNullLabel;
	// }
	//
	// public void actionPerformed(ActionEvent arg0) {
	// selectScanFile(fileSelectedLabel, chooser, notNullLabel);
	// }
	//
	// private void validateFileAddedSuccessfully(JLabel notNullLabel) {
	// if (null == rawData) {
	// throw new ProcessingException(".drt file not added!");
	// } else {
	// notNullLabel.setEnabled(false);
	// notNullLabel.setText("");
	// }
	// }
	//
	// private void loadFileIntoXMLObject(InputStream file) throws Exception {
	//
	// JAXBContext jc = JAXBContext.newInstance(Doc.class);
	// Unmarshaller unmarshaller = jc.createUnmarshaller();
	//
	// rawData = (Doc) unmarshaller.unmarshal(file);
	//
	// }
	// }

	private JFrame frame;
	private JTextField companyName;
	private PDFGenerator generator;
	FileNameUtility utility = new FileNameUtility();

	private Doc preScan;
	private Doc postScan;
	private JTextField companyStreetAddress;
	private JTextField companyCityStateZip;
	private JTextField companyPhoneNumber;
	private JTextField invoiceNumber;
	private JTextField invoicePayableDate;
	private JTextField customerFullName;
	private JTextField customerStreetAddress;
	private JTextField customerCityStateZip;
	private JTextField customerPhoneNumber;

	final String html1 = "<html><body style='width: ";
	final String html2 = "px'>";

	Map<String, JTextField> fieldContents = new HashMap<String, JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBD_Scanner_Driver window = new OBD_Scanner_Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public OBD_Scanner_Driver() throws MalformedURLException, IOException {
		initialize();
		generator = new PDFGenerator();
		initJTextFields();
	}

	private void initJTextFields() {
		fieldContents.put("companyName", companyName);
		fieldContents.put("companyStreetAddress", companyStreetAddress);
		fieldContents.put("companyCityStateZip", companyCityStateZip);
		fieldContents.put("companyPhoneNumber", companyPhoneNumber);
		fieldContents.put("companyPhoneNumber", companyPhoneNumber);
		fieldContents.put("invoiceNumber", invoiceNumber);
		fieldContents.put("invoicePayableDate", invoicePayableDate);
		fieldContents.put("customerFullName", customerFullName);
		fieldContents.put("customerStreetAddress", customerStreetAddress);
		fieldContents.put("customerCityStateZip", customerCityStateZip);
		fieldContents.put("customerPhoneNumber", customerPhoneNumber);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 773);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Label label1 = new Label("OBD Wiz to Invoice Utility");
		label1.setFont(new Font("Arial", Font.BOLD, 20));
		label1.setBounds(70, 28, 288, 22);
		frame.getContentPane().add(label1);
		companyName = new JTextField();
		companyName.setName("companyName");
		companyName.setBounds(163, 112, 176, 20);
		frame.getContentPane().add(companyName);
		companyName.setColumns(10);

		JLabel fileSelectedLabel = new JLabel("No File Selected Yet");
		fileSelectedLabel.setBackground(Color.GREEN);
		fileSelectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileSelectedLabel.setFont(new Font("Sitka Small", Font.PLAIN, 13));
		fileSelectedLabel.setBounds(47, 560, 261, 14);
		frame.getContentPane().add(fileSelectedLabel);

		JFileChooser chooser = new JFileChooser();

		JLabel lblNewLabel = new JLabel("Street Address: ");
		lblNewLabel.setBounds(28, 143, 144, 14);
		frame.getContentPane().add(lblNewLabel);

		companyStreetAddress = new JTextField();
		companyStreetAddress.setName("companyStreetAddress");
		companyStreetAddress.setBounds(163, 140, 176, 20);
		frame.getContentPane().add(companyStreetAddress);
		companyStreetAddress.setColumns(10);

		JLabel lblCityStZip = new JLabel("City, ST ZIP Code: ");
		lblCityStZip.setBounds(28, 177, 154, 14);
		frame.getContentPane().add(lblCityStZip);

		companyCityStateZip = new JTextField();
		companyCityStateZip.setName("companyCityStateZip");
		companyCityStateZip.setBounds(163, 174, 176, 20);
		frame.getContentPane().add(companyCityStateZip);
		companyCityStateZip.setColumns(10);

		companyPhoneNumber = new JTextField();
		companyPhoneNumber.setName("companyPhoneNumber");
		companyPhoneNumber.setBounds(163, 199, 176, 20);
		frame.getContentPane().add(companyPhoneNumber);
		companyPhoneNumber.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Phone Number:");
		lblNewLabel_1.setBounds(28, 202, 144, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblInvoiceNumber = new JLabel("Invoice Number: ");
		lblInvoiceNumber.setBounds(28, 281, 144, 14);
		frame.getContentPane().add(lblInvoiceNumber);

		invoiceNumber = new JTextField();
		invoiceNumber.setName("invoiceNumber");
		invoiceNumber.setBounds(163, 278, 176, 20);
		frame.getContentPane().add(invoiceNumber);
		invoiceNumber.setColumns(10);

		JLabel lblDateInvoicePayable = new JLabel("Date Invoice Payable:");
		lblDateInvoicePayable.setBounds(28, 306, 154, 14);
		frame.getContentPane().add(lblDateInvoicePayable);

		invoicePayableDate = new JTextField();
		invoicePayableDate.setName("invoicePayableDate");
		invoicePayableDate.setBounds(163, 309, 176, 20);
		frame.getContentPane().add(invoicePayableDate);
		invoicePayableDate.setColumns(10);

		JLabel lblCompanyInformation = new JLabel("Your Company Information");
		lblCompanyInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCompanyInformation.setBounds(28, 85, 267, 14);
		frame.getContentPane().add(lblCompanyInformation);

		JLabel lblNewLabel_2 = new JLabel("Invoice Information");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(28, 256, 222, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblYourCompanyName = new JLabel("Your company name: ");
		lblYourCompanyName.setBounds(28, 118, 144, 14);
		frame.getContentPane().add(lblYourCompanyName);

		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCustomerInformation.setBounds(28, 360, 222, 14);
		frame.getContentPane().add(lblCustomerInformation);

		JLabel lblCustomerFullName = new JLabel("Full name: ");
		lblCustomerFullName.setBounds(28, 396, 144, 14);
		frame.getContentPane().add(lblCustomerFullName);

		JLabel label = new JLabel("Street Address: ");
		label.setBounds(28, 421, 144, 14);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("City, ST ZIP Code: ");
		label_1.setBounds(28, 446, 154, 14);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Phone Number:");
		label_2.setBounds(28, 471, 144, 14);
		frame.getContentPane().add(label_2);

		customerFullName = new JTextField();
		customerFullName.setName("customerFullName");
		customerFullName.setColumns(10);
		customerFullName.setBounds(163, 393, 176, 20);
		frame.getContentPane().add(customerFullName);

		customerStreetAddress = new JTextField();
		customerStreetAddress.setName("customerStreetAddress");
		customerStreetAddress.setColumns(10);
		customerStreetAddress.setBounds(163, 418, 176, 20);
		frame.getContentPane().add(customerStreetAddress);

		customerCityStateZip = new JTextField();
		customerCityStateZip.setName("customerCityStateZip");
		customerCityStateZip.setColumns(10);
		customerCityStateZip.setBounds(163, 443, 176, 20);
		frame.getContentPane().add(customerCityStateZip);

		customerPhoneNumber = new JTextField();
		customerPhoneNumber.setName("customerPhoneNumber");
		customerPhoneNumber.setColumns(10);
		customerPhoneNumber.setBounds(163, 468, 176, 20);
		frame.getContentPane().add(customerPhoneNumber);

		JLabel notNullLabel = new JLabel("");
		notNullLabel.setEnabled(false);
		notNullLabel.setBounds(47, 521, 311, 14);
		frame.getContentPane().add(notNullLabel);

		JButton createPDFButton = new JButton("Create PDF Invoice");
		createPDFButton.setBounds(28, 654, 303, 23);

		createPDFButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				validateSubmission();

				if (notNullLabel.isEnabled() != true) {
					try {
						createPDF();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					alertUserPDFExists();
				}

			}

			private void alertUserPDFExists() {

				String absolutePath = utility.generateFileName(fieldContents);
				JOptionPane.showMessageDialog(createPDFButton, "PDF Successfully Generated at: " + absolutePath);

			}

			private void createPDF() throws IOException, DocumentException {
				generator.updateAndSavePDFToDesktop(fieldContents, preScan);
			}

			private void validateSubmission() {
				try {

					notNull(companyName);
					notNull(companyStreetAddress);
					notNull(companyCityStateZip);
					notNull(companyPhoneNumber);

					notNull(invoiceNumber);
					notNull(invoicePayableDate);

					notNull(customerFullName);
					notNull(customerStreetAddress);
					notNull(customerStreetAddress);
					notNull(customerCityStateZip);
					notNull(customerPhoneNumber);

					validateFileAddedSuccessfully(notNullLabel);

				} catch (ProcessingException e) {
					notNullLabel.setEnabled(true);
					notNullLabel.setText("The following field cannot be empty: " + e.getMessage());
					notNullLabel.setBackground(Color.black);
					notNullLabel.setForeground(Color.RED);
				}

			}

			private void validateFileAddedSuccessfully(JLabel notNullLabel) {
				if (null == preScan && null == postScan) {
					throw new ProcessingException("One (1) scan need be added.");
				} else {
					notNullLabel.setEnabled(false);
					notNullLabel.setText("");
				}
			}

			private void notNull(JTextField field) {

				if (null == field.getText() || "".equals(field.getText())) {
					throw new ProcessingException(field.getName());
				}

			}

		});

		frame.getContentPane().add(createPDFButton);

		JLabel lblNewLabel_3 = new JLabel("Josh Harm 2019");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(28, 687, 303, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JButton preScan = new JButton("Select PreScan Diagnostic Report (.drt) File ");
		preScan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectScanFile(fileSelectedLabel, chooser, notNullLabel, 1);
			}

		});
		preScan.setBounds(28, 585, 303, 23);
		frame.getContentPane().add(preScan);

		JButton postScan = new JButton("Select PostScan Diagnostic Report (.drt) File");
		postScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				selectScanFile(fileSelectedLabel, chooser, notNullLabel, 2);
			}
		});
		postScan.setBounds(28, 620, 306, 23);
		frame.getContentPane().add(postScan);

	}

	protected void selectScanFile(JLabel fileSelectedLabel, JFileChooser chooser, JLabel notNullLabel, int scanType) {
		int returnValue = chooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = new File(chooser.getSelectedFile().toPath().toString());
			// File newFile = new File(selectedFile,
			// chooser.getSelectedFile().toPath().toString()"drt.txt");
			if (!selectedFile.getPath().substring(selectedFile.getPath().length() - 4).equals(".drt")) {
				fileSelectedLabel.setText("File Selected was not .drt!");
				fileSelectedLabel.setBackground(Color.RED);
			} else {
				try {
					String fileContent = "";
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));

					String line;
					boolean isFirstLine = true;
					while ((line = br.readLine()) != null) {

						if (isFirstLine) {
							// TRASH THAT SUCKA
							isFirstLine = false;
						} else {
							System.out.println(line);
							fileContent += line;

						}

					}

					// Document doc = loadXMLFromString(fileContent);
					String replaced = fileContent.replaceAll(" culture=\"\" version=\"1.00\"", "");

					InputStream stream = new ByteArrayInputStream(replaced.getBytes(StandardCharsets.UTF_8));

					final InputStream targetStream = new DataInputStream(new FileInputStream(selectedFile));
					loadFileIntoXMLObject(stream, selectedFile, scanType);
				} catch (Exception e) {
					e.printStackTrace();
					fileSelectedLabel.setText("File chosen was not formatted properly. Please choose another file");
				}

				fileSelectedLabel.setText(
						scanType == 1 ? "Pre-scan file added successfully." : "Post-Scan file added successfully");

				System.out.println("DRT File selected! ");
			}

		}

		validateFileAddedSuccessfully(notNullLabel);
	}

	private void validateFileAddedSuccessfully(JLabel notNullLabel) {
		if (null == preScan) {
			throw new ProcessingException(".drt file not added!");
		} else {
			notNullLabel.setEnabled(false);
			notNullLabel.setText("");
		}

	}

	private void loadFileIntoXMLObject(InputStream stream, File file, int scanType) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Doc.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		if (scanType == 1) {
			preScan = (Doc) unmarshaller.unmarshal(file);
		} else if (scanType == 2) {
			postScan = (Doc) unmarshaller.unmarshal(file);
		}

	}
}
