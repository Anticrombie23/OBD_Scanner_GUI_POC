package src.com.harm.solutions.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Service06Results {
	@XmlElement(name = "IsCAN")
	private String IsCAN;

	@XmlElement(name = "TestResult")
	List<TestResult> results = new ArrayList<TestResult>();


	public ArrayList<TestResult> getResult() {
		return (ArrayList<TestResult>) results;
	}

	public void setResult(ArrayList<TestResult> result) {
		this.results = result;
	}

}