package com.example.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
public class Device {
	private String deviceIp;
	private int deviceStatus;

	public Device() {
	}

	public Device(String deviceIp) {
		super();
		this.deviceIp = deviceIp;
	}

	@XmlAttribute
	public String getIp() {
		return deviceIp;
	}

	public void setIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	@XmlAttribute
	public int getStatus() {
		return deviceStatus;
	}

	public void setStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
}
