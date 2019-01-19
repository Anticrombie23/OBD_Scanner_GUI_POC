package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class LegacyFingerprint {
	
	
	private String isVin;
	private String __text;

	// Getter Methods
	@XmlAttribute
	public String get_isVin() {
		return isVin;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set_isVin(String _isVin) {
		this.isVin = _isVin;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}
