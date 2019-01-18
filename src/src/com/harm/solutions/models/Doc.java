package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "doc")
public class Doc {
	@XmlElement(name = "CreatedDate")
	private String CreatedDate;

	@XmlElement(name = "VehicleIdentifier")
	VehicleIdentifier VehicleIdentifier;

	@XmlElement(name = "MonitorStatus")
	MonitorStatus MonitorStatus;

	@XmlElement(name = "TroubleCodes")
	TroubleCodes TroubleCodes;

	@XmlElement(name = "FreezeFrame")
	FreezeFrame FreezeFrame;

	@XmlElement(name = "Service05Results")
	Service05Results Service05Results;

	@XmlElement(name = "Service06Results")
	Service06Results Service06Results;

	@XmlElement(name = "Service09Results")
	Service09Results Service09Results;

	PIDResults results;

	// Getter Methods

	public String getCreatedDate() {
		return CreatedDate;
	}

	public VehicleIdentifier getVehicleIdentifier() {
		return VehicleIdentifier;
	}

	public MonitorStatus getMonitorStatus() {
		return MonitorStatus;
	}

	public TroubleCodes getTroubleCodes() {
		return TroubleCodes;
	}

	public FreezeFrame getFreezeFrame() {
		return FreezeFrame;
	}

	public Service05Results getService05Results() {
		return Service05Results;
	}

	public Service06Results getService06Results() {
		return Service06Results;
	}

	public Service09Results getService09Results() {
		return Service09Results;
	}

	public PIDResults getPIDResults() {
		return results;
	}

	// Setter Methods

	public void setCreatedDate(String CreatedDate) {
		this.CreatedDate = CreatedDate;
	}

	public void setVehicleIdentifier(VehicleIdentifier VehicleIdentifier) {
		this.VehicleIdentifier = VehicleIdentifier;
	}

	public void setMonitorStatus(MonitorStatus MonitorStatus) {
		this.MonitorStatus = MonitorStatus;
	}

	public void setTroubleCodes(TroubleCodes TroubleCodes) {
		this.TroubleCodes = TroubleCodes;
	}

	public void setFreezeFrame(FreezeFrame FreezeFrame) {
		this.FreezeFrame = FreezeFrame;
	}

	public void setService05Results(Service05Results Service05Results) {
		this.Service05Results = Service05Results;
	}

	public void setService06Results(Service06Results Service06Results) {
		this.Service06Results = Service06Results;
	}

	public void setService09Results(Service09Results Service09Results) {
		this.Service09Results = Service09Results;
	}

	public void setPIDResults(PIDResults PIDResults) {
		this.results = PIDResults;
	}

}