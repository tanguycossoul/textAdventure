import java.util.ArrayList;

public class StatsObjTester_v0 {

    public static void main(String[] args) {
        StatsObj_v0 obj = new StatsObj_v0();
        obj.add("a");
        obj.add("a");
        obj.add("a");
        obj.add("b");
        obj.add("b");
        obj.add("c");
        obj.add("z");

        ArrayList<String> printlist = obj.getList();
        for (int ind = 0; ind < 7; ind ++) {
            System.out.println(printlist.get(ind));
        }

        System.out.println(obj.getCountOf("a") + " = getCountOf(a)");
        System.out.println(obj.getCountOf("j") + " = getCountOf(j)");
        System.out.println(obj.getTotalCount() + " = getTotalCount");
        System.out.println(obj.getUniqueVals() + " = getUniqueVals") ;
        System.out.println( obj.getTop3MostFreq()  + " : getTop3MostFreq()");
        System.out.println( obj.getTopNMostFreq(3) + " : getTopNMostFreq(3)");
    }
}