package src.com.harm.solutions.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class CalibrationIds {

	@XmlElement(name = "Item")
	ArrayList<Item> items = new ArrayList<Item>();

	public ArrayList<Item> getItem() {
		return items;
	}

	public void setItem(ArrayList<Item> item) {
		this.items = item;
	}

}
