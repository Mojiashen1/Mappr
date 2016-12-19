public class RouteDataTransfer {
	private String from, to;
	private Prefs preferences;

	public RouteDataTransfer(String from, String to, Prefs preferences) {
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

	public Prefs getPreferences() {
		return preferences;
	}
}