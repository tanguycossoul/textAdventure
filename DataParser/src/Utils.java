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

    public static String cleanupLine(String line) {
        line = removeQuotesAndComa(line);
        line = line.replace("%", "");
        line = line.replace("$", "");
        line = line.replace("\t", "");
        line = line.replace(" ", "");
        return line;
    }

    public static String removeQuotesAndComa(String line) {
        int firstQuote = line.indexOf("\"");
        int secondQuote = line.indexOf("\"", firstQuote + 1);

        while (firstQuote != -1 && secondQuote != -1) {
            String fieldOrigin = line.substring(firstQuote, secondQuote+1);
            String fieldClean = fieldOrigin.replace(",", "").trim();
            fieldClean = fieldClean.replace("\"", "");

            line = line.replace(fieldOrigin, fieldClean);

            firstQuote = line.indexOf("\"");
            secondQuote = line.indexOf("\"", firstQuote + 1);

//            if (firstQuote != -1 ^ secondQuote != 1) {
//                System.out.println("WARNING: single quote found in line: " + line);
//            }
        }
        return line;
    }

    public static double safeStringToDouble(String s) {
        if ( s.equals("") ) { return 0; }

        double res = 0;
        try {
            res = Double.parseDouble( s );
        }
        catch (Exception e) {
            System.out.println("WARNING: safeStringToDouble() of s=" + s);
            return -1;
        }
        return res;
    }

    public static int safeStringToInt(String s) {
        if ( s.equals("") ) { return 0; }

        int res = 0;
        try {
            res = Integer.parseInt( s );
        }
        catch (Exception e) {
            System.out.println("WARNING: safeStringToInt() of s=" + s);
            return -1;
        }
        return  res;
    }

}
