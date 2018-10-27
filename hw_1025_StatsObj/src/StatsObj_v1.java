import java.util.ArrayList;

// Uses a priority queue
public class StatsObj_v1 {

    private ArrayList<Entry> data;
    private int num_top_most = 0;
    private int total_letters_count = 0;

    // constructor: takes N, which is how many “most frequent” letter our getTopMostFreq() will return
    public StatsObj_v1(int N) {
        this.total_letters_count = 0;
        this.num_top_most = N;
        data = new ArrayList<>();
    }


    // check that item is a single letter and add it to your statsObj
    public void add(String item) {
        if (item.length() != 1) {
            return;
        }

        total_letters_count++;

        // If the letter already exists, increment its count
        for (int i = 0; i < data.size() ; i++) {
            if (data.get(i).getLetter().equals(item)) {
                data.get(i).incCount();
                reorder(i);
                return;
            }
        }

        // If the letter doesn't alreayd exists, add it
        Entry newEntry = new Entry(item, 1);
        data.add(newEntry);

    }

    private void reorder(int letter_index) {
        if (letter_index == 0)
            return;

        int i = letter_index - 1;
        while (i > 0 && data.get(i).getCount() < data.get(letter_index).getCount() ) {
            i--;
        }
        Entry newEntry = new Entry(data.get(letter_index).getLetter(), data.get(letter_index).getCount());
        data.remove(letter_index);
        data.add(i, newEntry);
    }


    // add ALL letters from str to your obj
    public void addAllLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            String letter = str.substring(i, i+1);
            add(letter);
        }
    }


    // return the frequency of testLetter
    public int getCountOf(String testLetter) {
        for (Entry e : data) {
            if (e.getLetter().equals( testLetter )) {
                return e.getCount();
            }
        }
        return 0;
    }


    // returns the total number of letters including duplicates
    // should run in O(1)
    public int size() {
        return total_letters_count;
    }


    // return the number of unique things in your obj (returns the number of unique letters in the list)
    // should run in O(1)
    public int getNumUnique() {
       return data.size();
    }


    // return the most frequent thing in your obj
    public String getMostFreq() {
        if (data.size() != 0) {
            return data.get(0).getLetter();
        }
        return "";
    }


    // returns top N most freq where N is given in constructor
    public ArrayList<String> getTopMostFreq() {
        ArrayList<String> output = new ArrayList<>();
        int num_letter = num_top_most < data.size() ? num_top_most : data.size();
        for (int i = 0; i < num_letter; i++) {
            output.add( data.get(i).getLetter() );
        }
        return output;
    }

    public String toString() {
        String output = "";
        for (Entry e : data) {
            output += e.getLetter();
        }
        return output;
    }
}