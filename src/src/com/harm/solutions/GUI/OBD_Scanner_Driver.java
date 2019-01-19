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

public class OBD_Scanner_Driver {

	private JFrame frame;
	private JTextField textField;

	private Map xmlDiagnosticContent;
	private PDFGenerator generator;

	Doc rawData;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
		frame.setBounds(100, 100, 490, 729);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Label label1 = new Label("OBD Wiz to Invoice Utility");
		label1.setFont(new Font("Arial", Font.BOLD, 20));
		label1.setBounds(70, 28, 288, 22);
		frame.getContentPane().add(label1);

		JButton createPDFButton = new JButton("Create PDF Invoice");
		createPDFButton.setBounds(97, 619, 261, 23);

		createPDFButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				validateSubmission();
			}

			private void validateSubmission() {
				// TODO Auto-generated method stub
				
			}

		});

		frame.getContentPane().add(createPDFButton);

		Label label = new Label("Company Name to Use: ");
		label.setBounds(28, 166, 154, 22);
		frame.getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(182, 168, 176, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel fileSelectedLabel = new JLabel("No File Selected Yet");
		fileSelectedLabel.setBackground(Color.GREEN);
		fileSelectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileSelectedLabel.setFont(new Font("Sitka Small", Font.PLAIN, 13));
		fileSelectedLabel.setBounds(97, 492, 261, 14);
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
		btnNewButton_1.setBounds(97, 521, 261, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Street Address: ");
		lblNewLabel.setBounds(28, 202, 144, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 199, 176, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCityStZip = new JLabel("City, ST ZIP Code: ");
		lblCityStZip.setBounds(28, 237, 154, 14);
		frame.getContentPane().add(lblCityStZip);
		
		textField_2 = new JTextField();
		textField_2.setBounds(182, 234, 176, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(182, 265, 176, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Phone Number:");
		lblNewLabel_1.setBounds(28, 268, 144, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblInvoiceNumber = new JLabel("Invoice Number: ");
		lblInvoiceNumber.setBounds(28, 353, 144, 14);
		frame.getContentPane().add(lblInvoiceNumber);
		
		textField_4 = new JTextField();
		textField_4.setBounds(182, 350, 176, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDateInvoicePayable = new JLabel("Date Invoice Payable:");
		lblDateInvoicePayable.setBounds(28, 402, 154, 14);
		frame.getContentPane().add(lblDateInvoicePayable);
		
		textField_5 = new JTextField();
		textField_5.setBounds(182, 399, 176, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

	}
}
