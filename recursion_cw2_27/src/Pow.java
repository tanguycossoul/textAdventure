public class Pow {
    public static void main(String[] args) {
        System.out.println( pow(2, 6) );
    }

    private static double pow(double v, int i) {
        if (i == 1) { return v; }
        else {
            return v * pow(v, i-1);
        }
    }
}
