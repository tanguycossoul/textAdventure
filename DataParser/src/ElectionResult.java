public class ElectionResult {
    private int votes_dem;
    private int votes_gop;
    private int total_votes;
    private double per_dem;
    private double per_gop;
    private int diff;
    private double per_point_diff;
    private String state_abbr;
    private String county_name;
    private int combined_fips;

    public ElectionResult(String[] fields) {
        this.votes_dem = (int) Double.parseDouble( fields[1] );
        this.votes_gop = (int) Double.parseDouble( fields[2] );
        this.total_votes = (int) Double.parseDouble( fields[3] );
        this.per_dem = Double.parseDouble( fields[4] );
        this.per_gop = Double.parseDouble( fields[5] );
        this.diff = (int) Double.parseDouble( fields[6] );
        this.per_point_diff = Double.parseDouble( fields[7] );
        this.state_abbr = fields[8];
        this.county_name = fields[9];
        this.combined_fips = Integer.parseInt( fields[10] );
    }

    public ElectionResult(int votes_dem, int votes_gop, int total_votes, double per_dem, double per_gop, int diff, double per_point_diff, String state_abbr, String county_name, int combined_fips) {
        this.votes_dem = votes_dem;
        this.votes_gop = votes_gop;
        this.total_votes = total_votes;
        this.per_dem = per_dem;
        this.per_gop = per_gop;
        this.diff = diff;
        this.per_point_diff = per_point_diff;
        this.state_abbr = state_abbr;
        this.county_name = county_name;
        this.combined_fips = combined_fips;
    }

    public int getVotes_dem() {
        return votes_dem;
    }

    public void setVotes_dem(int votes_dem) {
        this.votes_dem = votes_dem;
    }

    public int getVotes_gop() {
        return votes_gop;
    }

    public void setVotes_gop(int votes_gop) {
        this.votes_gop = votes_gop;
    }

    public int getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(int total_votes) {
        this.total_votes = total_votes;
    }

    public double getPer_dem() {
        return per_dem;
    }

    public void setPer_dem(double per_dem) {
        this.per_dem = per_dem;
    }

    public double getPer_gop() {
        return per_gop;
    }

    public void setPer_gop(double per_gop) {
        this.per_gop = per_gop;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public double getPer_point_diff() {
        return per_point_diff;
    }

    public void setPer_point_diff(double per_point_diff) {
        this.per_point_diff = per_point_diff;
    }

    public String getState_abbr() {
        return state_abbr;
    }

    public void setState_abbr(String state_abbr) {
        this.state_abbr = state_abbr;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public int getCombined_fips() {
        return combined_fips;
    }

    public void setCombined_fips(int combined_fips) {
        this.combined_fips = combined_fips;
    }

    @Override
    public String toString() {
        return "ElectionResult{" +
                "votes_dem=" + votes_dem +
                ", votes_gop=" + votes_gop +
                ", total_votes=" + total_votes +
                ", per_dem=" + per_dem +
                ", per_gop=" + per_gop +
                ", diff=" + diff +
                ", per_point_diff=" + per_point_diff +
                ", state_abbr='" + state_abbr + '\'' +
                ", county_name='" + county_name + '\'' +
                ", combined_fips=" + combined_fips +
                '}';
    }
}

