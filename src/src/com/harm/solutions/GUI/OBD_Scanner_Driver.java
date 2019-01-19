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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import src.com.harm.solutions.PDFGenerator.PDFGenerator;
import src.com.harm.solutions.models.Doc;
import src.com.harm.solutions.utilities.ProcessingException;

public class OBD_Scanner_Driver {

	private JFrame frame;
	private JTextField companyName;

	private Map xmlDiagnosticContent;
	private PDFGenerator generator;

	Doc rawData;
	private JTextField companyStreetAddress;
	private JTextField companyCityStateZip;
	private JTextField companyPhoneNumber;
	private JTextField invoiceNumber;
	private JTextField invoicePayableDate;
	private JTextField customerFullName;
	private JTextField customerStreetAddress;
	private JTextField customerCityStateZip;
	private JTextField customerPhoneNumber;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 729);
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

		JButton btnNewButton_1 = new JButton("Select Diagnostic Report (.drt) File ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
							loadFileIntoXMLObject(stream);
						} catch (Exception e) {
							e.printStackTrace();
							fileSelectedLabel
									.setText("File chosen was not formatted properly. Please choose another file");
						}
						fileSelectedLabel.setText("File chosen was successfully input into memory");
						System.out.println("DRT File selected! ");
					}
				}

			}

			private void loadFileIntoXMLObject(InputStream file) throws Exception {

				JAXBContext jc = JAXBContext.newInstance(Doc.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();

				rawData = (Doc) unmarshaller.unmarshal(file);

			}
		});
		btnNewButton_1.setBounds(28, 585, 303, 23);
		frame.getContentPane().add(btnNewButton_1);

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

		JLabel invoiceGenerated = new JLabel("Josh Harm copyright 2019");
		invoiceGenerated.setHorizontalAlignment(SwingConstants.CENTER);
		invoiceGenerated.setBounds(-10, 654, 388, 14);
		frame.getContentPane().add(invoiceGenerated);

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
		createPDFButton.setBounds(28, 619, 303, 23);

		createPDFButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				validateSubmission();
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

					notNullLabel.setEnabled(false);
					notNullLabel.setText("");
					
				} catch (ProcessingException e) {
					notNullLabel.setEnabled(true);
					notNullLabel.setText("The following field cannot be empty: " + e.getMessage());
					notNullLabel.setBackground(Color.black);
					notNullLabel.setForeground(Color.RED);
				}

			}

			private void notNull(JTextField field) {

				if (null == field.getText() || "".equals(field.getText())) {
					throw new ProcessingException(field.getName());
				}

			}

		});

		frame.getContentPane().add(createPDFButton);

	}
}
