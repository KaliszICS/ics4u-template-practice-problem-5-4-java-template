class Rectangle implements Shape, Transformable {

	int x = 0;
	int y = 0;

	public double length;
	public double width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	//Shape methods we need to implement
	@Override
	public double getArea() {
		return this.length * this.width;
	}
	@Override
	public double getPerimeter() {
		return this.length * 2 + this.width * 2;
	}
	@Override
	public String toString() {
		return "Rectangle: " + this.length + " by " + this.width;
	}

	//Transformable methods we need to implement
	@Override
	public void up() {
		y += 1;
	}
	@Override
	public void down() {
		y -= 1;
	}
	@Override
	public void left() {
		x -= 1;
	}
	@Override
	public void right() {
		x += 1;
	}
}