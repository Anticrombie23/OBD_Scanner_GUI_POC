package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class FreezeFrame {
	@XmlElement(name = "Data")
	private String Data;
	@XmlElement(name = "MonitorStatusInfo")
	MonitorStatusInfo MonitorStatusInfoObject;

	// Getter Methods

	public String getData() {
		return Data;
	}

	public MonitorStatusInfo getMonitorStatusInfo() {
		return MonitorStatusInfoObject;
	}

	// Setter Methods

	public void setData(String Data) {
		this.Data = Data;
	}

	public void setMonitorStatusInfo(MonitorStatusInfo MonitorStatusInfoObject) {
		this.MonitorStatusInfoObject = MonitorStatusInfoObject;
	}
}