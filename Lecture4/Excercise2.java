import java.lang.Math;

public class excercise2 {

	public static void main(String[] args) {
		double decimalVariable = 5.445;
	       
        double tangentValue = Math.tan(decimalVariable);
        double cotangentValue = 1 / tangentValue;
        double circleRadius = decimalVariable;
        double circleArea = Math.PI * Math.pow(circleRadius, 2);
        
        System.out.println("Sine: " + Math.sin(decimalVariable));
        System.out.println("Cosine: " + Math.cos(decimalVariable));
        System.out.println("Tangent: " + Math.tan(decimalVariable));
        System.out.println("Cotangent: " + 1 / tangentValue );
        System.out.println("Square Root: " + Math.sqrt(decimalVariable));
        System.out.println("Square: " + Math.pow(decimalVariable, 2));
        System.out.println("Cube: " + Math.pow(decimalVariable, 3) );
        System.out.println("Smallest Integer Larger or Equal: " + Math.ceil(decimalVariable));
        System.out.println("Largest Integer Smaller or Equal: " + Math.floor(decimalVariable) );
        System.out.println("Area of the Circle: " + circleArea );
        System.out.println("Value of Pi: " + Math.PI);
        System.out.println("Value of E: " + Math.E);
	}

}
