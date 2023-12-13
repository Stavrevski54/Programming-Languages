import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input2 {

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(isr);
        String name = "";
        int birthYear = 0;
        double height = 0.0;
        double weight = 0.0;

        System.out.print("What is your name? ");
        try {
            name = input.readLine();

            System.out.print("Which year you were born in? ");
            birthYear = Integer.parseInt(input.readLine());

            System.out.print("How tall <in meters> are you? ");
            height = Double.parseDouble(input.readLine());

            System.out.print("What is your weight <in kilograms>? ");
            weight = Double.parseDouble(input.readLine());

        } catch (Exception e) {
            System.out.println("An exception has occurred");
            return;
        }

        int currentYear = java.time.Year.now().getValue();
        int age = currentYear - birthYear;
        double body_mass_index = weight / (height * height);

        System.out.println("\nYour name is " + name);
        System.out.println("This year you'll turn " + age);
        System.out.println("Your body-mass index is " + body_mass_index);
    }
}
