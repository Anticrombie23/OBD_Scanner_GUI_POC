package src.com.harm.solutions.models;

import javax.xml.bind.annotation.XmlElement;

public class TroubleCodes {
	@XmlElement(name = "AdditionalInformation")
	AdditionalInformation AdditionalInformationObject;

	// Getter Methods

	public AdditionalInformation getAdditionalInformation() {
		return AdditionalInformationObject;
	}

	// Setter Methods

	public void setAdditionalInformation(AdditionalInformation AdditionalInformationObject) {
		this.AdditionalInformationObject = AdditionalInformationObject;
	}
}
