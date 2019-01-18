package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class AdditionalInformation {
	@XmlElement(name = "PIDResults")
	PIDResults PIDResultsObject;
	@XmlElement(name = "MonitorStatusResult")
	MonitorStatusResult MonitorStatusResultObject;

	// Getter Methods

	public PIDResults getPIDResults() {
		return PIDResultsObject;
	}

	public MonitorStatusResult getMonitorStatusResult() {
		return MonitorStatusResultObject;
	}

	// Setter Methods

	public void setPIDResults(PIDResults PIDResultsObject) {
		this.PIDResultsObject = PIDResultsObject;
	}

	public void setMonitorStatusResult(MonitorStatusResult MonitorStatusResultObject) {
		this.MonitorStatusResultObject = MonitorStatusResultObject;
	}
}
