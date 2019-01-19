package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TestResult {

	@XmlElement(name = "OBDMID")
	private String OBDMID;
	@XmlElement(name = "TID")
	private String TID;
	@XmlElement(name = "UnitScalingId")
	private String UnitScalingId;
	@XmlElement(name = "RawValue")
	private String RawValue;
	@XmlElement(name = "RawMin")
	private String RawMin;
	@XmlElement(name = "RawMax")
	private String RawMax;
	@XmlElement(name = "TestsComplete")
	private boolean TestsComplete;

	public String getOBDMID() {
		return OBDMID;
	}

	public void setOBDMID(String oBDMID) {
		OBDMID = oBDMID;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getUnitScalingId() {
		return UnitScalingId;
	}

	public void setUnitScalingId(String unitScalingId) {
		UnitScalingId = unitScalingId;
	}

	public String getRawValue() {
		return RawValue;
	}

	public void setRawValue(String rawValue) {
		RawValue = rawValue;
	}

	public String getRawMin() {
		return RawMin;
	}

	public void setRawMin(String rawMin) {
		RawMin = rawMin;
	}

	public String getRawMax() {
		return RawMax;
	}

	public void setRawMax(String rawMax) {
		RawMax = rawMax;
	}

	public boolean isTestsComplete() {
		return TestsComplete;
	}

	public void setTestsComplete(boolean testsComplete) {
		TestsComplete = testsComplete;
	}

}
