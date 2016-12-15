public class RouteDataTransfer {
	private String from, to, preferences;

	public RouteDataTransfer(String from, String to, String preferences) {
		this.from = from;
		this.to = to;
		this.preferences = preferences;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getPreferences() {
		return preferences;
	}
}