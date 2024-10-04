// STEP #6.	Remove Temporary variables

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

public class StudentList5 {
    public static void main(String[] args) {
        Constants constants = new Constants();

        // Check arguments
        if( args[0].length() == 0 ) {
            System.out.println("No Arguments Pass! You can pass a, r, +, ? or c ");
            return;         // Exits Early
        }  else if (args[0].equals(constants.showAll)) {
            System.out.println(constants.PRINT+"..");
            try {
                String reads = LoadData(constants.STUDENTLIST);
                String words[] = reads.split(",");
                for (String word : words) {
                    System.out.println(word);
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].equals(constants.ShowRandom)) {
            System.out.println(constants.PRINT+"..");
            try {
                String reads = LoadData(constants.STUDENTLIST);
                System.out.println(reads);
                String words[] = reads.split(",");
                Random random = new Random();
                int number = random.nextInt();
                System.out.println(words[number]);
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.addNewStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                BufferedWriter reader = new BufferedWriter(
                                        new FileWriter(constants.STUDENTLIST, true));
                String words = args[0].substring(1);
                DateFormat dateFormat = new SimpleDateFormat(constants.DATE);
                String formateDate = dateFormat.format(new Date(0));
                reader.write(", " + words + "\nList last updated on " + formateDate);
                reader.close();
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.findStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                String reads = LoadData(constants.STUDENTLIST);
                String words[] = reads.split(",");
                boolean done = false;
                String word = args[0].substring(1);
                for (int index = 0; index < words.length && !done; index++) {
                    if (words[index].equals(word)) {
                        System.out.println("We found it!");
                        done = true;
                    }
                }
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        } else if (args[0].contains(constants.countNoOfStudent)) {
            System.out.println(constants.PRINT+"..");
            try {
                String reads = LoadData(constants.STUDENTLIST);
                char characters[] = reads.toCharArray();
                boolean in_word = false;
                int count = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!in_word) {
                            count++;
                            in_word = true;
                        } else {
                            in_word = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + characters.length);
            } catch (Exception e) {}
            System.out.println(constants.PRINT);
        }
    }

    static String LoadData( String fileName ) {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(
                                    new FileInputStream("students.txt")));
            line = reader.readLine();
            reader.close();
        } catch (Exception e) {}
        return line;
    }
}
