
class Notes {
	public static void main(String[] args) {
		Rectangle rect = new Rectangle(10, 5);
		Triangle tri = new Triangle(15, 4);
		Circle cir = new Circle(5);

		System.out.println(cir);
		System.out.println(tri);
		System.out.println(rect);

		System.out.println(cir.getArea());
		System.out.println(tri.getArea());
		System.out.println(rect.getArea());

		System.out.println(cir.getPerimeter());
		System.out.println(tri.getPerimeter());
		System.out.println(rect.getPerimeter());

		cir.up(); //move the position up.
		cir.up();

		System.out.println(cir.position[0]);
		System.out.println(cir.position[1]);
	}
}
