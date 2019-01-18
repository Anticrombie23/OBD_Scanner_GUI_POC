package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class LegacyFingerprint {
	private String _isVin;
	private String __text;

	// Getter Methods

	public String get_isVin() {
		return _isVin;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set_isVin(String _isVin) {
		this._isVin = _isVin;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}
