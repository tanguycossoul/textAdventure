public class CW0306 {

    public static void insertxBTWDups(String str, int counter) {
        int i = counter;
        counter++;
        if( str.substring(i, i + 1).equals(str.substring(i + 1, i + 2))){
            str = str.substring( i, i + 1) +"*" + str.substring(i + 1, i + 2);
            System.out.println(str);
            CW0306.insertxBTWDups(str, i);
        }
    }
}
