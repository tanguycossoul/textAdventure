import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    private static DataManager DM;

    public static void main(String[] args) {
        DM = new DataManager();

        Education.loadFromCSV( DM, DM.education_filename );

        printHeader();

        // Tests:
//        printData("TEST", DM.getState("TEST").getCounty("test2").getEduc().getAllData() );
//        printData("TEST", calculateEducStateAvg("TEST") );

        printData("US", DM.getState("US").getCounty("UnitedStates").getEduc().getAllData() );
        printData("VA", calculateEducStateAvg("VA") );
        printData("Arlington County", DM.getState("VA").getCounty("ArlingtonCounty").getEduc().getAllData() );

//        Election.loadFromCSV( DM, DM.election_filename);
//        Employment.loadFromCSV( DM, DM.employment_filename );
    }

    private static void printHeader() {
        System.out.print("Area,Type," );
        for (int i = 0; i < Education.periods.length; i++) {
            System.out.print(Education.periods[i]);
            if (i < Education.periods.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    private static void printData(String area, double[][] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(area + "," + Education.fields_name[i] + ",");
            for (int j = 0; j < data[0].length; j++) {
                System.out.print( (int) data[i][j]);
                if (j < data[0].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    private static double[][] calculateEducStateAvg(String state_name) {
        double[][] output = new double[Education.NUM_FIELDS][Education.NUM_PERIODS];
        double[][] data = new double[Education.NUM_FIELDS][Education.NUM_PERIODS];

        State state = DM.getState( state_name );
        if (state == null) {
            for (int i = 0; i < Education.NUM_FIELDS; i++) {
                for (int j = 0; j < Education.NUM_FIELDS; j++) {
                    output[i][j] = -1;  }
            }
            return output;
        }

        for (County c : state.getCounties()) {
            data = c.getEduc().getAllData();
            for (int f = 0; f < Education.NUM_FIELDS; f++) {
                for (int p = 0; p < Education.NUM_PERIODS; p++) {
                    output[f][p] += data[f][p];
                }
            }
        }

        // Calculate averages
        int num_counties = state.getCounties().size();
        for (int f = 0; f < Education.NUM_FIELDS; f++) {
            for (int p = 0; p < Education.NUM_PERIODS; p++) {
                output[f][p] /= num_counties;
            }
        }

        return output;
    }
}
