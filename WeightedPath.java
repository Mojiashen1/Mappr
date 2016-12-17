import javafoundations.Queue;

public class WeightedPath<T> {
	private Queue<T> path;
	private int weight;

	public WeightedPath(Queue<T> path, int weight) {
		this.path = path;
		this.weight = weight;
	}

	public Queue<T> getPath() {
		return path;
	}

	public int getWeight() {
		return weight;
	}
}