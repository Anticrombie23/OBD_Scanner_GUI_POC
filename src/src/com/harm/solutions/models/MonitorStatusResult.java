package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class MonitorStatusResult {
	@XmlElement(name = "Response")
	Response ResponseObject;

	// Getter Methods

	public Response getResponse() {
		return ResponseObject;
	}

	// Setter Methods

	public void setResponse(Response ResponseObject) {
		this.ResponseObject = ResponseObject;
	}
}
