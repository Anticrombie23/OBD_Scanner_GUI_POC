package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	@XmlElement(name = "ECU")
	private String ecu;
	@XmlElement(name = "Value")
	private String value;

	public String getEcu() {
		return ecu;
	}

	public void setEcu(String ecu) {
		this.ecu = ecu;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
