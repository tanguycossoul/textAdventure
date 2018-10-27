import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsObj_v1Test extends TestCase {

    @Test
    public void test1_add_abc() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.add("a");
        stats.add("b");
        stats.add("c");
        stats.add("a");
        assertEquals("abc", stats.toString());
    }

    @Test
    public void test2_add_bac() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.add("a");
        stats.add("b");
        stats.add("c");
        stats.add("b");
        assertEquals("bac", stats.toString());
    }

    @Test
    public void test3_add_cab() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.add("a");
        stats.add("b");
        stats.add("c");
        stats.add("c");
        assertEquals("cab", stats.toString());
    }

    @Test
    public void test4_addAllLetters() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.addAllLetters("abcc");
        assertEquals("cab", stats.toString());
    }

    @Test
    public void test5_getCountOf() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.addAllLetters("abcc");
        assertEquals(2, stats.getCountOf("c"));
    }

    @Test
    public void test6_size() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.addAllLetters("abcc");
        assertEquals(4, stats.size());
    }

    @Test
    public void test7_getNumUnique() {
        StatsObj_v1 stats = new StatsObj_v1(3);
        stats.addAllLetters("abcc");
        assertEquals(3, stats.getNumUnique());
    }
    @Test
    public void test8_getTopMostFreq() {
        StatsObj_v1 stats = new StatsObj_v1(2);
        stats.addAllLetters("abcc");
        assertEquals("[c, a]", stats.getTopMostFreq().toString());
    }
}