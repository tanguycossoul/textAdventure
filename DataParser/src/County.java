public class County {
    private String name; // not unique! use FIPS instead
    private int fips; // unique ID
    private Election vote;
    private Education educ;
//    private Employment employ;

    public County(String name, int fips) {
        this.name = name;
        this.fips = fips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public Election getVote() {
        return vote;
    }

    public void setVote(Election vote) {
        this.vote = vote;
    }

    public Education getEduc() {
        return educ;
    }

    public void setEduc(Education educ) {
        this.educ = educ;
    }

//    public Employment getEmploy() {
//        return employ;
//    }
//
//    public void setEmploy(Employment employ) {
//        this.employ = employ;
//    }
}
