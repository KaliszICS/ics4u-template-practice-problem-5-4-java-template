class Circle implements Shape, Transformable {//use implements to implement interfaces
	
	public double radius;
	int[] position = new int[]{0, 0};

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return this.radius * this.radius * Math.PI;
	}

	@Override
	public double getPerimeter() {
		return this.radius*2*Math.PI;
	}

	@Override
	public String toString() {
		return "Circle: " + this.radius;
	}

	@Override
	public void up() {
		position[1] += this.radius;
	}

	@Override
	public void down() {
		position[1] -= this.radius;
	}

	@Override
	public void left() {
		position[0] -= this.radius;
	}

	@Override
	public void right() {
		position[0] += this.radius;
	}

}