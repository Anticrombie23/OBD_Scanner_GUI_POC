package src.com.harm.solutions.utilities;

import java.util.Map;

import javax.swing.JTextField;

public class FileNameUtility {

	public String generateFileName(Map<String, JTextField> map) {
		String fileLocation = System.getProperty("user.home") + "/Desktop/";
		String fileName = map.get("companyName").getText() + "_Invoice_" + map.get("invoiceNumber").getText();
		String absolutePath = fileLocation + fileName + ".pdf";
		return absolutePath.replace("\\", "/");
	}

}
