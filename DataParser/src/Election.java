import java.util.ArrayList;

public class Election {
    private double demVotes;
    private double gopVotes;
    private double totalVotes;

    public static boolean loadFromCSV(DataManager DM, String filename) {
        String data = "";
        try {
            data = Utils.readFileAsString( filename );
        }
        catch (Exception e) {
            return false;
        }

        String[] lines = data.split("\n");

        // Skip header lines, and go thru each line
        for (int i = DM.election_file_start; i < lines.length; i++) {
            lines[i] = cleanupFields( lines[i] );
            String[] fields = lines[i].split(",");

            State state = DM.safeAddState( fields[8] );
            County county = state.safeAddCounty( fields[9], Integer.parseInt( fields[10] ) );

            Election vote = new Election();
            vote.setDemVotes( (int) Double.parseDouble( fields[1] ) );
            vote.setGopVotes( (int) Double.parseDouble( fields[2] ));
            vote.setTotalVotes( (int) Double.parseDouble( fields[3] ));

            county.setVote( vote );
        }
        return true;
    }

    private static String cleanupFields(String line) {
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

    // Setters and getters
    public double getDemVotes() {
        return demVotes;
    }

    public void setDemVotes(double demVotes) {
        this.demVotes = demVotes;
    }

    public double getGopVotes() {
        return gopVotes;
    }

    public void setGopVotes(double gopVotes) {
        this.gopVotes = gopVotes;
    }

    public double getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(double totalVotes) {
        this.totalVotes = totalVotes;
    }

    @Override
    public String toString() {
        return "ElectionResult{" +
                "votes_dem=" + demVotes +
                ", votes_gop=" + gopVotes +
                ", total_votes=" + totalVotes +
                '}';
    }

}
