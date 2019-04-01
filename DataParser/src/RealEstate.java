import com.sun.deploy.util.ArrayUtil;

public class RealEstate {
    public static int NUM_PERIODS;
    public static String[] periods = new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"};

    public static int NUM_FIELDS;
    public static String[] fields_name = new String[] {"salesPrice"};
    private int[] salePrice = new int[ periods.length ];

    public RealEstate() {
        NUM_PERIODS = periods.length;
        NUM_FIELDS = fields_name.length;
    }

    // fields[0]: propertyKey
    // fields[1]: realEstatePropertyCode
    // fields[2]: provalLrsnId
    // fields[3]: neighborhoodNbr
    // fields[4]: propertyStreetNbrNameText
    // fields[5]: salesTypeCode
    // fields[6]: salesTypeDsc
    // fields[7]: deedBookNbr
    // fields[8]: deedPageNbr
    // fields[9]: granteeName
    // * fields[10]: saleAmt (non-zero)
    // * fields[11]: saleDate
    // fields[12]: salesHistoryKey
    // fields[13]: landRecordDocumentNbr
    public static boolean loadFromCSV(DataManager DM, String filename) {
        String data = Utils.readFileAsString( filename );
        String[] lines = data.split("\n");

        State state = DM.safeAddState( "VA" );
        if (state == null) { return false; }
        County county = state.safeAddCounty("Arlington County", 51013);
        if (county == null) { return false; }
        RealEstate re = new RealEstate();
        county.setRealEstate(re);

        long[] totals = new long[ periods.length ];
        long[] totals_count = new long[ periods.length ];

        // Skip header lines, and go thru each line
        for (int i = DM.realestate_file_start; i < lines.length; i++) {
            String[] fields = extractCleanFields( lines[i] );
            int salePrice = Utils.safeStringToInt(fields[10]);
            if (salePrice > 0) {
                int period_ind = Utils.safeStringToInt(fields[11].substring(0, 4)) - Utils.safeStringToInt(periods[0]);
                if (period_ind >= 0 && period_ind < periods.length) {
                    totals[period_ind] += salePrice;
                    totals_count[period_ind]++;
                }
            }
        }

        // Save the average
        int avg;
        for (int period_ind = 0; period_ind < periods.length; period_ind++) {
            if (totals_count[period_ind] == 0) {
                avg = 0;
            } else {
                avg = (int)(totals[period_ind] / totals_count[period_ind]);
            }
            re.setSalePrice(period_ind, avg);
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
            output[0][period] = getSalePrice( period );
        }
        return output;
    }


    public int getSalePrice(int period) {
        return salePrice[ period ];
    }


    public void setSalePrice(int period, int salePrice) {
        this.salePrice[ period ] = salePrice;
    }

}
