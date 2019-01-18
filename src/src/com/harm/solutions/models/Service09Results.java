package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class Service09Results {
	@XmlElement(name = "VehicleInformation")
	VehicleInformation VehicleInformationObject;
	@XmlElement(name = "TrackingCounters")
	TrackingCounters TrackingCountersObject;

	// Getter Methods

	public VehicleInformation getVehicleInformation() {
		return VehicleInformationObject;
	}

	public TrackingCounters getTrackingCounters() {
		return TrackingCountersObject;
	}

	// Setter Methods

	public void setVehicleInformation(VehicleInformation VehicleInformationObject) {
		this.VehicleInformationObject = VehicleInformationObject;
	}

	public void setTrackingCounters(TrackingCounters TrackingCountersObject) {
		this.TrackingCountersObject = TrackingCountersObject;
	}
}
