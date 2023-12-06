import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
class Student {
    String name;
    int indexNo;
    int age;
    public Student(String name, int indexNo, int age) {
        this.name = name;
        this.indexNo = indexNo;
        this.age = age;
    }
}

public class Demo {
    public static Student
    createStudent(String name, int indexNo) {
        Random random = new Random();
        int age = random.nextInt(13) + 18;
        return new Student(name, indexNo, age);
    }
    public static void printValues(Student s) {
        System.out.println("Name: " + s.name + ", Index No: " + s.indexNo + ", Age: " + s.age);
    }

    public static void main(String[] args) {
        List<String> initialListOfNames = Arrays.asList("Reflection", "Collection", "Stream", "Java", "Sorting");
        List<Student> students = initialListOfNames.stream()
                .map(name -> createStudent(name, new Random().nextInt(10000) + 1))
                .collect(Collectors.toList());
        students.forEach(Demo::printValues);
    }
}
