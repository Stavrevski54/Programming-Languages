import java.util.Random;

interface Average {
    float average();
}

interface Scholarship {
    float rate();
}

class Person {
    String p_name;

    public Person(String name) {
        this.p_name = name;
    }
}

class Employee extends Person implements Average {
    int no_salaries;
    int[] salary = new int[12];

    public Employee(String name, int n) {
        super(name);
        this.no_salaries = n;
        Random rand = new Random();
        for (int i = 0; i < 12; i++) {
            this.salary[i] = rand.nextInt(30001);
        }
    }

    @Override
    public float average() {
        if (this.no_salaries == 0) {
            return 0;
        }
        float sum = 0;
        for (int s : this.salary) {
            sum += s;
        }
        return sum / this.no_salaries;
    }
}

class Student extends Person implements Average, Scholarship {
    int no_grades;
    Employee parent1, parent2;
    int[] grade = new int[6];

    public Student(String name, int n, Employee r1, Employee r2) {
        super(name);
        this.no_grades = n;
        this.parent1 = r1;
        this.parent2 = r2;
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            this.grade[i] = rand.nextInt(6) + 5;
        }
    }

    @Override
    public float average() {
        float sum = 0;
        for (int g : this.grade) {
            sum += g;
        }
        return sum / this.no_grades;
    }

    @Override
    public float rate() {
        float gpa = average();
        float parentSalaries = parent1.average() + parent2.average();
        return gpa * 6 + (60000 - parentSalaries) * 4;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[6];
        for (int i = 0; i < 6; i++) {
            if (i < 2) {
                employees[i] = new Employee("Unemployed", 0);
            } else {
                employees[i] = new Employee("Employee" + (i - 1), 12);
            }
        }

        Student[] students = new Student[3];
        students[0] = new Student("Student1", 6, employees[0], employees[1]);
        students[1] = new Student("Student2", 6, employees[2], employees[3]);
        students[2] = new Student("Student3", 6, employees[4], employees[5]);

        for (Student student : students) {
            System.out.println("Name: " + student.p_name);
            System.out.println("GPA: " + student.average());
            System.out.println("Parent 1: " + student.parent1.p_name + " - Average Salary: " + student.parent1.average());
            System.out.println("Parent 2: " + student.parent2.p_name + " - Average Salary: " + student.parent2.average());
            System.out.println("Scholarship Points: " + student.rate());
            System.out.println();
        }

        Student scholarshipRecipient = findScholarshipRecipient(students);
        System.out.println("The student who will get the scholarship is: " + scholarshipRecipient.p_name);
    }

    private static Student findScholarshipRecipient(Student[] students) {
        Student maxPointsStudent = students[0];
        float maxPoints = maxPointsStudent.rate();

        for (int i = 1; i < students.length; i++) {
            float points = students[i].rate();
            if (points > maxPoints) {
                maxPoints = points;
                maxPointsStudent = students[i];
            }
        }

        return maxPointsStudent;
    }
}
