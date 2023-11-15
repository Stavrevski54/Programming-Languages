interface Two_D_shape {
    double PI = 3.14;

    abstract void parameters();

    abstract float area();

    abstract float perimeter();
}

interface Three_D_shape {
    abstract float volume();
}

class Triangle implements Two_D_shape {
    private double sideA, sideB, sideC, height;

    public Triangle(double sideA, double sideB, double sideC, double height) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.height = height;
        System.out.println("A new triangle is created");
    }

    @Override
    public void parameters() {
        System.out.println("Triangle parameters: SideA = " + sideA + ", SideB = " + sideB + ", SideC = " + sideC + ", Height = " + height);
    }

    @Override
    public float area() {
        return (float) (0.5 * sideB * height);
    }

    @Override
    public float perimeter() {
        return (float) (sideA + sideB + sideC);
    }

    public boolean rectangular() {
        return (sideA * sideA + sideB * sideB == sideC * sideC) ||
               (sideA * sideA + sideC * sideC == sideB * sideB) ||
               (sideB * sideB + sideC * sideC == sideA * sideA);
    }
}

class Sphere implements Two_D_shape, Three_D_shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
        System.out.println("A new sphere has been created");
    }

    @Override
    public void parameters() {
        System.out.println("Sphere parameters: Radius = " + radius);
    }

    @Override
    public float area() {
        return (float) (4 * PI * radius * radius);
    }

    @Override
    public float perimeter() {
        return (float) (2 * PI * radius);
    }

    @Override
    public float volume() {
        return (float) ((4.0 / 3.0) * PI * Math.pow(radius, 3));
    }
}

public class ShapesMain {
    public static void main(String[] args) {
        Triangle T1 = new Triangle(3, 5, 6, 3);
        Sphere B1 = new Sphere(4);
        Two_D_shape T2 = new Triangle(8, 2, 10, 16);
        Two_D_shape B2 = new Sphere(3);
        Three_D_shape B3 = new Sphere(7);

        T1.parameters();
        System.out.println("Triangle Area: " + T1.area());
        System.out.println("Triangle Perimeter: " + T1.perimeter());
        System.out.println("Is T1 rectangular? " + T1.rectangular());

        B1.parameters();
        System.out.println("Sphere Area: " + B1.area());
        System.out.println("Sphere Perimeter: " + B1.perimeter());
        System.out.println("Sphere Volume: " + ((Sphere) B1).volume());
    }
}
