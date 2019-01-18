package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Service06Results {
	@XmlElement(name = "IsCAN")
	private String IsCAN;
	ArrayList<Object> TestResult = new ArrayList<Object>();

	// Getter Methods

	public String getIsCAN() {
		return IsCAN;
	}

	// Setter Methods

	public void setIsCAN(String IsCAN) {
		this.IsCAN = IsCAN;
	}
}