package com.kapil.jdom;

import java.util.List;

public class ServerDetails {

	private String id;
	private String serverName;
	private String secondsToWait;
	private List<String> services;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSecondsToWait() {
		return secondsToWait;
	}

	public void setSecondsToWait(String secondsToWait) {
		this.secondsToWait = secondsToWait;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "ServerDetails [id=" + id + ", serverName=" + serverName + ", secondsToWait=" + secondsToWait
				+ ", services=" + services + "]";
	}

}
