package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class PIDResults {
	
	@XmlElementWrapper(name="results")
    @XmlElement(name="PIDResult")
	ArrayList<PIDResult> results = new ArrayList<PIDResult>();

	public ArrayList<PIDResult> getPIDResult() {
		return results;
	}

	public void setPIDResult(ArrayList<PIDResult> pIDResult) {
		results = pIDResult;
	}

}