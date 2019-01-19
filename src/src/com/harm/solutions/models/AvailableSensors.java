package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class AvailableSensors {

	@XmlElement(name = "Sensor")
	ArrayList<Sensor> testers = new ArrayList<Sensor>();

	public ArrayList<Sensor> getSensors() {
		return testers;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.testers = sensors;
	}

}