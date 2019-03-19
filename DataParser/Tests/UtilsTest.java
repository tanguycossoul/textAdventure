import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void cleanLineTest() {
        String s;
        s = "3,2,\"334,125\",3%,\"1,000,000\"";
        assertEquals( "3,2,334125,3%,1000000", Utils.removeQuotesAndComa( s ));

        s = "3,2,42,3";
        assertEquals( "3,2,42,3", Utils.removeQuotesAndComa( s ));

        s = "3,2,\"42\",3";
        assertEquals( "3,2,42,3", Utils.removeQuotesAndComa( s ));

        s = "3,2,\"1,000,000\",3";
        assertEquals( "3,2,1000000,3", Utils.removeQuotesAndComa( s ));
    }
}