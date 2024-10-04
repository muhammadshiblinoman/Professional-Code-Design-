// STEP #9.	Adds handling for case when user enters invalid arguments

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

class Constants {
    final String STUDENTLIST = "students.txt";
    final String PRINT = "Data Loaded.";
    final String DATE =  "dd/mm/yyyy-hh:mm:ss a";

    final String showAll = "-a";
    final String ShowRandom = "-r";
    final String addNewStudent = "-+";
    final String findStudent = "-?";
    final String countNoOfStudent = "-c";
}


public class StudentList8 {
    public static void main(String[] args) {
        Constants constants = new Constants();
        // Check arguments
        if( args[0].length() == 0 ) {
            System.out.println("No Arguments Pass! You can pass a, r, +, ? or c ");
            return;         // Exits Early
        }  else if (args[0].equals(constants.showAll)) {
            System.out.println(constants.PRINT+"..");
            try {
                String studentList = LodeStudentList(constants.STUDENTLIST);
                String students[] = studentList.split(",");
                for (String student : students) {
                    System.out.println(student);
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].equals(constants.ShowRandom)) {
            System.out.println(constants.PRINT+"..");
            try {
                String studentList = LodeStudentList(constants.STUDENTLIST);
                System.out.println(studentList);
                String students[] = studentList.split(",");
                Random random = new Random();
                int number = random.nextInt();
                System.out.println(students[number]);
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.addNewStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                BufferedWriter reader = new BufferedWriter(
                                        new FileWriter(constants.STUDENTLIST, true));
                String students = args[0].substring(1);
                DateFormat dateFormat = new SimpleDateFormat(constants.DATE);
                String formateDate = dateFormat.format(new Date(0));
                reader.write(", " + students + "\nList last updated on " + formateDate);
                reader.close();
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.findStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                String studentList = LodeStudentList(constants.STUDENTLIST);
                String students[] = studentList.split(",");
                String student = args[0].substring(1);
                int indexNumber = -1;
                for (int index = 0; index < students.length; index++) {
                    if (students[index].equals(student)) {
                        indexNumber = index;
                        break;
                    }
                }
                if( indexNumber > -1 ) {
                    System.out.println("We found it!" + "Found the Position is" + indexNumber);
                } else {
                    System.out.println("Not found it!");
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.countNoOfStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                String studentList = LodeStudentList(constants.STUDENTLIST);
                String[] students = studentList.trim().split("\\s+");
                int wordCount = students.length;
                System.out.println(wordCount + " word(s) found");
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        }
    }

    static String LodeStudentList( String studentName ) {
        try {
            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(
                                    new FileInputStream("students.txt")));
            studentName = reader.readLine();
            reader.close();
        } catch (Exception e) {}
        return studentName;
    }
}
