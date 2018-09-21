public class cw_0919_triangleprob {
    static double side1, side2, side3;
    static int numTrials = 10000;
    public static void main(String[] args) {
        System.out.println("Probability of Triangle: " + (getProbabilityOfTriangle(numTrials) * 100) + "%");
    }
    public static void getSideLengths() {
        double remainingLength = 1;
        side1 = Math.random();
        remainingLength -= side1;
        side2 = Math.random() * remainingLength;
        remainingLength -= side2;
        side3 = remainingLength;
    }

    public static boolean testForTriangle(double a, double b, double c) {
        return ((a + b > c) && (a + c > b) && (b + c > a));
    }

    public static double getProbabilityOfTriangle(int numTrials) {
        int numOfTriangles = 0;
        double probability;
        for (int i = 0; i < numTrials; i++) {
            getSideLengths();
            if (testForTriangle(side1, side2, side3)) {
                numOfTriangles++;
            }
        }
        probability = (double)(numOfTriangles)/numTrials;
        return probability;
    }
}
