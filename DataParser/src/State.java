import java.util.ArrayList;
import java.util.List;

public class State {
    private String name; // abbreviation name
    private List<County> counties;
    private List<Integer> county_fips;

    public State(String name) {
        this.name = name;
        counties = new ArrayList<>();
        county_fips = new ArrayList<>();
    }

    // Safe add:
    // - if already exists, don't add and return existing
    // - if doesn't already exists, add and return it
    public County safeAddCounty(String name, int fips) {
        if (name.equals("") || fips <= 0) { return null; }

        if ( county_fips.contains( fips ) ) {
            return searchCounty( fips );
        }
        else {
            County county = new County( name, fips );
            counties.add( county );
            county_fips.add( fips );
            return county;
        }
    }

    private County searchCounty(int fips) {
        for (County c : counties) {
            if (c.getFips() == fips) {
                return c;
            }
        }
        return null; // oops
    }

    public boolean removeCounty(County c) {
        return counties.remove( c );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }
}
