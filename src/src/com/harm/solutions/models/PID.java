package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class PID {

	private String _pt;

	private String _uid;

	// Getter Methods
	@XmlElement(name = "pt")
	public String get_pt() {
		return _pt;
	}

	@XmlElement(name = "pt")
	public String get_uid() {
		return _uid;
	}

	// Setter Methods

	public void set_pt(String _pt) {
		this._pt = _pt;
	}

	public void set_uid(String _uid) {
		this._uid = _uid;
	}
}