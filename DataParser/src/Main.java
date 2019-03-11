import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    private static DataManager DM;

    public static void main(String[] args) {
        DM = new DataManager();

        Election.loadFromCSV( DM, DM.election_filename);
//        Education.loadFromCSV( DM, DM.education_filename );
//        Employment.loadFromCSV( DM, DM.employment_filename );
    }
}
