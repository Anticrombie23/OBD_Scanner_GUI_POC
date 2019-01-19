package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TrackingCounter {

	@XmlElement(name = "TrackingCounter")
	private String trackingCounter;
	@XmlElement(name = "Value")
	private String value;
	@XmlElement(name = "IsCompressionEngine")
	private String IsCompressionEngine;

	public String getTrackingCounter() {
		return trackingCounter;
	}

	public void setTrackingCounter(String trackingCounter) {
		this.trackingCounter = trackingCounter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsCompressionEngine() {
		return IsCompressionEngine;
	}

	public void setIsCompressionEngine(String isCompressionEngine) {
		IsCompressionEngine = isCompressionEngine;
	}

}
