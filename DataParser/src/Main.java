import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    private static DataManager DM;

    public static void main(String[] args) {
        DM = new DataManager();
        printHeader();

        Education.loadFromCSV( DM, DM.education_filename );
        // Tests:
//        printEducData("TEST", DM.getState("TEST").getCounty("test2").getEduc().getAllData() );
//        printEducData("TEST", calculateEducStateAvg("TEST") );
        printEducData("US", DM.getState("US").getCounty("UnitedStates").getEduc().getAllData() );
        printEducData("VA", calculateEducStateAvg("VA") );
        printEducData("Arlington County", DM.getState("VA").getCounty("ArlingtonCounty").getEduc().getAllData() );

        Employment.loadFromCSV( DM, DM.employment_filename);
        // Tests:
//        printEmployData("TEST", DM.getState("TEST").getCounty("test2").getEmploy().getAllData() );
//        printEmployData("TEST", calculateEmployStateAvg("TEST") );
        printEmployData("US", DM.getState("US").getCounty("UnitedStates").getEmploy().getAllData() );
        printEmployData("VA", calculateEmployStateAvg("VA") );
        printEmployData("Arlington County", DM.getState("VA").getCounty("ArlingtonCounty").getEmploy().getAllData() );


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

    private static void printEducData(String area, double[][] data) {
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

    private static void printEmployData(String area, double[][] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(area + "," + Employment.fields_name[i] + ",,,,,");
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
        double[][] data;

        State state = DM.getState( state_name );
        if (state == null) {
            for (int i = 0; i < Education.NUM_FIELDS; i++) {
                for (int j = 0; j < Education.NUM_FIELDS; j++) {
                    output[i][j] = -1;  }
            }
            return output;
        }

        int num_counties = 0;
        for (County c : state.getCounties()) {
            if (c.getEduc() != null) {
                num_counties++;
                data = c.getEduc().getAllData();
                for (int f = 0; f < Education.NUM_FIELDS; f++) {
                    for (int p = 0; p < Education.NUM_PERIODS; p++) {
                        output[f][p] += data[f][p];
                    }
                }
            }
        }

        // Calculate averages
        for (int f = 0; f < Education.NUM_FIELDS; f++) {
            for (int p = 0; p < Education.NUM_PERIODS; p++) {
                output[f][p] /= num_counties;
            }
        }

        return output;
    }

    private static double[][] calculateEmployStateAvg(String state_name) {
        double[][] output = new double[Employment.NUM_FIELDS][Employment.NUM_PERIODS];
        double[][] data = new double[Employment.NUM_FIELDS][Employment.NUM_PERIODS];

        State state = DM.getState( state_name );
        if (state == null) {
            for (int i = 0; i < Employment.NUM_FIELDS; i++) {
                for (int j = 0; j < Employment.NUM_FIELDS; j++) {
                    output[i][j] = -1;  }
            }
            return output;
        }

        int num_counties = 0;
        for (County c : state.getCounties()) {
            if (c.getEmploy() != null) {
                num_counties++;
                data = c.getEmploy().getAllData();
                for (int f = 0; f < Employment.NUM_FIELDS; f++) {
                    for (int p = 0; p < Employment.NUM_PERIODS; p++) {
                        output[f][p] += data[f][p];
                    }
                }
            }
        }

        // Calculate averages
        for (int f = 0; f < Employment.NUM_FIELDS; f++) {
            for (int p = 0; p < Employment.NUM_PERIODS; p++) {
                output[f][p] /= num_counties;
            }
        }

        return output;
    }
}
