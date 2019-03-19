public class Employment {
    private int totalLaborForce;
    private int employedLaborForce;
    private int unemployedLaborForce;

    public Employment() {
    }

    /**
     * field[0], int: FIPStxt
     * field[1], String: State
     * field[2], String: Area_name
     field[3], int : Rural_urban_continuum_code_2013
     field[4], int : Urban_influence_code_2013
     field[5], int: Metro_2013
     field[6], int: Civilian_labor_force_2007
     field[7], int: Employed_2007
     field[8], int : Unemployed_2007
     field[9], double: Unemployment_rate_2007
     field[10], int: Civilian_labor_force_2008
     field[11], int: Employed_2008
     field[12], int: Unemployed_2008
     field[13], double: Unemployment_rate_2008
     field[14], int: Civilian_labor_force_2009
     field[15], int: Employed_2009
     field[16], int: Unemployed_2009
     field[17], double: Unemployment_rate_2009
     field[18], int: Civilian_labor_force_2010
     field[19], int: Employed_2010
     field[20], int: Unemployed_2010
     field[21], double: Unemployment_rate_2010
     field[22], int: Civilian_labor_force_2011
     field[23], int: Employed_2011
     field[24], int: Unemployed_2011
     field[25], double: Unemployment_rate_2011
     field[26], int: Civilian_labor_force_2012
     field[27], int: Employed_2012
     field[28], int: Unemployed_2012
     field[29], double: Unemployment_rate_2012
     field[30], int: Civilian_labor_force_2013
     field[31], int: Employed_2013
     field[32], int: Unemployed_2013
     field[33], double: Unemployment_rate_2013
     field[34], int: Civilian_labor_force_2014
     field[35], int: Employed_2014
     field[36], int: Unemployed_2014
     field[37], double: Unemployment_rate_2014
     field[38], int: Civilian_labor_force_2015
     field[39], int: Employed_2015
     field[40], int: Unemployed_2015
     field[41], double: Unemployment_rate_2015
     * field[42], int: Civilian_labor_force_2016
     * field[43], int: Employed_2016
     * field[44], int: Unemployed_2016
     field[45], double: Unemployment_rate_2016
     field[46], int: Civilian_labor_force_2017
     field[47], int: Employed_2017
     field[48], int: Unemployed_2017
     field[49], double: Unemployment_rate_2017
     field[50], int: Median_Household_Income_2016
     field[51], double: Med_HH_Income_Percent_of_State_Total_2016
    **/
    public static boolean loadFromCSV(DataManager DM, String filename) {
        String data = Utils.readFileAsString( filename );
        String[] lines = data.split("\n");

        // Skip header lines, and go thru each line
        for (int i = DM.employment_file_start; i < lines.length; i++) {
            String[] fields = extractCleanFields( lines[i] );

            State state = DM.safeAddState( fields[1] );
            if (state != null) {
                County county = state.safeAddCounty(fields[2], Utils.safeStringToInt(fields[0]));
                if (county != null) {
                    Employment employ = new Employment();
                    county.setEmploy( employ );
                    employ.setTotalLaborForce( Utils.safeStringToInt(fields[42]) );
                    employ.setEmployedLaborForce( Utils.safeStringToInt(fields[43]) );
                    employ.setUnemployedLaborForce( Utils.safeStringToInt(fields[44]) );

                    if (employ.getTotalLaborForce() != employ.getEmployedLaborForce() + employ.getUnemployedLaborForce()) {
                        System.out.println("WARNING: employment data doesn't add up: line=" + lines[i]);
                    }
                }
            }
        }
        return true;
    }

    private static String[] extractCleanFields(String line) {
        line = Utils.cleanupLine( line );
        String[] fields = line.split(",", -1);
        fields[2] = removeStateFromCountyName( fields[2], fields[1] );
        return fields;
    }

    private static String removeStateFromCountyName(String countyName, String State) {
        String last2Chars = countyName.substring(countyName.length() - 2, countyName.length());
        if (last2Chars.equals(State)) {
            return countyName.substring(0, countyName.length() - 2);
        }
        else {
            return countyName;
        }
    }

    public int getTotalLaborForce() {
        return totalLaborForce;
    }

    public void setTotalLaborForce(int totalLaborForce) {
        this.totalLaborForce = totalLaborForce;
    }

    public int getEmployedLaborForce() {
        return employedLaborForce;
    }

    public void setEmployedLaborForce(int employedLaborForce) {
        this.employedLaborForce = employedLaborForce;
    }

    public int getUnemployedLaborForce() {
        return unemployedLaborForce;
    }

    public void setUnemployedLaborForce(int unemployedLaborForce) {
        this.unemployedLaborForce = unemployedLaborForce;
    }
}
