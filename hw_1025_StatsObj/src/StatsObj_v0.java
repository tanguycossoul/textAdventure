import java.util.ArrayList;

public class StatsObj_v0 {
    private static ArrayList<String> list = new ArrayList<>();

    //---------------------------------------------------------------
    public static void add(String NewWord) {
        list.add(NewWord);
    }

    //---------------------------------------------------------------
    public static ArrayList<String> getList() {
        return list;
    }

    //---------------------------------------------------------------
    public static int getCountOf(String letter) {
        int count = 0;
        for (String word : list) {
            if (word.indexOf(letter) != -1) {
                count++;
            }
        }
        return count;
    }

    //---------------------------------------------------------------
    public static int getTotalCount() {
        return list.size();
    }

    //---------------------------------------------------------------
    public ArrayList<String> getUniqueList() {
        ArrayList<String> uniquelist = new ArrayList<>();
        boolean unique;
        for (String word1 : list) {
            unique = true;
            for (String word2 : uniquelist)
                if (word1.equals(word2)) {
                    unique = false;
                    break;
                }
            if (unique) {
                uniquelist.add(word1);
            }
        }
        return uniquelist;
    }

    //---------------------------------------------------------------
    public int getUniqueVals() {
        return getUniqueList().size();
    }

    //---------------------------------------------------------------
    public ArrayList<String> getTop3MostFreq() {
        return getTopNMostFreq(3);
    }

    //---------------------------------------------------------------
    public ArrayList<String> getTopNMostFreq(int n) {
        ArrayList<String> unique_list = getUniqueList();
        ArrayList<String> max_list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int max_occurence = 0;
            String max_word = "";
            for (String word : unique_list) {
                int count = getCountOf(word);
                if (count > max_occurence) {
                    max_occurence = count;
                    max_word = word;
                }
            }
            unique_list.remove( unique_list.indexOf(max_word) );
            max_list.add(max_word);
            if (max_list.size() == n)
                break;
        }
        return max_list;
    }
}