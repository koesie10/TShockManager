package co.tshock.manager.events;

public enum EventType {
	ERROR, CREATE_TOKEN("/token/create/%s/%s"), STATUS("/status"), DESTROY_TOKEN("/token/destroy/%s"), SERVER_BROADCAST("/v2/server/broadcast");
	
	private String url;
	
	private EventType(String url) {
		this.url = url;
	}
	
	private EventType() {
		this("");
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUrl(Object... args) {
		return String.format(url, args);
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
