public class County {
    private String name; // not unique! use FIPS instead
    private int fips;    // unique ID
    private Education educ;
    private Employment employ;
    private RealEstate re;

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

    public Education getEduc() {
        return educ;
    }

    public void setEduc(Education educ) {
        this.educ = educ;
    }

    public Employment getEmploy() {
        return employ;
    }

    public void setEmploy(Employment employ) {
        this.employ = employ;
    }

    public RealEstate getRealEstate() {
        return re;
    }

    public void setRealEstate(RealEstate re) {
        this.re = re;
    }
}
