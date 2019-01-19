package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class TrackingCounters {

	@XmlElement(name = "TrackingCounter")
	ArrayList<TrackingCounter> TrackingCounter = new ArrayList<TrackingCounter>();

	public ArrayList<TrackingCounter> getTrackingCounter() {
		return TrackingCounter;
	}

	public void setTrackingCounter(ArrayList<TrackingCounter> trackingCounter) {
		TrackingCounter = trackingCounter;
	}

}