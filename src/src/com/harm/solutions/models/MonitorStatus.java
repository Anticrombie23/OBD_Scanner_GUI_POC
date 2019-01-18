package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class MonitorStatus {
	private String MILOn;
	@XmlElement(name = "NumberOfTroubleCodes")
	private String NumberOfTroubleCodes;
	@XmlElement(name = "Status")
	Status StatusObject;

	// Getter Methods

	public String getMILOn() {
		return MILOn;
	}

	public String getNumberOfTroubleCodes() {
		return NumberOfTroubleCodes;
	}

	public Status getStatus() {
		return StatusObject;
	}

	// Setter Methods

	public void setMILOn(String MILOn) {
		this.MILOn = MILOn;
	}

	public void setNumberOfTroubleCodes(String NumberOfTroubleCodes) {
		this.NumberOfTroubleCodes = NumberOfTroubleCodes;
	}

	public void setStatus(Status StatusObject) {
		this.StatusObject = StatusObject;
	}
}
