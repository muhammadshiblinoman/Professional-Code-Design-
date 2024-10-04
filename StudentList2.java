// STEP #3.	Makes improvements to variable names

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class StudentList2 {
    public static void main(String[] args) {
        // Check arguments
        if( args[0].length() == 0 ) {
            System.out.println("No Arguments Pass!");
            return;         // Exits Early
        }  else if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                        new FileInputStream("students.txt")));
                String reads = reader.readLine();
                String words[] = reads.split(",");
                for (String word : words) {
                    System.out.println(word);
                }
                reader.close();
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                        new FileInputStream("students.txt")));
                String reads = reader.readLine();
                System.out.println(reads);
                String words[] = reads.split(",");
                Random random = new Random();
                int number = random.nextInt();
                System.out.println(words[number]);
                reader.close();
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter reader = new BufferedWriter(
                                        new FileWriter("students.txt", true));
                String words = args[0].substring(1);
                Date date = new Date(0);
                String dateTime = "dd/mm/yyyy-hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(dateTime);
                String formateDate = dateFormat.format(date);
                reader.write(", " + words + "\nList last updated on " + formateDate);
                reader.close();
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                        new FileInputStream("students.txt")));
                String reads = reader.readLine();
                String words[] = reads.split(",");
                boolean done = false;
                String word = args[0].substring(1);
                for (int index = 0; index < words.length && !done; index++) {
                    if (words[index].equals(word)) {
                        System.out.println("We found it!");
                        done = true;
                    }
                }
                reader.close();
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                        new FileInputStream("students.txt")));
                String reads = reader.readLine();
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
                reader.close();
                System.out.println(count + " word(s) found " + characters.length);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
    }
}