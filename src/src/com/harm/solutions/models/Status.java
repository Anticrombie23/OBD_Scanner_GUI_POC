package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class Status {
	@XmlElement(name = "RawByte1")
	private String RawByte1;
	@XmlElement(name = "RawByte2")
	private String RawByte2;
	@XmlElement(name = "RawByte3")
	private String RawByte3;

	// Getter Methods

	public String getRawByte1() {
		return RawByte1;
	}

	public String getRawByte2() {
		return RawByte2;
	}

	public String getRawByte3() {
		return RawByte3;
	}

	// Setter Methods

	public void setRawByte1(String RawByte1) {
		this.RawByte1 = RawByte1;
	}

	public void setRawByte2(String RawByte2) {
		this.RawByte2 = RawByte2;
	}

	public void setRawByte3(String RawByte3) {
		this.RawByte3 = RawByte3;
	}
}
