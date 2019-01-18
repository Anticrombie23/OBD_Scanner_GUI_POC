package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class AvailableSensors {
	
	@XmlElementWrapper(name="AvailableSensors")
    @XmlElement(name="Sensor")
	ArrayList<Object> Sensor = new ArrayList<Object>();

	// Getter Methods

	// Setter Methods

}