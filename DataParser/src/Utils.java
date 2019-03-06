import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> output = new ArrayList<>();
        String[] lines = data.split("\n");

        // Skip first line with header definition
        System.out.println(lines[0]);
        for (int i = 1; i < lines.length; i++) {
            lines[i] = removeSpecialCharacters(lines[i]);
            String[] fields = lines[i].split(",");
            ElectionResult er = new ElectionResult( fields );
            System.out.println( er.toString() );
        }
        return output;
    }

    // Note: make it public before running UtilsTest
    private static String removeSpecialCharacters(String line) {
        // Remove from per_point_diff: the % character
        line = line.replace("%", "");

        // Remove from diff: quotes and middle coma
        int quote_start_index = line.indexOf("\"");
        int quote_end_index = line.indexOf("\"", quote_start_index+1);

        if (quote_start_index == -1 || quote_end_index == -1) {
            return line;
        }

        String s1 = line.substring(quote_start_index, quote_end_index+1);
        String s2 = s1.replace("\"", "");
        String s3 = s2.replace(",", "");
        return line.replace(s1, s3);
    }
}
