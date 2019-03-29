public class TimeFrame {
    private int start = 0;
    private int end = 100;

    public TimeFrame ( int start, int end) {
        this.start = start;
        this.end = end;
    }
    public int getMax() {
        return this.end;
    }

    public int getMin() {
        return this.start;
    }
}
