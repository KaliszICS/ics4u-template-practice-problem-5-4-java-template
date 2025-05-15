class Triangle implements Shape {
	
	public double base;
	public double height;

	public Triangle (double base, double height) {
		this.base = base;
		this.height = height;
	}

	@Override
	public double getArea() {
		return this.base * this.height / 2;
	}

	@Override
	public double getPerimeter() {
		return 0; //unknown at the moment
	}

	@Override
	public String toString() {
		return "Triangle: " + this.base + " by " + this.height;
	}

}