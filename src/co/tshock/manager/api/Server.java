package co.tshock.manager.api;

import com.j256.ormlite.field.DatabaseField;

public class Server {
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private String displayName;
	
	@DatabaseField
	private String ip;
	
	@DatabaseField
	private int port;

	/**
	 * Construct a new server
	 * 
	 * @param displayName
	 * @param ip
	 * @param port
	 */
	public Server(String displayName, String ip, int port) {
		super();
		this.displayName = displayName;
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

/**
	 * Gets a human readable {@link String] of IP and port combined
	 * @return the host
	 */
	public String getHost() {
		return String.format("%s:%d", ip, port);
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Server))
			return false;
		Server other = (Server) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[Server] id=" + id + ",displayName=" + displayName + ",ip=" + ip + ",port"
				+ port + ",host=" + getHost();
	}
}
