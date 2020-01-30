package draw;

public class Position {
	private double x;
	private double y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Position() {
		this.x = 0;
		this.y = 0;
	}

	public double getX() { return this.x; }
	public void setX(double x) { this.x = x; }

	public double getY() { return this.y; }
	public void setY(double y) { this.y = y; }

	public String toString() { return "" + this.x + "," + this.y; }
}
