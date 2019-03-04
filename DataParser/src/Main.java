import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/*
    @Author: Tanguy Cossoul
 */
public class Main {

    public static void main(String[] args) {
        String data = Utils.readFileAsString("data\\2016_Presidential_Results.csv");
        ArrayList<ElectionResult> results = Utils.parse2016ElectionResults( data );
    }
}
