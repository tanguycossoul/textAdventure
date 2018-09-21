public class cw_0919_random_betting {
    static int numOfTimesReachingGoal = 0;
    public static void main(String[] args) {
        displayChances(50, 150, 10000);
    }

    public static void displayChances(int initial, int goal, int trials) {
        int numTrialsSum = 0;
        double totalTrials = 10000, averageTrials, chanceOfReachingGoal;
        for (int i = 0;i <trials;i++){
            numTrialsSum += bet(initial, goal);
        }
        chanceOfReachingGoal = numOfTimesReachingGoal/totalTrials;
        averageTrials = numTrialsSum/totalTrials;
        System.out.println("Chance Of Meeting Goal: " + (chanceOfReachingGoal * 100) + "%\t\tAverage Number Of Trials Before Reaching 0 or Goal: " + averageTrials);
    }

  public static   int randomBetting(){
        if (Math.random() > 0.5) {
            return 1;
        } else
            return -1;
    }
    public static int bet(int mon, int goal){
        int numOfTrials = 0;
        while(mon<goal&&mon>0){
            mon += randomBetting();
            numOfTrials++;
        }
        if (mon == goal) {
            numOfTimesReachingGoal++;
        }
        return numOfTrials;
    }
}