package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class Service05Results {
	@XmlElement(name = "AvailableSensors")
	AvailableSensors AvailableSensorsObject;
	@XmlElement(name = "TestResults")
	private String TestResults;
	@XmlElement(name = "TestsComplete")
	private String TestsComplete;

	// Getter Methods

	public AvailableSensors getAvailableSensors() {
		return AvailableSensorsObject;
	}

	public String getTestResults() {
		return TestResults;
	}

	public String getTestsComplete() {
		return TestsComplete;
	}

	// Setter Methods

	public void setAvailableSensors(AvailableSensors AvailableSensorsObject) {
		this.AvailableSensorsObject = AvailableSensorsObject;
	}

	public void setTestResults(String TestResults) {
		this.TestResults = TestResults;
	}

	public void setTestsComplete(String TestsComplete) {
		this.TestsComplete = TestsComplete;
	}
}
