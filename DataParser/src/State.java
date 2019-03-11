import java.util.ArrayList;
import java.util.List;

public class State {
    private String name; // abbreviation name
    private List<County> counties;
    private List<String> county_names;

    public State(String name) {
        this.name = name;
        counties = new ArrayList<>();
        county_names = new ArrayList<>();
    }

    // Safe add:
    // - if already exists, don't add and return existing
    // - if doesn't already exists, add and return it
    public County safeAddCounty(String name) {
        if ( county_names.contains( name ) ) {
            return searchCounty( name );
        }
        else {
            County county = new County(name);
            counties.add( county );
            county_names.add( name );
            return county;
        }
    }

    private County searchCounty(String name) {
        for (County c : counties) {
            if (c.getName().equals( name )) {
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
