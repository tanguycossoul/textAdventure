public class Entry {
    private String letter;
    private int count;

    public Entry(String letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // Increment by 1
    public void incCount() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}
