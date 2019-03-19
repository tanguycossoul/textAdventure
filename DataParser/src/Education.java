public class Education {
    private double noHighSchool;    // percent
    private double onlyHighSchool;  // percent
    private double someCollege;     // percent
    private double bachelorsOrMore; // percent

    public Education() {
    }

    // * fields[0], int: FIPS Code
    // * fields[1], String: State
    // * fields[2], String: Area name
    // fields[3], int: 2003 Rural-urban Continuum Code
    // fields[4], int: 2003 Urban Influence Code
    // fields[5], int: 2013 Rural-urban Continuum Code
    // fields[6], int: 2013 Urban Influence Code
    // fields[7], int: "Less than a high school diploma, 1970",
    // fields[8], int: "High school diploma only, 1970"
    // fields[9], int: "Some college (1-3 years), 1970"
    // fields[10] int,: "Four years of college or higher, 1970"
    // fields[11], double: "Percent of adults with less than a high school diploma, 1970"
    // fields[12], double: "Percent of adults with a high school diploma only, 1970"
    // fields[13], double: "Percent of adults completing some college (1-3 years), 1970"
    // fields[14], double: "Percent of adults completing four years of college or higher, 1970"
    // fields[15], int: "Less than a high school diploma, 1980"
    // fields[16], int: "High school diploma only, 1980"
    // fields[17], int: "Some college (1-3 years), 1980"
    // fields[18], int: "Four years of college or higher, 1980"
    // fields[19], double: "Percent of adults with less than a high school diploma, 1980"
    // fields[20], double: "Percent of adults with a high school diploma only, 1980"
    // fields[21], double: "Percent of adults completing some college (1-3 years), 1980"
    // fields[22], double: "Percent of adults completing four years of college or higher, 1980"
    // fields[23], int: "Less than a high school diploma, 1990"
    // fields[24], int: "High school diploma only, 1990"
    // fields[25], int: "Some college or associate's degree, 1990"
    // fields[26], int: "Bachelor's degree or higher, 1990"
    // fields[27], double: "Percent of adults with less than a high school diploma, 1990"
    // fields[28], double: "Percent of adults with a high school diploma only, 1990"
    // fields[29], double: "Percent of adults completing some college or associate's degree, 1990"
    // fields[30], double: "Percent of adults with a bachelor's degree or higher, 1990"
    // fields[31], int: "Less than a high school diploma, 2000"
    // fields[32], int: "High school diploma only, 2000"
    // fields[33], int: "Some college or associate's degree, 2000"
    // fields[34], int: "Bachelor's degree or higher, 2000"
    // fields[35], double: "Percent of adults with less than a high school diploma, 2000"
    // fields[36], double: "Percent of adults with a high school diploma only, 2000"
    // fields[37], double: "Percent of adults completing some college or associate's degree, 2000"
    // fields[38], double: "Percent of adults with a bachelor's degree or higher, 2000"
    // fields[39], int: "Less than a high school diploma, 2012-2016"
    // fields[40], int: "High school diploma only, 2012-2016"
    // fields[41], int: "Some college or associate's degree, 2012-2016"
    // fields[42], int: "Bachelor's degree or higher, 2012-2016"
    // * fields[43], double: "Percent of adults with less than a high school diploma, 2012-2016"
    // * fields[44], double: "Percent of adults with a high school diploma only, 2012-2016"
    // * fields[45], double: "Percent of adults completing some college or associate's degree, 2012-2016"
    // * fields[46], double: "Percent of adults with a bachelor's degree or higher, 2012-2016"
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
                    educ.setNoHighSchool(Utils.safeStringToDouble(fields[43]));
                    educ.setOnlyHighSchool(Utils.safeStringToDouble(fields[44]));
                    educ.setSomeCollege(Utils.safeStringToDouble(fields[45]));
                    educ.setBachelorsOrMore(Utils.safeStringToDouble(fields[46]));
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

    // Setters and getters
    public double getNoHighSchool() {
        return noHighSchool;
    }

    public void setNoHighSchool(double noHighSchool) {
        this.noHighSchool = noHighSchool;
    }

    public double getOnlyHighSchool() {
        return onlyHighSchool;
    }

    public void setOnlyHighSchool(double onlyHighSchool) {
        this.onlyHighSchool = onlyHighSchool;
    }

    public double getSomeCollege() {
        return someCollege;
    }

    public void setSomeCollege(double someCollege) {
        this.someCollege = someCollege;
    }

    public double getBachelorsOrMore() {
        return bachelorsOrMore;
    }

    public void setBachelorsOrMore(double bachelorsOrMore) {
        this.bachelorsOrMore = bachelorsOrMore;
    }
}
