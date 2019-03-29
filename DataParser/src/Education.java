public class Education {
    public static int NUM_PERIODS;
    public static String[] periods = new String[] {"1970", "1980", "1990", "2000", "2012-2016"};

    public static int NUM_FIELDS;
    public static String[] fields_name = new String[] {"noHighSchool", "onlyHighSchool", "someCollege", "bachelorsOrMore"};
    private int[] noHighSchool = new int[ periods.length ];    // count per period
    private int[] onlyHighSchool = new int[ periods.length ];  // count per period
    private int[] someCollege = new int[ periods.length ];     // count per period
    private int[] bachelorsOrMore = new int[ periods.length ]; // count per period

    public Education() {
        NUM_PERIODS = periods.length;
        NUM_FIELDS = fields_name.length;
    }

    // * fields[0], int: FIPS Code
    // * fields[1], String: State
    // * fields[2], String: Area name
    // fields[3], int: 2003 Rural-urban Continuum Code
    // fields[4], int: 2003 Urban Influence Code
    // fields[5], int: 2013 Rural-urban Continuum Code
    // fields[6], int: 2013 Urban Influence Code
    // * fields[7], int: "Less than a high school diploma, 1970",
    // * fields[8], int: "High school diploma only, 1970"
    // * fields[9], int: "Some college (1-3 years), 1970"
    // * fields[10] int,: "Four years of college or higher, 1970"
    // fields[11], double: "Percent of adults with less than a high school diploma, 1970"
    // fields[12], double: "Percent of adults with a high school diploma only, 1970"
    // fields[13], double: "Percent of adults completing some college (1-3 years), 1970"
    // fields[14], double: "Percent of adults completing four years of college or higher, 1970"
    // * fields[15], int: "Less than a high school diploma, 1980"
    // * fields[16], int: "High school diploma only, 1980"
    // * fields[17], int: "Some college (1-3 years), 1980"
    // * fields[18], int: "Four years of college or higher, 1980"
    // fields[19], double: "Percent of adults with less than a high school diploma, 1980"
    // fields[20], double: "Percent of adults with a high school diploma only, 1980"
    // fields[21], double: "Percent of adults completing some college (1-3 years), 1980"
    // fields[22], double: "Percent of adults completing four years of college or higher, 1980"
    // * fields[23], int: "Less than a high school diploma, 1990"
    // * fields[24], int: "High school diploma only, 1990"
    // * fields[25], int: "Some college or associate's degree, 1990"
    // * fields[26], int: "Bachelor's degree or higher, 1990"
    // fields[27], double: "Percent of adults with less than a high school diploma, 1990"
    // fields[28], double: "Percent of adults with a high school diploma only, 1990"
    // fields[29], double: "Percent of adults completing some college or associate's degree, 1990"
    // fields[30], double: "Percent of adults with a bachelor's degree or higher, 1990"
    // * fields[31], int: "Less than a high school diploma, 2000"
    // * fields[32], int: "High school diploma only, 2000"
    // * fields[33], int: "Some college or associate's degree, 2000"
    // * fields[34], int: "Bachelor's degree or higher, 2000"
    // fields[35], double: "Percent of adults with less than a high school diploma, 2000"
    // fields[36], double: "Percent of adults with a high school diploma only, 2000"
    // fields[37], double: "Percent of adults completing some college or associate's degree, 2000"
    // fields[38], double: "Percent of adults with a bachelor's degree or higher, 2000"
    // * fields[39], int: "Less than a high school diploma, 2012-2016"
    // * fields[40], int: "High school diploma only, 2012-2016"
    // * fields[41], int: "Some college or associate's degree, 2012-2016"
    // * fields[42], int: "Bachelor's degree or higher, 2012-2016"
    // fields[43], double: "Percent of adults with less than a high school diploma, 2012-2016"
    // fields[44], double: "Percent of adults with a high school diploma only, 2012-2016"
    // fields[45], double: "Percent of adults completing some college or associate's degree, 2012-2016"
    // fields[46], double: "Percent of adults with a bachelor's degree or higher, 2012-2016"
    // fields[47], -: none
    public static boolean loadFromCSV(DataManager DM, String filename) {
        String data = Utils.readFileAsString( filename );
        String[] lines = data.split("\n");

        // Skip header lines, and go thru each line
        for (int i = DM.education_file_start; i < lines.length; i++) {
            String[] fields = extractCleanFields( lines[i] );

            State state = DM.safeAddState( fields[1] );
            if (state != null) {
                County county = state.safeAddCounty(fields[2], Utils.safeStringToInt(fields[0]));
                if (county != null) {
                    Education educ = new Education();
                    county.setEduc(educ);
                    educ.setNoHighSchool(     0, Utils.safeStringToInt(fields[7]) );
                    educ.setOnlyHighSchool(   0, Utils.safeStringToInt(fields[8]) );
                    educ.setSomeCollege(      0, Utils.safeStringToInt(fields[9]) );
                    educ.setBachelorsOrMore(  0, Utils.safeStringToInt(fields[10]) );

                    educ.setNoHighSchool(     1, Utils.safeStringToInt(fields[15]) );
                    educ.setOnlyHighSchool(   1, Utils.safeStringToInt(fields[16]) );
                    educ.setSomeCollege(      1, Utils.safeStringToInt(fields[17]) );
                    educ.setBachelorsOrMore(  1, Utils.safeStringToInt(fields[18]) );

                    educ.setNoHighSchool(     2, Utils.safeStringToInt(fields[23]) );
                    educ.setOnlyHighSchool(   2, Utils.safeStringToInt(fields[24]) );
                    educ.setSomeCollege(      2, Utils.safeStringToInt(fields[25]) );
                    educ.setBachelorsOrMore(  2, Utils.safeStringToInt(fields[26]) );

                    educ.setNoHighSchool(     3, Utils.safeStringToInt(fields[31]) );
                    educ.setOnlyHighSchool(   3, Utils.safeStringToInt(fields[32]) );
                    educ.setSomeCollege(      3, Utils.safeStringToInt(fields[33]) );
                    educ.setBachelorsOrMore(  3, Utils.safeStringToInt(fields[34]) );

                    educ.setNoHighSchool(     4, Utils.safeStringToInt(fields[39]) );
                    educ.setOnlyHighSchool(   4, Utils.safeStringToInt(fields[40]) );
                    educ.setSomeCollege(      4, Utils.safeStringToInt(fields[41]) );
                    educ.setBachelorsOrMore(  4, Utils.safeStringToInt(fields[42]) );
                }
            }
        }
        return true;
    }

    private static String[] extractCleanFields(String line) {
        line = Utils.cleanupLine( line );
        String[] fields = line.split(",", -1);
        return fields;
    }

    public double[][] getAllData() {
        double[][] output = new double[NUM_FIELDS][NUM_PERIODS];
        for (int period = 0; period < NUM_PERIODS; period++) {
            output[0][period] = getNoHighSchool( period );
            output[1][period] = getOnlyHighSchool( period );
            output[2][period] = getSomeCollege( period );
            output[3][period] = getBachelorsOrMore( period );
        }
        return output;
    }


    public int getNoHighSchool(int period) {
        return noHighSchool[ period ];
    }


    public void setNoHighSchool(int period, int noHighSchool) {
        this.noHighSchool[ period ] = noHighSchool;
    }

    public int getOnlyHighSchool(int period) {
        return onlyHighSchool[ period ];
    }

    public void setOnlyHighSchool(int period, int onlyHighSchool) {
        this.onlyHighSchool[ period ] = onlyHighSchool;
    }

    public int getSomeCollege(int period) {
        return someCollege[ period ];
    }

    public void setSomeCollege(int period, int someCollege) {
        this.someCollege[ period ] = someCollege;
    }

    public int getBachelorsOrMore(int period) {
        return bachelorsOrMore[ period ];
    }

    public void setBachelorsOrMore(int period, int bachelorsOrMore) {
        this.bachelorsOrMore[ period ] = bachelorsOrMore;
    }
}
