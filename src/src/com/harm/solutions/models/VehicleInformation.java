package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class VehicleInformation {
	private String VIN;
	@XmlElement(name = "CalibrationIds")
	CalibrationIds CalibrationIdsObject;
	@XmlElement(name = "CalibrationVerificationNumbers")
	CalibrationVerificationNumbers CalibrationVerificationNumbersObject;

	// Getter Methods

	public String getVIN() {
		return VIN;
	}

	public CalibrationIds getCalibrationIds() {
		return CalibrationIdsObject;
	}

	public CalibrationVerificationNumbers getCalibrationVerificationNumbers() {
		return CalibrationVerificationNumbersObject;
	}

	// Setter Methods

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	public void setCalibrationIds(CalibrationIds CalibrationIdsObject) {
		this.CalibrationIdsObject = CalibrationIdsObject;
	}

	public void setCalibrationVerificationNumbers(CalibrationVerificationNumbers CalibrationVerificationNumbersObject) {
		this.CalibrationVerificationNumbersObject = CalibrationVerificationNumbersObject;
	}
}
