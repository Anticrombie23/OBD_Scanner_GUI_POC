package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class VehicleIdentifier {
	@XmlElement(name = "Vin")
	private String Vin;
	@XmlElement(name="Fingerprint")
	private String Fingerprint;
	@XmlElement(name="PidFingerprint")
	private String PidFingerprint;
	@XmlElement(name="LegacyFingerprint")
	LegacyFingerprint LegacyFingerprintObject;

	// Getter Methods

	public String getVin() {
		return Vin;
	}

	public String getFingerprint() {
		return Fingerprint;
	}

	public String getPidFingerprint() {
		return PidFingerprint;
	}

	public LegacyFingerprint getLegacyFingerprint() {
		return LegacyFingerprintObject;
	}

	// Setter Methods

	public void setVin(String Vin) {
		this.Vin = Vin;
	}

	public void setFingerprint(String Fingerprint) {
		this.Fingerprint = Fingerprint;
	}

	public void setPidFingerprint(String PidFingerprint) {
		this.PidFingerprint = PidFingerprint;
	}

	public void setLegacyFingerprint(LegacyFingerprint LegacyFingerprintObject) {
		this.LegacyFingerprintObject = LegacyFingerprintObject;
	}
}
