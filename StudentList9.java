// STEP #10.	Add more comments and makes more naming improvements.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

// Declaration Constant class and application it..
class Constants {
    // File Name declation  constant
    final String STUDENTLIST = "students.txt";

    // Print Out String 
    final String PRINT = "Data Loaded.";
    final String DATE =  "dd/mm/yyyy-hh:mm:ss a";

    final String showAll = "-a";
    final String ShowRandom = "-r";
    final String addNewStudent = "-+";
    final String findStudent = "-?";
    final String countNoOfStudent = "-c";
}


public class StudentList9 {
    public static void main(String[] args) {
        // Constant Obeject declaration
        Constants constants = new Constants();

        // Check arguments
        if( args[0].length() == 0 ) {
            // No Argument pass and it dose not working other condition..
            System.out.println("No Arguments Pass! You can pass a, r, +, ? or c ");

            return;    // Exits Early
        }

        // Show All StudentList....
        else if (args[0].equals(constants.showAll)) {
            System.out.println(constants.PRINT+"..");

            // Handle Exception 
            try {
                // Call BufferedReader Methode
                String studentList = LodeStudentList(constants.STUDENTLIST);

                // Add all StudentList in a String...
                String students[] = studentList.split(",");

                // Print Out All StudentList...
                for (String student : students) {
                    System.out.println(student);
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } 
        
        // Show Random StudentList...
        else if (args[0].equals(constants.ShowRandom)) {
            System.out.println(constants.PRINT+"..");

            // Handle Exception
            try {
                // Call BufferedReader Methode
                String studentList = LodeStudentList(constants.STUDENTLIST);

                System.out.println(studentList);
                String students[] = studentList.split(",");
                Random random = new Random();
                int number = random.nextInt();

                // Print Random one StudentList...
                System.out.println(students[number]);
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } 
        
        // Added New Student in The List...
        else if (args[0].contains(constants.addNewStudent)) {
            System.out.println(constants.PRINT+"..");

            // Handle Exception
            try {
                BufferedWriter reader = new BufferedWriter(
                                        new FileWriter(constants.STUDENTLIST, true));

                // Cut  SubString in the StudentList...
                String students = args[0].substring(1);

                // Date Formet
                DateFormat dateFormat = new SimpleDateFormat(constants.DATE);
                String formateDate = dateFormat.format(new Date(0));
                reader.write(", " + students + "\nList last updated on " + formateDate);
                reader.close();
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } 
        
        // Find any student in the List exist or not
        else if (args[0].contains(constants.findStudent)) {
            System.out.println(constants.PRINT+"..");
            
            // Handle Exception
            try {
                // Call BufferedReader Methode
                String studentList = LodeStudentList(constants.STUDENTLIST);
                String students[] = studentList.split(",");
                String student = args[0].substring(1);
                int indexNumber = -1;

                // Check a Student List and put out its Index Number...
                for (int index = 0; index < students.length; index++) {
                    if (students[index].equals(student)) {
                        indexNumber = index;
                        break;
                    }
                }

                // Find the Sutdent in The studentlist..
                if( indexNumber > -1 ) {
                    System.out.println("We found it!" + "Found the Position is" + indexNumber);
                } 
                
                // Not Find the Sutdent in The studentlist..
                else {
                    System.out.println("Not found it!");
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } 
        
        // Count the Number of Word in the StudentList...
        else if (args[0].contains(constants.countNoOfStudent)) {
            System.out.println(constants.PRINT+"..");

            // Handle Exception
            try {
                String studentList = LodeStudentList(constants.STUDENTLIST);

                String[] students = studentList.trim().split("\\s+");
                int wordCount = students.length;
                System.out.println(wordCount + " word(s) found");
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        }
    }

    // BufferedReader Method call..
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
