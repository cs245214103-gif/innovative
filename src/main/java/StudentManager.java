import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int roll;
    String name;
    int marks;

    Student(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    void display() {
        System.out.println("Roll No: " + roll + " Name: " + name + " Marks: " + marks);
    }
}

public class StudentRecordManager {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Student Record Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();

                    students.add(new Student(roll, name, marks));
                    System.out.println("Student Added Successfully");
                    break;

                case 2:
                    System.out.println("\nStudent Records:");
                    for (Student s : students) {
                        s.display();
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}