public class Vehicle {
	
public class Car {
	private String type;
	private int no_seats ;
	private String fuel;
	private String body_color;
	
	Car(){
		type="";
		no_seats=0;
		fuel="";
		body_color="";
	}
	public Car(String type, int seats, String fuel, String body_color){
		this.type=type;
		this.no_seats=seats;
		this.fuel=fuel;
		this.body_color=body_color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
	public int no_seats() {
		return no_seats;
	}
	public void no_seats(int no_seats) {
		this.no_seats=no_seats;
	}
	public String setFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel=fuel;
	}
	public String setBody_color() {
		return body_color;
	}
	public void setBody_color(String body_color) {
		this.body_color=body_color;
	}
	
		public static void main(String[] args) {
			Car car1 = new Car("Toyota",5,"dizel","green");
			Car car2 = new Car();
			
		System.out.println("Car 1 details:");
		System.out.println("Type" + car1.type);
		System.out.println("Seats" + car1.no_seats);
		System.out.println("Fuel" + car1.fuel);
		System.out.println("Body color" + car1.body_color);
		
		car2.setType("Honda");
		car2.no_seats(5);
		car2.setFuel("Benzin");
		car2.setBody_color("Yellow");
		
		System.out.println("Car 2 details:");
		System.out.println("Type" + car2.type);
		System.out.println("Seats" + car2.no_seats);
		System.out.println("Fuel" + car2.fuel);
		System.out.println("Body color" + car2.body_color);

		}
	}

}
