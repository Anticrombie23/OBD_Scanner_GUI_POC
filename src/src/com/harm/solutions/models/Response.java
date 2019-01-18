package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


public class Response {
	@XmlElement(name = "PID")
	PID PIDObject;
	@XmlElement(name = "Time")
	private String Time;
	@XmlElement(name = "NumericValue")
	private String NumericValue;

	// Getter Methods

	public PID getPID() {
		return PIDObject;
	}

	public String getTime() {
		return Time;
	}

	public String getNumericValue() {
		return NumericValue;
	}

	// Setter Methods

	public void setPID(PID PIDObject) {
		this.PIDObject = PIDObject;
	}

	public void setTime(String Time) {
		this.Time = Time;
	}

	public void setNumericValue(String NumericValue) {
		this.NumericValue = NumericValue;
	}
}